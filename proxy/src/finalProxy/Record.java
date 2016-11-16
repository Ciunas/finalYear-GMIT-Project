package finalProxy;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;

import selproxy.TlsRecord.CopyStatus;

public class Record {
	
	   public enum CopyStatus {
	        MSG_FINISHED, RECORD_FINISHED, MSG_AND_RECORD_FINISHED
	    }

	    byte[] recordHeader = new byte[5];  //TLSRecord header.
	    ByteBuffer tlsRecordPayload = null; //TLSRecord payload
	    public int bytesIntoPayload = 0;    //TLSRecord payload pointer

	    private byte contentType;           //Type of TLS Record.
	    private int length;                 //Length of TLS Record payload
	    private short version;              //Version of TLS/SSL record.


	    //TLSRecord Content-Types
	    public static final byte ChangeCipherSpec = 0x14;
	    public static final byte Alert = 0x15;
	    public static final byte Handshake = 0x16;
	    public static final byte Application = 0x17;
	    public static final byte Heartbeat = 0x18;

	    //Handshake message types
	    public static final byte HelloRequest = 0x0;
	    public static final byte ClientHello = 0x1;
	    public static final byte ServerHello = 0x2;
	    public static final byte Certificate = 0xb;
	    public static final byte ServerKeyExchange = 0xc;
	    public static final byte CertificateRequest = 0xd;
	    public static final byte ServerHelloDone = 0xe;
	    public static final byte CertificateVerify = 0xf;
	    public static final byte ClientKeyExchange = 0x10;
	    public static final byte Finished = 0x14;

	    //Version types (Minor)
	    public static final byte Ssl3 = 0;
	    public static final byte Tls10 = 0x1;
	    public static final byte Tls11 = 0x2;
	    public static final byte Tls12 = 0x3;


	    //Protocol Message constants
	    public static final int HANDSHAKE_RECORD_HDR_SIZE = 4;
	    public static final int HANDSHAKE_MESSAGE_HDR_SIZE = 4;

	    public Record() {

	    }
	    /**
	     *
	     * TODO - Not tested - Does this replace the original read method?
	     * Copy a message from the current TLSRecord into a buffer. It also takes into account
	     * messages that are fragmented across multiple TLSRecords.
	     *
	     * @param messageBuf The buffer to copy it in to
	     * @param msgPtr The offset into the buffer with which to start writing data from.
	     */
	    public CopyStatus copyNextMessage(byte[] messageBuf, int msgPtr) {
	        //case 1 - msgPtr == buf.length -> Message is finished but Record has other messages.
	        //case 2 - bytesInto == this.length -> Reached end of TLSRecord but not message.
	        //case 3 - both conditions are false -> Reached the end of message AND TLSRecord.
	        // msgbufferPtr < messageBuf.length && offset into payload < payload length.
	        while (msgPtr < messageBuf.length && bytesIntoPayload < this.length) {
	            messageBuf[msgPtr] = tlsRecordPayload.get(bytesIntoPayload);
	            bytesIntoPayload++;
	            msgPtr++;
	        }
	        //Return the status of the copying.
	        if(msgPtr >= messageBuf.length && bytesIntoPayload >= this.length) {
	            return CopyStatus.MSG_AND_RECORD_FINISHED;
	        } else if ( msgPtr >= messageBuf.length) {
	            return CopyStatus.MSG_FINISHED;
	        } else {
	            return CopyStatus.RECORD_FINISHED;
	        }
	    }

	    /**
	     *
	     * TODO - Tested - works.
	     * Read in a given TLSRecord from the input stream i. The header is stored in recordHeader
	     * and the TLS Record payload is stored in tlsRecordPayload.
	     *
	     * @param i - The input
	     * @return - Whether or not the read was successful. -1 indicates end of stream, -2 for reading less than 5 bytes of the header.
	     */
	    public int read(InputStream i) {
	        int count = 0;
	        int readBytes = 0;
	        try {
	            //Sometimes even before reading anything the other end closes the connection..
	            readBytes = i.read(recordHeader);
	            if (readBytes == -1) {
	                //Connection closed.
	               /// Log.v(ProxyMain.TAG, "EOF - Closed.");
	                return -1;
	            } else if (readBytes != recordHeader.length) {
	               // Log.e(ProxyMain.TAG, "Didn't read 5-byte TLS Record header, only got " + readBytes);
	                return -2;
	            }
	            this.contentType = recordHeader[0];
	            //Assign length of TLSRecord
	            this.length = ((recordHeader[3] << 8) & 0x0000ff00) | (recordHeader[4] & 0x000000ff);
	            //Only want minor version of the SSL/TLS.
	            this.version = recordHeader[2];

	            //Read in the rest of the TLS record into the ByteBuffer.
	            tlsRecordPayload = ByteBuffer.wrap(new byte[this.length]);
	            //Reset the counter into the payload
	            bytesIntoPayload = 0;

	            while (count < this.length) {
	                //Pull all of the TLS record payload into the buffer.
	                tlsRecordPayload.put((byte) i.read());
	                count++;
	            }
	        }
	        catch (SocketException se) {
	            //Log.e(ProxyMain.TAG, "Unexpected socket error when trying to read: " + se.getMessage());
	        }
	        catch (IOException ioe) {
	            ioe.printStackTrace();
	        }

	        return 1;
	    }

	    /**
	     *
	     * Write a given TLSRecord to the outputstream o. Read must have been invoked on this
	     * object before attempting to write it to an output stream.
	     * @param o
	     */
	    public boolean write(OutputStream o) {

	        BufferedOutputStream bo = new BufferedOutputStream(o);

	        if(tlsRecordPayload == null) {
	            //error.
	            return false;
	        }
	        //Write the header.
	        try {
	            bo.write(recordHeader);
	            bo.write(tlsRecordPayload.array());
	            bo.flush();

	        } catch (SocketException se) {
	           // Log.e(ProxyMain.TAG, "Unexpected socket error when trying to write: " + se.getMessage());
	        }
	        catch (IOException ioe) {
	            ioe.printStackTrace();
	            return false;
	        }
	        return true;
	    }

	    public int getRecordPayloadLength() {
	        return this.length;
	    }

	    /**
	     * By taking the current position inside the payload as the starting position of a message,
	     * calculate the size of the next message. It does not move the pointer into the payload.
	     * @return
	     */
	    public int getNextMessageLength() {
	        //Assuming the current position held in bytesIntoPayload is the beginning of the msg,
	        //calculate the size of the message.
	        //We only care about the first 8bits.
	        int msb = ((int)tlsRecordPayload.get(bytesIntoPayload+1)) & 0xf;
	        int nsb = ((int)tlsRecordPayload.get(bytesIntoPayload+2)) & 0xf;
	        int lsb = ((int)tlsRecordPayload.get(bytesIntoPayload+3)) & 0xf;
	        return (msb << 16) | (nsb << 8) | lsb;
	    }

	    /**
	     * By taking the current position inside the payload as the starting position of a TLS message, return
	     * the type of the message
	     * @return The 8-bit value corresponding to the type of Message inside the TLSRecord.
	     */
	    public int getNextMessageType() {
	        return ((int)tlsRecordPayload.get(bytesIntoPayload)) & 0xF;
	    }


	    public byte getContentType() {
	        return contentType;
	    }

	    public short getVersion() {
	        return version;
	    }

	    public int getLength() {
	        return length;
	    }
}
