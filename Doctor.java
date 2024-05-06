package hmSystem;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	
	private Connection connection;
	
//	constructor
	public Doctor(Connection connection) {
		this.connection = connection;
	}
	
	
	public void viewDoctors() {
		String query = "select * from doctors";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Doctors: ");
			System.out.println("+-----------+----------------+--------------------+");
			System.out.println("| Doctor Id | Name           | Specialization     |");
			System.out.println("+-----------+----------------+--------------------+");
			
//			this method will set a pointer and print the value line by line
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String specialization = resultSet.getString("specialization");
				System.out.printf("| %-10s| %-15s| %-18s |\n",id,name,specialization);	
				System.out.println("+-----------+----------------+--------------------+");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Now we check that patient exist in or data or not by passing ID
	public boolean getDoctorById(int id) {
		String query ="SELECT * FROM doctors WHERE id=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
//		true hoga to upr wala execute hojaaega vrna false he rhega
		return false;
		
	}
}
