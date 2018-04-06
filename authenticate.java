package tpo;

import java.sql.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
@Path("/authenticate")
public class authenticate {	
	@Path("/check")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String conf(@QueryParam("type") String type,@QueryParam("user") String name,@QueryParam("password") String pass) {
		String n = "null",m = "null", d = "null";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpo","tpo","tpo");
			Statement state = conn.createStatement();
			ResultSet myres = state.executeQuery("SELECT * FROM USERS");
			while (myres.next()) {
				n = myres.getString("type");
				m = myres.getString("username");
				d = myres.getString("password");
				if(n.equals(type))
					if(m.equals(name))
						if (d.equals(pass)) {
							String res = "<html>" + "<head>" + "<title>USer Details here</title>" + "</head>" + "<body>" + 
							"<p>Logged In as : "+ type + "</p>"+
							"<p>UserName : "+name + "</p>"+
							"<p>PassWord : " + pass + "</p>"+
							"</body>" + 
							"</html>";
							String resi = "<!DOCTYPE html>\r\n" + 
									"<html>\r\n" + 
									"<head>\r\n" + 
									"<meta charset=\"ISO-8859-1\">\r\n" + 
									"<title>Menu</title>\r\n" + 
									"<style>\r\n" + 
									".content {\r\n" + 
									"    max-width: 500px;\r\n" + 
									"    margin: auto;\r\n" + 
									"    background: white;\r\n" + 
									"    padding: 10px;\r\n" + 
									"}\r\n" + 
									"</style>\r\n" + 
									"</head>\r\n" + 
									"<body class=\"content\">\r\n" + 
									"	<h1>Welcome To TPO</h1>\r\n" + 
									"	<ul>\r\n" + 
									"		<li><a href=\"\">Profile</a></li>\r\n" + 
									"		<li><a href=\"\">Notifications</a></li>\r\n" + 
									"		<li><a href=\"\">Apply For Companies</a></li>\r\n" + 
									"		<li><a href=\"\">Placements</a></li>\r\n" + 
									"	</ul>\r\n" + 
									"\r\n" + 
									"</body>\r\n" + 
									"</html>"; 
							return resi;
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		String r = "<html>"+"<head>"+"<title>"+"authentication"+"</title>"+"</head>"+"<body><h1>"+"Sorry, We dont have your accont "+"</h1></body>"+"</html>";
		return r;
	}
	@Path("/register")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String create(@QueryParam("type") String type,@QueryParam("name") String name,@QueryParam("user") String user,@QueryParam("password") String pass)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpo","tpo","tpo");
			//Statement state = conn.createStatement();
			String sql = "INSERT INTO USERS"+"(type,name,username,password)"+"values(?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, type);
			state.setString(2, name);
			state.setString(3, user);
			state.setString(4, pass);
			state.executeUpdate();
			String str = "You have Successfully created account";
			return str;
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		String r = "<html>"+"<head>"+"<title>"+"authentication"+"</title>"+"</head>"+"<body><h1>"+"Sorry, We can't have your accont "+"</h1></body>"+"</html>";
		return r;
	}
}
	
