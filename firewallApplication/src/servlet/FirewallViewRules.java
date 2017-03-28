package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import firewallObject.*;

public class FirewallViewRules extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/octet-stream");
		InputStream in = request.getInputStream();
		ObjectInputStream inputFromApplet = new ObjectInputStream(in);
		OutputStream outstr = response.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outstr);

		String[] cmd = { "/bin/bash", "-c", "echo \"password\" | sudo -S /sbin/iptables -S" };
		Process pb = Runtime.getRuntime().exec(cmd);
		String line;

		BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
		while ((line = input.readLine()) != null) {
			FirewallRule r = new FirewallRule(line);
			oos.writeObject(r);
			oos.flush();
		}
		input.close();
		FirewallRule r = new FirewallRule(true);
		oos.writeObject(r);
		oos.flush();
		oos.close();
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
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