package Appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Login_Page {
	
	private static final String url = "jdbc:mysql://localhost:3306/doctors_appoinment";
	private static final String username ="root";
	private static final String password ="Jibbu@01";
	
	
	public static void main(String[] args)throws ClassNotFoundException,SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
			Scanner scanner =new Scanner(System.in);
			Patient Patient =new Patient(connection,scanner);
			Doctor Doctor = new Doctor(connection,scanner);
			Hospital hospital = new Hospital(connection,scanner);
			
			String EMAIL;  
			long Patient_ID;
			String Medical_ID;
			
			
			// front PAGE TO LOGIN AND FOR SIGNUP
			
			while(true) {
				System.out.println("-*---*---- WELCOME TO DOCTOR'S APPOINTMENT ---*----*-");
				System.out.println();
				System.out.println("A. REGISTER");
				System.out.println("B. LOGIN");
				System.out.println("C. EXIT");
				System.out.println("ENTER YOUR CHOICE : ");
				String st=scanner.nextLine();
				
				// SIGN-UP FOR PATIENT
				
				
				
				switch(st) {
				case "A":
										
					
					System.out.println("PRESS 1 FOR PATIENT SIGN-UP :  ");
				    System.out.println("PRESS 2 FOR DOCTOR SIGN-UP : ");	
					 int choice1 =scanner.nextInt();
					switch( choice1) {
					case 1:					
					Patient.register();
					break;
					
				   case 2:
					Doctor.register_doctor();
					}
				
				
				case "B":
					 System.out.println("PRESS 1 FOR PATIENT LOGIN : ");
					 System.out.println("PRESS 2 FOR DOCTOR LOGIN : ");

					int choice2=scanner.nextInt();
					switch(choice2){  //CCHOICE 2 START HERE
					case 1: 
							
					EMAIL =Patient.login();
					if(EMAIL != null) {
						System.out.println();
						System.out.println("USER LOGGED IN SUCCESSFULLY!!...");
						if(!hospital.Patient_exist(EMAIL)) {
							System.out.println();
							System.out.println("1. FILL THE NEW PATIENT APPOINTMENT ACCOUNT ");
							if(scanner.nextInt() == 1) {
								Patient_ID = hospital.open_account(EMAIL);
								System.out.println("NEW APPOINTMENT ACCOUNT CREATED SUCESSFULLY !!...");
				                System.out.println("YOUR PATIENT ID IS : "+ Patient_ID);
							}
						else {
								break;
							}
						}
					
					
						Patient_ID = hospital.getPatient_ID(EMAIL);
					int choice3 = 0;
					while(choice3 != 3) {
						System.out.println();
						System.out.println("1. BOOK FOR AN APPOINTMENT");
						System.out.println("2. LOG-OUT !!...");
						System.out.println("3. DELETE ACCOUNT");
						choice3 = scanner.nextInt();
						switch(choice3) {
						case 1 :
							hospital.book_appoinment(EMAIL);
						case 2:
							break;
							
						default:
							System.out.println(EMAIL);
							break;
					}
					
				 }
				
					}  else {
			System.out.println("INCORRECT EMAIL OR PASSWORD !!...");
			}
		//---------------------------------------------------------------------------------------------------------------			
					case 2:
						Medical_ID=Doctor.login_doctor();
						if(Medical_ID != null) {
							System.out.println();
							System.out.println("USER LOGGED IN SUCCESSFULLY..");
							if(!hospital.Doctor_exist(Medical_ID)) {
								System.out.println();
								System.out.println("1. FILL THE NEW DOCTOR ACCOUNT");
								hospital.open_account_doctor(Medical_ID);
							//	if(scanner.nextInt() == 1) {
							//		Medical_ID = hospital.open_account_doctor(Medical_ID);
								}
								else {
									break;
								}
							} 
					//	}
		} // CHOICE 2  ENDS HERE
					
	//------------------------------------------------------------------------------------------
				case "C":
				System.out.println("THANK YOU FOR USING DOCTOR'S APPOINTMENT APP!!!.....");
				System.out.println("Exiting DOCTOR'S APPOINMENT!!!....");
				return; 
				
			default:
				System.out.println("Enter valid Choice");
				break;
			}
				
			}	
	}	catch(SQLException e) {
		e.printStackTrace();

		}
}
}



