package Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	private Scanner scanner;
	
	public Doctor(Connection connection, Scanner scanner) {
		this.connection=connection;	
		this.scanner=scanner;
	}
	
	
	public  void register_doctor() {
		scanner.nextLine();
		System.out.println("--> YOUR FULL NAME OF DOCTOR : ");
		String Full_Name=scanner.nextLine();
		System.out.println("--> ENTER YOUR MEDICAL ID NUMBER : "); // THIS WILL SPERATED DOCTORS FROM PATIENT BECAUSE PATIENT DOESNT HAVE  MEDICAL ID 
		String Medical_ID=scanner.nextLine();
		System.out.println("--> EMAIL : ");
		String Email=scanner.nextLine();
		System.out.println("--> ENTER YOUR PASSWORD : ");
		String Password = scanner.nextLine();
		
		if(Doctor_exist(Medical_ID)) {
			System.out.println("User already exists for this MEDICAL ID !!...");
			return;
		}
		String register_doctor_query="INSERT INTO DOCTORS (Full_Name,Medical_ID,Email,Password) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(register_doctor_query);
			preparedStatement.setString(1,Full_Name);
			preparedStatement.setString(2,Medical_ID);
			preparedStatement.setString(3,Email);
			preparedStatement.setString(4,Password);
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("REGISTRATION SUCCESSFULL!!...");
			}else {
				System.out.println("REGISTRATION FAIED ENTRE YOUR DETAILS CORRECTLY");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public String login_doctor() {
		scanner.nextLine();
		System.out.println("--> ENTER YOUR MEDICAL ID : ");
		String Medical_ID=scanner.nextLine();
		System.out.println("--> ENTER YOUR PASSWORD : ");
		String Password=scanner.nextLine();
		String login_doctor_query = "SELECT * FROM DOCTORS WHERE MEDICAL_ID = ? AND PASSWORD = ? ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(login_doctor_query);
			preparedStatement.setString(1,Medical_ID); 
			preparedStatement.setString(2,Password);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return Medical_ID;
			}else {
				//return null;
				System.out.println("INVALID USER or PASSWORD !!...");
			}
		}catch(SQLException e) {
			System.out.println("INVALID USER or PASSWORD !!...");
		}return null;
		
	}
	
	public boolean Doctor_exist(String Medical_ID) {
		String query = "SELECT * FROM DOCTORS WHERE MEDICAL_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,Medical_ID);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	return false;
	}
}
