package Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection connection;
	private Scanner scanner;
	
	
	public Patient(Connection connection, Scanner scanner) {
		this.connection=connection;
		this.scanner=scanner;
	}
	
	
	public void register(){
		scanner.nextLine();
		System.out.println("--> YOUR FULL NAME OF PATIENT :");
		String FULL_NAME= scanner.nextLine();
		System.out.println("--> EMAIL :");
		String EMAIL =scanner.nextLine();
		System.out.println("--> ENTER YOUR PASSWORD : ");
		String PASSWORD=scanner.nextLine();
		System.out.println("--> ENTER YOUR PHONE NUMBER : ");
		String PHONE_NUMBER = scanner.nextLine();
		
		if(Patient_exist(EMAIL)) {
			System.out.println("User already exists for this EMAIL address!!!....");
			return;
		}
		String register_query= "INSERT INTO PATIENT(FULL_NAME,EMAIL,PASSWORD,PHONE_NUMBER)VALUES(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(register_query);
			preparedStatement.setString(1, FULL_NAME);
			preparedStatement.setString(2, EMAIL);
			preparedStatement.setString(3, PASSWORD);
			preparedStatement.setString(4, PHONE_NUMBER);	
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("REGISTRATION SUCCESSFULL !!.....");
			}else {
				System.out.println("REGISTRATION FAILED ENTRE YOUR DETAILS CORRECTLY !!...");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public String login() {
		scanner.nextLine();
		System.out.println("--> EMAIL : ");
		String EMAIL= scanner.nextLine();
		System.out.println("--> PASSWORD : ");
		String PASSWORD = scanner.nextLine();
		String login_query= "SELECT * FROM PATIENT WHERE EMAIL = ? AND PASSWORD = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(login_query);
			preparedStatement.setString(1, EMAIL);
			preparedStatement.setString(2, PASSWORD);
			ResultSet resultSet= preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return EMAIL;
			}else {
				//return null;
				System.out.println("INVALID USER or PASSWORD !!...");

			}
		}catch(SQLException e) {
			System.out.println(" INVALID EMAIL or PASSWORD !!.....");
		}return null;
	}

	public boolean Patient_exist(String EMAIL) {
		String query = "SELECT * FROM PATIENT WHERE EMAIL = ?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1,EMAIL);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}


}

	

