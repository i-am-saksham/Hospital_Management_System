package hmSystem;
import java.sql.*;

public class Jdbcconect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "jdbc:mysql://localhost:3306/hospital";
		String user = "root";  
		String password = "root";
		
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
                // Do your database operations here
                // Remember to close the connection when done
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
	}

}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class MySQLConnectionExample {
//    public static void main(String[] args) {
//        // JDBC URL, username, and password of MySQL server
//        String url = "jdbc:mysql://localhost:3306/mydatabase";
//        String user = "username";
//        String password = "password";
//        
//        try {
//            // Establishing connection
//            Connection connection = DriverManager.getConnection(url, user, password);
//            if (connection != null) {
//                System.out.println("Connected to the database!");
//                // Do your database operations here
//                // Remember to close the connection when done
//                connection.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Connection failed!");
//            e.printStackTrace();
//        }
//    }
//}
