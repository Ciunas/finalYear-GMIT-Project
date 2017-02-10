package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import firewallObject.*;

@WebServlet(urlPatterns = {"/TerminalEmulator"})
public class TerminalEmulator extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,ClassNotFoundException {

        response.setContentType("application/octet-stream");
        InputStream in = request.getInputStream();
        ObjectInputStream inputFromApplet = new ObjectInputStream(in);
        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);



        FirewallRule r = (FirewallRule) inputFromApplet.readObject();
        String command = r.getCommand();

        String[] cmd = {"/bin/bash", "-c","echo \"password\" | sudo -S " + command };
        String[] cmd1 = {"/bin/bash", "-c", command};
        Process pb = null;
        System.out.println(command);
        if(command.contains("cd ")){
            try {
                pb = Runtime.getRuntime().exec(cmd1);
                System.out.println("cd Used");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            try {
                pb = Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }

        String line;
        FirewallRule r1 = new FirewallRule();
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {

            r1.myVector.add(line);
            System.out.println( line);
        }
        input.close();
        oos.writeObject(r1);
        oos.flush();
        oos.close();
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "View Firewall Rule.";
    }
}
