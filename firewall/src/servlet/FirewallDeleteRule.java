package servlet;


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


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        response.setContentType("application/octet-stream");
        InputStream in = request.getInputStream();
        ObjectInputStream inputFromApplet = new ObjectInputStream(in);
        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);


        System.out.println("Accessed");

        File myTempFile = null;
        File rules = null;

        BufferedWriter bw = null;
        System.out.println("AccessedHere");

        try (BufferedReader br = new BufferedReader(new FileReader("/home/ciunas/Project/testFile"))) {


            Rules r = (Rules) inputFromApplet.readObject();
            String rule = r.getRule();
            System.out.println(rule);
            String line;

            myTempFile = createTempFile("myTempFile",".tmp");
            myTempFile.deleteOnExit();
            rules = new File("/home/ciunas/Project/testFile");
            bw = new BufferedWriter(new FileWriter(myTempFile));

            while (( line = br.readLine()) != null) {

                if (line.matches(rule)) {
                    System.out.println("Matches");
                } else {
                    bw.append(line + "\n");
                    System.out.println("Written to Temp file : " + myTempFile.getAbsolutePath());
                }

            }


        } catch (IOException e) {
            System.out.println("printstack error");
            e.printStackTrace();


        } finally {
            bw.close();
            copyFile(myTempFile, rules);

            oos.writeInt(1);
            oos.flush();
            oos.close();
            System.out.println("finally");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
        System.out.println("Accessed");
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
        Files.copy(source.toPath(), dest.toPath());
    }


}


