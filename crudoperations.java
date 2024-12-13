import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class crudoperations {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/market";
	private static final String user = "root";
	private static final String pass = "root";
	private static Connection conn;
	private static PreparedStatement pmst;

	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		int ch;
		do {
			getdetails();
			ch = Integer.parseInt(scr.next());
			switch (ch) {
			case 1:
				login();
				break;
			case 2:
				registration();
				break;
			case 3:
				adduser();
				break;
			case 4:
				deleteuser();
				break;
			case 5:
				modifyuserdetails();
				break;
			case 6:
				getalluserdetails();
				break;
			case 7:
				getuserdetailsbyemail();
				break;
			case 8:
				System.exit(ch);
				break;
			default:
				System.out.println("Invalid Operation.");
				break;
			}
		} while (ch > 0);

	}

	private static void getuserdetailsbyemail() {
		try {
			Scanner scr = new Scanner(System.in);

			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);

			System.out.println("Enter TableName:");
			String sql = "select * from " + scr.next() + " where email=?";
			System.out.println("Enter Email:");
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, scr.next());

			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("id: " + rs.getInt("id"));
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Email: " + rs.getString("email"));
				System.out.println("Password: " + rs.getString("password"));
			}

			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getalluserdetails() {
		try {
			Scanner scr = new Scanner(System.in);

			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);

			System.out.println("Enter TableName:");
			String sql = "select * from " + scr.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("id: " + rs.getInt("id"));
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Email: " + rs.getString("email"));
				System.out.println("Password: " + rs.getString("password"));
			}

			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void modifyuserdetails() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);

			System.out.println("Enter Tablename:");
			String sql = "Update " + scr.next() + " set id=?,name=?,email=?,password=? where id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Id:");
			pmst.setInt(1, scr.nextInt());
			System.out.println("Enter Name:");
			pmst.setString(2, scr.next());
			System.out.println("Enter Email:");
			pmst.setString(3, scr.next());
			System.out.println("Enter Password:");
			pmst.setString(4, scr.next());
			System.out.println("Provide Id:");
			pmst.setInt(5, scr.nextInt());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Successfully Updated.");
			} else {
				System.out.println("Not Updated");
			}
			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteuser() {
		try {
			Scanner scr = new Scanner(System.in);

			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);

			System.out.println("Enter TableName:");
			String sql = "delete from " + scr.next() + " where id=?";

			pmst = conn.prepareStatement(sql);

			System.out.println("Enter Id");
			pmst.setInt(1, scr.nextInt());

			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("Successfully Deleted.");
			} else {
				System.out.println("Not Deleted.");
			}

			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void adduser() {
		try {
			Scanner scr = new Scanner(System.in);

			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);

			String sql = "insert into reg1(name,email,password)values(?,?,?)";

			pmst = conn.prepareStatement(sql);

			System.out.println("Enter Name");
			pmst.setString(1, scr.next());
			System.out.println("Enter Email");
			pmst.setString(2, scr.next());
			System.out.println("Enter Password");
			pmst.setString(3, scr.next());

			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Inserted.");
			} else {
				System.out.println("Not Inserted.");
			}

			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void registration() {

	}

	private static void login() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Enter Username");
			String email = scr.next();
			System.out.println("Enter password");
			String password = scr.next();
			String sql = "select * from reg1 where email=? and password=?";
			pmst = conn.prepareStatement(sql);

			pmst.setString(1, email);
			pmst.setString(2, password);

			ResultSet rs = pmst.executeQuery();
			if (rs.next()) {
				System.out.println("Successfully Login.");
			} else {
				System.out.println("Invalid Login.");

			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void getdetails() {
		System.out.println("Market Operations.");
		System.out.println("_________________________");
		System.out.println("Choose an Operation.");
		System.out.println("\t 1.login");// get
		System.out.println("\t 2.registration");// sent
		System.out.println("\t 3.adduser");// send
		System.out.println("\t 4.deleteuser");// get
		System.out.println("\t 5.modifyuserdetails");// sent
		System.out.println("\t 6.getalluserdetails");// get
		System.out.println("\t 7.getuserdetailsbyemail");// get
		System.out.println("\t 8.Exit");
		System.out.println("_________________________");

	}
}
