
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement st;
	String message;
	String fName, lName, regNo, phoneNo, email, gender, zone, yrStudy, courseDuration, facil;

	public RegisterServlet() {
		super();
	}

	public void init(ServletConfig cg) throws ServletException {
		super.init(cg);
		ServletContext sc = getServletContext();
		try {
			Class.forName(sc.getInitParameter("driver"));
			con = DriverManager.getConnection(sc.getInitParameter("dburl"), sc.getInitParameter("dbuser"),
					sc.getInitParameter("dbpwd"));
		} catch (ClassNotFoundException e) {
			message = e.toString();
		} catch (SQLException e) {
			message = e.toString();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

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

		message = register("'" + fName + "'", "'" + lName + "'", "'" + regNo + "'", "'" + phoneNo + "'",
				"'" + email + "'", "'" + yrStudy + "'", "'" + courseDuration + "'", "'" + gender + "'",
				"'" + zone + "'", "'" + facil + "'");

		res.sendRedirect("index.html");
	}

	public String register(String fName, String lName, String regNo, String phoneNo, String email, String yrStudy,
			String courseDuration, String gender, String zone, String facil) {

		int resultChecker = 0;
		if (con != null) {
			try {
				st = con.createStatement();
			} catch (SQLException e) {
				message = e.toString();
			}
			if (st != null) {
				String query2=
				String query1 = "insert into register values (" + fName + "," + lName + "," + regNo + "," + phoneNo
						+ "," + email + "," + yrStudy + "," + courseDuration + ", " + gender + ", " + zone + ", "
						+ facil + ")";

				try {
					resultChecker = st.executeUpdate(query1);
				} catch (SQLException e) {
					message = e.toString();
				}

				if (resultChecker != 0) {
					message = "Succesfully added";
				} else {
					message = "Addition of record not successfull";
				}
			} else {
				message = "Null pointer exception in the connection1";
			}
		} else {
			message = "Null pointer exception in the connection2";
		}
		return message;
	}

	public void destroy() {
		try {
			st.close();
		} catch (SQLException e) {
			message = e.toString();
		}
		try {
			con.close();
		} catch (SQLException e) {
			message = e.toString();
		}

	}

}
