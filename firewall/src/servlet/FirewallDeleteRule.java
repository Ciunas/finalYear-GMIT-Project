package servlet;

import firewallObject.FirewallRule;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.nio.file.Files;

import static java.io.File.createTempFile;

/**
 * Created by ciunas on 1/30/17.
 */
@WebServlet(urlPatterns = {"/FirewallDeleteRule"})
public class FirewallDeleteRule extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {

        response.setContentType("application/octet-stream");
        InputStream in = request.getInputStream();
        ObjectInputStream inputFromApplet = new ObjectInputStream(in);
        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);




        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/opt/tomcat/webapps/firewallServlet/command.sh"))) {

            FirewallRule r = (FirewallRule) inputFromApplet.readObject();
            String rule = r.getRule();
            System.out.println(rule);
            rule = rule.replace("-A ","-D ");
            bw.write("#!/bin/bash" + "\n");
            bw.append("iptables ");
            rule = rule.replace("-A ", "-D ").replace("-P ", "-D ").replace("-N ", "-D ");
            bw.append(rule + "\n");
            bw.flush();
            System.out.println(rule);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            String[] cmd = {"/bin/bash","-c"," echo \"nag0ri4H\" | sudo -S /opt/tomcat/webapps/firewallServlet/command.sh"};
            Process pb = Runtime.getRuntime().exec(cmd);
            try {
                pb.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            oos.writeInt(1);
            oos.flush();
            oos.close();
        }
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
        return "Delete Firewall Rule.";
    }// </editor-fold>


    private static void copyFile(File source, File dest) throws IOException {
        dest.delete();
        System.out.println(source.getAbsolutePath() + "  " + dest.getAbsolutePath());
        //Files.copy(source.toPath(), dest.toPath());
    }


}


