package servlet;

import firewallObject.FirewallRule;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by ciunas on 1/30/17.
 */
@WebServlet(urlPatterns = {"/FirewallViewRules"})
public class FirewallViewRules extends HttpServlet {


    *//**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *//*
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/octet-stream");

        InputStream in = request.getInputStream();
        ObjectInputStream inputFromApplet = new ObjectInputStream(in);
        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);

        String[] cmd = {"/bin/bash","-c"," echo \"nag0ri4H\" | sudo -S /opt/tomcat/webapps/firewallServlet/script.sh"};

        Process pb = Runtime.getRuntime().exec(cmd);

        try {
            pb.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("/opt/tomcat/webapps/firewallServlet/iptables"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                FirewallRule r = new FirewallRule(line);
                oos.writeObject(r);
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FirewallRule r = new FirewallRule(true);
            oos.writeObject(r);
            oos.flush();
            oos.close();
        }

    }

    *//**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     *//*

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    *//**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     *//*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    *//**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     *//*
    @Override
    public String getServletInfo() {
        return "View Firewall Rule.";
    }// </editor-fold>


    public class Rules implements Serializable {


        private static final long serialVersionUID = 1L;
        String name = "";;				//User name for rule
        String type = "";;				//TCP or UDP
        String direction = "";; 			//In or Out direction
        int port = 0; 					//Port number
        String ip = "0.0.0.0"; 					//ip to block
        String rule = "";					// Full rule
        boolean end = false;

        public Rules(boolean end) {
            this.end = end;
        }

        public Rules(String rule) {
            this.rule = rule;
        }

        public Rules(String name, String type, String direction, int port, String ip, String rule) {
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
        public Rules() {
            super();
        }
    }



}
