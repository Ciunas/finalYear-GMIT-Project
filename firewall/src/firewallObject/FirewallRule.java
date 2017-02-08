package firewallObject;

import java.io.Serializable;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FirewallRule implements Serializable {

    private static final long serialVersionUID = 1L;
    String rule = "";					// Full rule
    boolean end = false;
    public Vector<String> myVector= new Vector<String>();
    public List<String> Interfaces = new ArrayList<String>();


    public Vector<String> getMyVector() {
        return myVector;
    }

    public void setMyVector(Vector<String> myVector) {
        this.myVector = myVector;
    }

    public void addToMyVector(String vectorData ){
        myVector.add(vectorData);
    }

    public void resetMyVector(){
        myVector.clear();
    }

    public FirewallRule(boolean end) throws SocketException {
        this.end = end;
    }

    public FirewallRule(String rule) throws SocketException {
        this.rule = rule;
    }

    //Getters and Setters
    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
    //Constructor
    public FirewallRule() throws SocketException {
        super();
    }

}