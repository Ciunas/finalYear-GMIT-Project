package firewallObject;

import java.io.Serializable;

public class FirewallRule implements Serializable {

    private static final long serialVersionUID = 1L;
    String name = "";;				//User name for rule
    String type = "";;				//TCP or UDP
    String direction = "";; 			//In or Out direction
    int port = 0; 					//Port number
    String ip = "0.0.0.0"; 					//ip to block
    String rule = "";					// Full rule
    boolean end = false;

    public FirewallRule(boolean end) {
        this.end = end;
    }

    public FirewallRule(String rule) {
        this.rule = rule;
    }

    public FirewallRule(String name, String type, String direction, int port, String ip, String rule) {
        this.name = name;
        this.type = type;
        this.direction = direction;
        this.port = port;
        this.ip = ip;
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", direction='" + direction + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }

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
    public FirewallRule() {
        super();
    }

}
