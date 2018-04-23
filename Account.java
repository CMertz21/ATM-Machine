/*
 * File: Account.java
 * Author: Carrie Miles 
 * Date: 4/5/18
 * Purpose: Constructors for Account objects and methods to handle Account events from GUI buttons
 */
package ATMMachine;

import javax.swing.JOptionPane;

public class Account extends MachineGUI {
	
private final double SERVICE_CHARGE =1.50;
private double balance =0;


//Default Constructor
public Account () {
	balance = 0;
}
//Parameterized Constructors
public Account(double i) {
	this.balance = i;
}
public Account(double i, int entry) {
		this.balance=i;
		this.entry = entry;
		}
public Account(double i, int entry, int count) {
	this.balance = i;
	this.entry = entry;
	this.count = count;
}

//Setter Methods
public void setBalance() {
	balance = 0;
}
public void setBalance(double i) {
	balance = i;
}

	

//Getter Methods
public double getBalance () {
	return this.balance;
}
public int getCount() {
	return count;
}
//Perform button functions from Machine GUI
//Withdraw Funds
public double doWithdrawl() {
	
	
		double new1Bal = balance - entry;
		
		//Service Fee charged after the 4th withdrawal
			if(count>4) {
				JOptionPane.showMessageDialog(withdrawl, "A Service Charge of $1.50 will be charged to this and every continuing withdrawl during this session");
				new1Bal= new1Bal-SERVICE_CHARGE;
			}
		
			if(new1Bal<0) {
				//Insufficient Funds Exception
				try {
					throw new InsufficientFunds();
				} catch (InsufficientFunds e) {
					JOptionPane.showMessageDialog(null, "Insufficient Funds. Closing Application. Goodbye.");
					System.exit(0);
				}
			}
		//change the actual values only after all checks have been made. 
		balance=new1Bal;
		JOptionPane.showMessageDialog(checking, "Withdrawal of: " +  entry + " New Balance is: " + balance  );
	return balance;

}//end withdrawal

//Deposit Funds
public double doDeposit() {
	balance= balance + entry;
	JOptionPane.showMessageDialog(checking, "Deposit of: " + entry + " New Balance is: " + balance);
	return balance;
	
}// end deposit

//Transfer Funds from an account object
public double doTransferFrom(Account from) {
	  double newFromBal = from.getBalance() - entry;
		 if(newFromBal<0) {
				//Insufficient Funds Exception
				try {
					throw new InsufficientFunds();
				} catch (InsufficientFunds e) {
					JOptionPane.showMessageDialog(null, "Insufficient Funds. Closing Application. Goodbye.");
					System.exit(0);
				}
			
		 	}
		 //change the actual values only after all checks have been made. 
		 from.setBalance(newFromBal);	 
		 return  newFromBal;
	}
//Transfer funds to an Account object
public double doTransferTo(Account to) {
	double newToBal = to.getBalance() +entry;
	to.setBalance(newToBal);
	
	return newToBal;
	
}

//perform a count for number of withdrawals
public int doCount() {
	count++;
	return count;
}

} //end class
