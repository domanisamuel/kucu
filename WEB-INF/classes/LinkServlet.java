
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.sql.*;

public class LinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LinkServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		ServletContext sc=getServletContext();
		String driver = sc.getInitParameter("driver");
		String dburl = sc.getInitParameter("dburl");
		String dbuser = sc.getInitParameter("dbuser");
		String dbpwd = sc.getInitParameter("dbpwd");
		
		
		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String regNo = req.getParameter("regNo");
		String phoneNo = req.getParameter("phoneNo");
		String email = req.getParameter("email");
		String yrStudy = req.getParameter("yrStudy");
		String courseDuration = req.getParameter("courseDuration");
		String gender = req.getParameter("gender");
		String zone = req.getParameter("zone");
		String facil = req.getParameter("facil");
		
		MemberReg newMem=new MemberReg(fName, lName, regNo, phoneNo,  email,  yrStudy,
										courseDuration, gender,  zone,facil);
		String message=newMem.register(driver, dburl, dbuser, dbpwd);
		
		//pw.println("document.getElementById('bottom').innerHTML=loadPage('signup.html')");
		pw.println(message);
	}
}


