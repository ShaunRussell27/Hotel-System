/**
 * Class: B.Sc. in Computing
 * Instructor: Shaun Russell
 * Description: Payable Interface
 * Date: 28/11/2024
 * @author Shaun russell
 * @version 1.0
**/
package ie.atu.hotel;

public interface Payable {
	public abstract double calculatePay(double taxPercentage);
	// Don't have to put in public abstract
	double incrementSalary(double incrementAmount);
}

// A Java interface can contain only abstract 
// methods, i.e. methods without a body