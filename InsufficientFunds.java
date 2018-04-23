/*
 * File: InsufficientFunds.java
 * Author: Carrie Miles
 * Date: 4/5/18
 * Purpose: Create a user defined checked exception for insufficient funds available
 */
		
package ATMMachine;



public class InsufficientFunds extends Exception {
	//default constructor
	public InsufficientFunds() {
	}
	//constructor that accepts a message
	public InsufficientFunds(String message) {
		super(message);
	}
	
}
