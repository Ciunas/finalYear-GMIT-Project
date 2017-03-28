package servlet;

import firewallObject.FirewallRule;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FirewallDeleteRule extends HttpServlet {

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
			throws ServletException, IOException, ClassNotFoundException {

		response.setContentType("application/octet-stream");
		InputStream in = request.getInputStream();
		ObjectInputStream inputFromApplet = new ObjectInputStream(in);
		OutputStream outstr = response.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outstr);

		FirewallRule r = (FirewallRule) inputFromApplet.readObject();
		String rule = r.getRule();
		System.out.println(rule);
		rule = rule.replace("-A ", "-D ").replace("-P ", "-D ").replace("-N ", "-D ");
		System.out.println(rule);

		String[] cmd = { "/bin/bash", "-c", "echo \"password\" | sudo -S /sbin/iptables " + rule };
		Process pb = Runtime.getRuntime().exec(cmd);
		try {
			pb.waitFor();
			oos.writeInt(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			oos.writeInt(-1);
		}
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
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	}


}
