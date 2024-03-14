package Appointment;

import java.sql.*;
import java.util.Scanner;

public class Hospital {
	private Connection connection;
	private Scanner scanner;
	public Hospital(Connection connection,Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
// CREATING THE PATIENT TABLE 
	
	public long open_account(String email) {
		if(!Patient_exist(email)) {
			String open_account_query = "INSERT INTO HOSPITAL (FULL_NAME,EMAIL,AGE,PHONE_NUMBER,MEDICAL_CONDITION,PATIENT_ID,ADDRESS,PINCODE) VALUES(?,?,?,?,?,?,?,?)";
			scanner.nextLine();
			System.out.println("--> ENTER YOUR FULL NAME : ");
			String FULL_NAME= scanner.nextLine();
			System.out.println("--> ENTER YOUR EMAIL : ");
			String EMAIL = scanner.nextLine();
			System.out.println("--> ENTER YOUR AGE : ");
			int AGE =scanner.nextInt();
			scanner.nextLine();
			System.out.println("--> ENTER YOUR PHONE NUMBER : ");
			long PHONE_NUMBER = scanner.nextLong();
			scanner.nextLine();
			System.out.println("--> WHAT IS YOUR MEDICAL DISORDER : ");
			String MEDICAL_CONDITION = scanner.nextLine();
			System.out.println("--> ENTER YOUR ADDRESS : ");
			String ADDRESS=scanner.nextLine();
			System.out.println("--> ENTER YOUR PINCODE : ");
			int PINCODE=scanner.nextInt();
					
			try {
				long Patient_ID = generatePatient_ID();
				PreparedStatement preparedStatement =  connection.prepareStatement(open_account_query);
				preparedStatement.setString(1,FULL_NAME );
				preparedStatement.setString(2,EMAIL );
				preparedStatement.setInt(3,AGE );
				preparedStatement.setLong(4,PHONE_NUMBER );
				preparedStatement.setString(5,MEDICAL_CONDITION );
				preparedStatement.setLong(6,Patient_ID );
				preparedStatement.setString(7, ADDRESS);
				preparedStatement.setInt(8, PINCODE);
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected > 0) {
					return Patient_ID;
				}else {
					throw new RuntimeException("ACCOUNT CREATION FAILED!!... PLEASE ENTRE THE VALID INFORMATION..");
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		}
		throw new RuntimeException (" ACCOUNT ALREADY EXIST!!...");
	}

//---------------------------------------------------------------------------------------------------------------------	
	public long getPatient_ID(String EMAIL) {
		String query= " SELECT PATIENT_ID FROM HOSPITAL WHERE EMAIL = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, EMAIL);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getLong("PATIENT_ID");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("EMAIL doesn't Exist!!...");
	} 
//-----------------------------------------------------------------------------------------------------------------
//GENERATIN PATIENT ID
	
	private long generatePatient_ID() {
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery("SELECT PATIENT_ID FROM HOSPITAL ORDER BY PATIENT_ID DESC LIMIT 1");
			if(resultSet.next()) {
				long last_Patient_ID = resultSet.getLong("PATIENT_ID");
				return last_Patient_ID+1;
			}else {
				return 1000;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 1000;
	}
		
//--------------------------------------------------------------------------------------------------------------------------------
// CHECKING FOR PATIENT EXIST
		
		public boolean Patient_exist(String EMAIL) {
		String query = " SELECT PATIENT_ID FROM HOSPITAL WHERE EMAIL = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,  EMAIL);
			ResultSet resultSet = preparedStatement.executeQuery();
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
//----------------------------------------------------------------------------------------------------------------------------

// CREATING THE DOCTOR TABLE
		
		public String open_account_doctor(String Medical_ID) {
			if(!Doctor_exist(Medical_ID)) {
				String open_account_doctor_query = "INSERT INTO HOSPITAL_2 (FULL_NAME,EMAIL,MEDICAL_ID,SPECIALIZATION_IN,AGE,PHONE_NUMBER,HOSPITAL_NAME,PLACE,PINCODE)VALUES(?,?,?,?,?,?,?,?,?)";
				scanner.nextLine();
				System.out.println("--> ENTER YOUR FULL NAME : ");
				String FULL_NAME= scanner.nextLine();
				System.out.println("--> ENTER YOUR EMAIL : ");
				String EMAIL = scanner.nextLine();
				System.out.println("--> ENTER YOUR MEDICAL ID");
				String MEDICAL_ID= scanner.nextLine();
				System.out.println("--> SPECIALIZATION : ");
				String SPECIALIZATION_IN = scanner.nextLine();
				System.out.println("--> ENTER YOUR AGE : ");
				int AGE =scanner.nextInt();
				scanner.nextLine();
				System.out.println("--> ENTER YOUR PHONE NUMBER : ");
				long PHONE_NUMBER = scanner.nextLong();
				scanner.nextLine();
				System.out.println("--> ENTER YOUR HOSPITAL NAME : ");
				String HOSPITAL_NAME= scanner.nextLine();
				System.out.println("--> ENTER YOUR PLACE");
				String PLACE= scanner.nextLine();
				System.out.println("--> ENTER YOUR PINCODE");
				int PINCODE= scanner.nextInt();
				
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(open_account_doctor_query);
					preparedStatement.setString(1,FULL_NAME );
					preparedStatement.setString(2,EMAIL );
					preparedStatement.setString(3,MEDICAL_ID);
					preparedStatement.setString(4,SPECIALIZATION_IN );
					preparedStatement.setInt(5,AGE );
					preparedStatement.setLong(6,PHONE_NUMBER );
					preparedStatement.setString(7, HOSPITAL_NAME);
					preparedStatement.setString(8, PLACE);
					preparedStatement.setInt(9, PINCODE);
					int rowsAffected = preparedStatement.executeUpdate();
					if(rowsAffected > 0) {
						System.out.println("ACCOUNT CREATED SUCCESSFULLY!!...");
					}else {
						throw new RuntimeException("ACCOUNT CREATION FAILED!!... PLEASE ENTRE THE VALID INFORMATION..");
					}

		
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
		
			throw new RuntimeException (" ACCOUNT ALREADY EXIST!!...");
		}
		
//----------------------------------------------------------------------------------------------------------------------------
// CHECKING FOR DOCTOR EXIST
		
		public boolean Doctor_exist(String Medical_ID) {
		String query_doctor = " SELECT MEDICAL_ID FROM HOSPITAL_2 WHERE  EMAIL = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query_doctor);
			preparedStatement.setString(1,Medical_ID);
			ResultSet resultSet = preparedStatement.executeQuery();
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
//------------------------------------------------------------------------------------------------------------------------	
	public void book_appoinment(String EMAIL) {
		
		System.out.println("book appoinment is need to be created");
	}

	

	

}
