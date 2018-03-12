
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MemberReg {
	String fName, lName, regNo, phoneNo, email, gender, zone, yrStudy, courseDuration, facil;

	MemberReg(String fName, String lName, String regNo, String phoneNo, String email, String yrStudy,
			String courseDuration, String gender, String zone, String facil) {
		this.fName = "'" + fName + "'";
		this.lName = "'" + lName + "'";
		this.regNo = "'" + regNo + "'";
		this.phoneNo = "'" + phoneNo + "'";
		this.email = "'" + email + "'";
		this.yrStudy = "'" + yrStudy + "'";
		this.courseDuration = "'" + courseDuration + "'";
		this.gender = "'" + gender + "'";
		this.zone = "'" + zone + "'";
		this.facil = "'" + facil + "'";
		
	}

	public String register(String driver, String url, String user, String pass) {
		Connection con=null;
		Statement st =null;
		String message="";
		int resultChecker=0;
		try {
			Class.forName(driver);
		}
		catch (ClassNotFoundException e) {
			message=e.toString();
		} 
		try{
			con = DriverManager.getConnection(url, user, pass);
		}
			catch (SQLException e) {
				message=e.toString();
			}
			if (con != null) {
				try{
				st = con.createStatement();
				}
				catch (SQLException e) {
					message=e.toString();
				}
				if (st != null) {
					String query1 = "insert into register values (" + this.fName + "," + this.lName + "," + this.regNo+ "," + this.phoneNo +"," + this.email + ","+ this.yrStudy+","+this.courseDuration+", "+this.gender+", "+this.zone+", "+this.facil+")";
					
					try{
					resultChecker = st.executeUpdate(query1);}
					catch (SQLException e) {
						message=e.toString();
					}

					if (resultChecker != 0) {
						message=resultChecker +" ";
					} else {
						message=resultChecker +" ";
					}
				}
			}
		return message;} 
		
	}
	

