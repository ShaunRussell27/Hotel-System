package ie.atu.serialize;

/*
 * Author: Shaun Russell
 * Date Created:  November 27, 2024]
 * Description: This code handles the serialization.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ie.atu.hotel.Employee;
import ie.atu.hotel.Name;

public class EmployeeSerializer {
	private ArrayList<Employee> employees;
	
	private final String FILENAME = "employees.bin";	
	private File employeesFile;	
	
	// Default Constructor
	public EmployeeSerializer(){
		// Construct EmployeeList ArrayList
		employees = new ArrayList<Employee>();

		// TODO Construct employeesFile and physically create the File
		//(ii)
		employeesFile = new File(FILENAME);
		
		try {
            // Create the file if it does not already exist
            if (employeesFile.createNewFile()) {
                System.out.println("File created: " + employeesFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            // Handle any exceptions that occur while creating the file
            System.err.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
		

	}	
	/////////////////////////////////////////////////////////////
	// Method Name : add()								              //
	// Return Type : void								              //
	// Parameters : None								                 //
	// Purpose : Reads one Employee record from the user       //
	//           and adds it to the ArrayList called employees //
	/////////////////////////////////////////////////////////////
	public void add(){ 
	    // Create an Employee object
		 Employee employee = new Employee();
 
       // TODO - Update add() so it checks if Cancel was clicked when reading Employee
       //(iii)
		 // Read its details
		 if(employee.read()==true)
			 // And add it to the employees ArrayList
			 employees.add(employee);
		 else;
			 //increment the static
		 		//employee.decrementEmployeeNumber();
		 		//prevents file from opening
		 	
	}
	
	
	///////////////////////////////////////////////////////
	// Method Name : list()						              //
	// Return Type : void					   	           //
	// Parameters : None						                 //
	// Purpose : Lists all Employee records in employees //
	///////////////////////////////////////////////////////		
	public void list(){
		String employeesToList="";

		if(!employees.isEmpty()) {
			// for every Employee object in employees
			for(Employee tmpEmployee : employees) {
				// add it to employeesToList and
				employeesToList+=tmpEmployee;
				// add a newline
				employeesToList+="\n";
			}
			
			// Display employeesToList in a messageDialog
		   JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
		}
		else
			// Display "No Employees stored..." in messageDialog
		   JOptionPane.showMessageDialog(null, "No Employees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
	}	

	////////////////////////////////////////////////////////////////
	// Method Name : view()									              //
	// Return Type : Employee								              //
	// Parameters : None								                    //
	// Purpose : Displays the required Employee record on screen  //
	//         : And returns it, or null if not found             //   
	////////////////////////////////////////////////////////////////
	//(v)
	public Employee view(){
		 // TODO - Write the code for view()
	    //JOptionPane.showMessageDialog(null, "view() method must be coded!", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);			
		Employee foundEmployee=null;
		try {
			String input =JOptionPane.showInputDialog(null,"Enter the employee number:", "View Employee", JOptionPane.PLAIN_MESSAGE);
			// Check if the user pressed cancel or entered nothing
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No input provided. Exiting ","error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
		
			int employeenumber= Integer.parseInt(input);//convert to integer
			//search for employee
			for(Employee employee:employees) {
				
				if(employee.getNumber()== employeenumber) {
					foundEmployee=employee;
					break;
				}
			}
			// Display  result or no result
            if (foundEmployee != null) {
                JOptionPane.showMessageDialog(null, "Employee found: \n" + foundEmployee.toString(), "Employee Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Employee with number " + employeenumber + " not found.", "Employee Not Found", JOptionPane.WARNING_MESSAGE);
            }
            //catches errors
		} catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input: Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } 
		
	    return foundEmployee;
		
	}
	
	///////////////////////////////////////////////////////////////////
	// Method Name : delete()							        	           //
	// Return Type : void								        	           //
	// Parameters : None									                    //
	// Purpose : Deletes the required Employee record from employees //
	///////////////////////////////////////////////////////////////////	
	//(vii)
	public void delete(){
		 // TODO - Write the code for delete()
	    //JOptionPane.showMessageDialog(null, "delete() method must be coded!", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);	
		try {
            // Prompt the user for the employee number
            String input = JOptionPane.showInputDialog(null, "Enter the employee number you want to to delete:", "Delete employee", JOptionPane.PLAIN_MESSAGE);

            // Check if the user pressed "Cancel" or enter nothing
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nothin was inputed. Exiting delete.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int empNumber = Integer.parseInt(input); // Convert  to integer
            Employee employeeToDelete = null;

            // Search for the employee 
            for (Employee emp : employees) {
                if (emp.getNumber() == empNumber) {
                    employeeToDelete = emp;
                    break;
                }
            }

            // If employee is found, confirm and delete
            if (employeeToDelete != null) {
                // Confirm deletion
                int confirmation = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to delete this employee?\n" + employeeToDelete, 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION);
                //displayed if confirmed or not
                if (confirmation == JOptionPane.YES_OPTION) {
                    employees.remove(employeeToDelete);
                    JOptionPane.showMessageDialog(null, "deletetion successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion canceled.", "Cancel", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Employee  number " + empNumber + " not found.", "Employee Not Found", JOptionPane.WARNING_MESSAGE);
            }//catch exceptions
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input, Please enter  valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } 
    }

	///////////////////////////////////////////////////////////////
	// Method Name : edit()			  					                //
	// Return Type : void								    	          //
	// Parameters : None								     	             //
	// Purpose : Edits the required Employee record in employees //
	///////////////////////////////////////////////////////////////	
	//(vi)
	public void edit(){
		 // TODO - Write the code for edit()
	    //JOptionPane.showMessageDialog(null, "edit() method must be coded!", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);			
		Employee emp=view();//use the view method as output is similiar
		if(emp!=null) {
			emp.read();
		}

           
	}
	
	// This method will serialize the employees ArrayList when called, 
	// i.e. it will write employees to a file called employees.bin
	//(iv)
	public void serializeEmployees(){
		 // TODO - Write the code for serializeEmployees()
		 //JOptionPane.showMessageDialog(null, "You must write the code for the serializeEmployees() method.", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);
		//(iv)
		 ObjectOutputStream os=null;
		 try {
			 	//serializing arraylist
			 	FileOutputStream fos =new FileOutputStream(employeesFile);
			 
			 	os = new ObjectOutputStream(fos);
			 	os.writeObject(employees);
		 }
		 catch(FileNotFoundException fNFE) {
			 System.out.println("Cannot open file " + employeesFile.getName() + ".");
		}
		catch(IOException ioE){
			System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		finally {
				try {
					os.close();
				}
				catch(IOException ioE) {
					System.out.print(ioE.getMessage());
				}
				
			}
		 
		 
	}

	// This method will deserialize the Employees ArrayList when called, 
	// i.e. it will restore the employees ArrayList from the file employees.bin
	public void deserializeEmployees(){
		 ObjectInputStream is=null;
		
		 try {
			 // Deserialize the ArrayList...
			 FileInputStream fileStream = new FileInputStream(employeesFile);
				
			 is = new ObjectInputStream(fileStream);
						
			 employees = (ArrayList<Employee>)is.readObject();
          
          is.close();
          
          //last part is in here(viii)
          //Employee.setNextNumber(number +1)
		}
		catch(FileNotFoundException fNFE){
			 System.out.println("Cannot open file " + employeesFile.getName() + ".");
		}
		catch(IOException ioE){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		catch(Exception e){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
	}
}
