package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(urlPatterns = {"/FirewallRuleAdd"})
public class FirewallRuleAdd extends HttpServlet {

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
            throws ServletException, IOException {

        response.setContentType("application/octet-stream");
        InputStream in = request.getInputStream();
        ObjectInputStream inputFromApplet = new ObjectInputStream(in);
        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);
        String rule = null;

        System.out.println("Accessed");

        File rules = null;

        System.out.println("AccessedHere");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/ciunas/script.sh"))) {

            Rules r = (Rules) inputFromApplet.readObject();
            rule = r.getRule();
            bw.write(rule + "\n");
            bw.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/ciunas/Project/testFile", true))) { //must use this flag to append

            String[] cmd = {"/bin/bash","-c","echo \"nag0ri4H\"| sudo -S /home/ciunas/script.sh"};
            Process pb = Runtime.getRuntime().exec(cmd);
            bw.append(rule + "\n");

        }  finally {

            oos.writeInt(1);
            oos.flush();
            oos.close();
            System.out.println("finally");
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
        System.out.println("Accessed");
        processRequest(request, response);
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
        System.out.println("Accessed");
        processRequest(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Add a Rule to firewall";
    }// </editor-fold>
}
