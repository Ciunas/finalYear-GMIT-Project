package finalProxy;

import java.io.InputStream;
import java.io.OutputStream;


public class SSLconect implements Runnable{

    private OutputStream outStream = null;
    private InputStream inStream = null;

    private volatile boolean isClosed = false;
    private int messagePointer = 0;
    private byte[] currentMessage = null;

    private Record currentRecord = new Record();
    private boolean upStream;

    public SSLconect(InputStream in, OutputStream out, boolean upStream) {
        this.outStream = out;
        this.inStream = in;
        this.upStream = upStream;
    }

    public void run() {
       
    	//reading and writing messages
        while(currentRecord.read(this.inStream) != -1) {
            currentRecord.write(outStream);
            System.out.println("alive in records");
        }
        System.out.println("finished writing records");
        close();
        return;
    }
    
    //close
    public void close() {
        this.isClosed = true;
    }

}
