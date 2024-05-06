package hmSystem;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Patient {

//	data members
//	connection is an interface
//	for creating connection to the backend
	private Connection connection;
//	to get the input from the user
	private Scanner scanner;
	
//	constructor
	public Patient(Connection connection, Scanner scanner) {
//		for connection to database
		this.connection = connection;
//		to get input for the user
		this.scanner = scanner;
	}
	
//	method
	public void addPatient() {
//		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter Patient Name: ");	
		String name;
		name=scanner.next();
		
		System.out.println("Enter Patient Age: ");
		int age;
		age=scanner.nextInt();
		
		System.out.println("Enter Patient Gender: ");
		String gender;
		gender=scanner.next();
		
//		we have to handle SQLException everytime when connect with database
		try {
			String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			Add argument
			preparedStatement.setString(1,name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
//			execute the query
//			preparedStatement.executeUpdate();	
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("Patient added successfully!");
			}
			else {
				System.out.println("Failed to add Patient");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewPatient() {
		String query = "select * from patients";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Patients: ");
			System.out.println("+------------+-----------+---------+-------------+");
			System.out.println("| Patient Id | Name      | Age     | Gender      |");
			System.out.println("+------------+-----------+---------+-------------+");
			
//			this method will set a pointer and print the value line by line
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				System.out.printf("|%-12s|%-11s|%-9s|%-13s|\n",id,name,age,gender);	
				System.out.println("+------------+-----------+---------+-------------+");
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Now we check that patient exist in or data or not by passing ID
	public boolean getPatientById(int id) {
		String query ="SELECT * FROM patients WHERE id=?";
		
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
