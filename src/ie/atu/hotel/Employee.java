/**
 * Class: B.Sc. in Computing

 * Instructor: Shaun Russell
 * Description: Models an Employee
 * Date: 28/11/2024
 * @author Shaun Russell
 * @version 1.0
**/
package ie.atu.hotel;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


// INHERITANCE - Employee IS-A Person, and CAN-DO Payable methods
//(i)
public class Employee extends Person  implements Serializable, Payable {
    //private Date dob;		// Employee has name&phoneNumber from Person
    //private Date startDate;	// AND dob,startdate,salary, & number	
    private double salary;
    private int number;

	private static int nextNumber=10000;	// static for unique number - starts off at 1
	
	private final double MAX_SALARY = 150000;	

    // Default Constructor
	// Called when object is created like this ==> Employee eObj = new Employee();	
    public Employee(){
    	super();		// NOTE:Not needed
    	//dob=new Date();
    	//startDate=new Date();
 		salary=0.0;
		// Set number to static nextNumber before incrementing nextNumber
 		number=nextNumber++;
    }
	
    // Initialization Constructor
    // Called when object is created like this ==>
    //  Name name=new Name("Mr","Joe","Cole");
    //  Employee e2=new Employee(name,"087 1234567", new Date(25,12,1970),
    //                           new Date(10,11,2009),55000);
    public Employee(Name name, String phoneNo, Date dob,Date startDate, double salary){
          // Call super class constructor - Passing parameters required by Person ONLY!
	   super(name, phoneNo);
	   // And then initialise Employees own instance variables
	   //this.dob=dob;
	   //this.startDate=startDate;	// Set instance variable to parameter
	   this.salary=salary;
	   
		// Set number to static nextNumber before incrementing nextNumber
	   number = nextNumber++;						
	}
    
	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
    public String toString(){
		DecimalFormat df=new DecimalFormat("#.00");
		return number + " " + name + " " + phoneNumber +" £" +  df.format(salary) + ".";
		// OR
		// return number + " " + name + "\t€" +  String.format("%.2f", salary) + ".";			
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(e1.equals(e2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Employee eObject;
		if (obj instanceof Employee)
		   eObject = (Employee)obj;
		else
		   return false;
		   
	    return(this.number==eObject.number);
	}

	// set() and get() methods
	//public void setDOB(Date dob){
		//this.dob=dob;
	//}
	//public Date getDOB(){
		//return dob;
	//}
	//public void setStartDate(Date startDate){
		///this.startDate=startDate;
	//}
	//public Date getStartDate(){
		//return startDate;
	//}	
	public void setSalary(double salary){
		this.salary=salary;
	}
	public double getSalary(){
		return salary;
	}
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	// Method to decrement the employee number
   // public void decrementEmployeeNumber() {
       // if (nextNumber > 10000) { // Prevent going below the initial value
          //  nextNumber--;
        //}
   // }
	
	// read() method
	// ==> Called when you when you read() 
	//     an Employees details from the user... 
	//     e.g. e1.read();	
	/**
   public void read1() {//only for console
		Scanner kb=new Scanner(System.in);
		
		System.out.println("EMPLOYEE DETAILS");
		
		// read name & phoneNumner by calling Person read()
		super.read();
		
		// read dob
		System.out.println("Date of Birth=>");
		dob.read(); 
		
		// read startDate
		System.out.println("Start Date=>");
		startDate.read();

		// read salary
		System.out.print("Salary:");
		this.salary=kb.nextDouble();		
	}
	**/
   //(iii)
   public boolean read() {
	   try {
		    // Options for the dropdown menu
		    String[] choices = {"MR", "MRS", "miss", "Dr"};

		    // Create a JComboBox with the choices
		    JTextField txtEmployeeNumber = new JTextField(String.valueOf(number));
		    JComboBox<String> combobox = new JComboBox<>(choices);
		    JTextField txtFirstName = new JTextField();
		    JTextField txtSurname = new JTextField();
		    JTextField txtPhoneNumber = new JTextField();
		    JTextField txtSalary = new JTextField();

		    Object[] message = {//creates object
		        "Employee Number:", txtEmployeeNumber,
		        "Title", combobox,
		        "First Name:", txtFirstName,
		        "Surname", txtSurname,
		        "Phone Number:", txtPhoneNumber,
		        "Salary:", txtSalary,
		    };
		    //set layout
		    JDialog dialog = new JDialog();
		    dialog.setAlwaysOnTop(true);
		    //display dialog
		    int option = JOptionPane.showConfirmDialog(dialog, message, 
		    	"ENTER EMPLOYEE DETAILS", JOptionPane.OK_CANCEL_OPTION
		    );

		    if (option == JOptionPane.OK_OPTION) {
		        try {
		            // Extract and validate user input
		            String employeeNumber = txtEmployeeNumber.getText();

		            // Ensure required fields are not empty
		            if (txtFirstName.getText().trim().isEmpty() || txtSurname.getText().trim().isEmpty()) {
		                throw new IllegalArgumentException("Name cannot be empty.");
		            }

		            this.name.setTitle((String) combobox.getSelectedItem());
		            this.name.setFirstName(txtFirstName.getText());
		            this.name.setSurname(txtSurname.getText());
		            this.phoneNumber = txtPhoneNumber.getText();

		            // Validate that salary is an integer
		            try {
		                this.salary = Double.parseDouble(txtSalary.getText());
		            } catch (NumberFormatException e) {
		                throw new IllegalArgumentException("Salary must be  valid .");
		            }
		            // Show a success message when OK is pressed and input is valid
		            JOptionPane.showMessageDialog(
		                null, 
		                "Employee details saved successfully!", 
		                "Success", 
		                JOptionPane.INFORMATION_MESSAGE
		            );

		            return true;
		        } catch (IllegalArgumentException e) {
		            // Handle  errors
		            JOptionPane.showMessageDialog(
		                null, 
		                "Input Error: " + e.getMessage(), "Error", 
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    } else { // CANCEL_OPTION
		    	// Show a success message when OK is pressed and input is invalid
	            JOptionPane.showMessageDialog(
	                null, 
	                "Employee details not saved", 
	                "Fail", 
	                JOptionPane.INFORMATION_MESSAGE
	            );
		        return false;
		    }
		} catch (Exception e) {
		    // Handle errors 
		    JOptionPane.showMessageDialog(
		        null, 
		        "An unexpected error " + e.getMessage(), "Error", 
		        JOptionPane.ERROR_MESSAGE
		    );
		    return false;
		}
	   return false;
   }

	
	@Override
	public double calculatePay(double taxPercentage) {
		// return the monthly pay as salary/12 less taxPercentage
		double pay=salary/12;
		pay -= (pay * (taxPercentage/100));
		return pay;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		// add incrementAmount to, and return the new salary
		// salary should not go over a maximum salary of €150,000
		salary += incrementAmount;
		
		if(salary > MAX_SALARY)
			salary = MAX_SALARY;
		
		return salary;
	}
}
