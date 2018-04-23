/*
 * File: MachineGUI
 * Author: Carrie Miles
 * Date: 04/03/18
 * Purpose: Create a GUI for an ATM application and Run the program
 */
package ATMMachine;

//imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class MachineGUI  implements ActionListener {

	public static JButton withdrawl= new JButton("Withdrawl");
	public static JButton deposit=new JButton("Deposit");
	public static JButton transfer = new JButton ("Transfer");
	public static JButton balance = new JButton ("Balance");
	public static Container pane;
	public static JFrame frame = new JFrame ("ATM Machine");
	public static JRadioButton checking = new JRadioButton("Checking");
	public static JRadioButton savings = new JRadioButton("Savings");
	public static  JTextField textField = new JTextField(1);
	public double checkingAccBalance;
	public double savingsAccBalance;
	public int entry = 0;
	public int count;
	
	//GUI Construction	
public MachineGUI() {
	checkingAccBalance=0;
	savingsAccBalance=0;
}
public MachineGUI(double checkBal, double saveBal) {
	checkingAccBalance = checkBal;
	savingsAccBalance=saveBal;
}

	
	//Method to build the Container and Components
    public void addComponentsToPane(Container pane) {
    int top = 3, left = 3, bottom = 3, right = 3;
    Insets inset = new Insets (top, left, bottom, right);
    	
    pane.setLayout(new GridBagLayout());
    
    //Build all of the buttons
    //Withdrawal
    GridBagConstraints wd = new GridBagConstraints();
    wd.fill = GridBagConstraints.HORIZONTAL;
    wd.insets = inset;
    withdrawl.addActionListener(this);
    pane.add(withdrawl, wd);
    	
    //Deposit
    GridBagConstraints dep = new GridBagConstraints();
    dep.fill = GridBagConstraints.HORIZONTAL;
    dep.insets = inset;
    deposit.addActionListener(this);
    pane.add(deposit, dep);
    
    //Transfer
    GridBagConstraints tran = new GridBagConstraints();
    tran.fill = GridBagConstraints.HORIZONTAL;
    tran.gridy=2;
    tran.insets = inset;
    transfer.addActionListener(this);
    pane.add(transfer, tran);
    
    // Balance
    GridBagConstraints bal = new GridBagConstraints();
    bal.fill = GridBagConstraints.HORIZONTAL;
    bal.gridy=2;
    bal.insets=inset;
    balance.addActionListener(this);
    pane.add(balance, bal);
    
    //Create the Radio Buttons
    
    //Checking
    GridBagConstraints check = new GridBagConstraints();
    check.gridy=3;
    check.insets = inset;
    //checking.addActionListener(this);
    pane.add(checking, check);
    
    //Savings
    GridBagConstraints save = new GridBagConstraints();
    savings.addActionListener(this);
    save.gridy = 3;
    save.insets = inset;
    //savings.addActionListener(this);
    pane.add(savings, save);
    
    //Only select one radio button at a time
    ButtonGroup group = new ButtonGroup();
    group.add(checking);
    group.add(savings);
    
    //Create text box 
    GridBagConstraints text = new GridBagConstraints();
    text.fill = GridBagConstraints.HORIZONTAL;
    text.gridwidth = 2;
    text.gridy =4;
    text.insets = inset;
    textField.addActionListener(this);
    
    pane.add(textField, text);
    }//end addComponents
    
  //Method to create the GUI and display
    private void createAndDisplayGUI() { 
   
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    
    //Set up the content pane
    addComponentsToPane(frame.getContentPane());
    
    
    //Display the window
    frame.setVisible(true);
    frame.getContentPane();
    }
 
    	
  
    
    //Main Method
    
		public static void main(String args[]) {
			Account checkingAcc = new Account(100);
			Account savingsAcc= new Account(100);
			MachineGUI gui = new MachineGUI(checkingAcc.getBalance(), savingsAcc.getBalance());
				//run the program
					gui.createAndDisplayGUI();	
				}	
			
				
		  public  void actionPerformed(ActionEvent evt) {
		       	
		    	//Ensure that the entry is only integers
		    	String text = textField.getText();
		    	try {
		    	entry = Integer.parseInt(text);   //tries to convert to int-- used to check if entry is numeric		
		    	
		    	}catch(NumberFormatException e) {
		    		JOptionPane.showMessageDialog(withdrawl, "Please enter only integers");
		    		System.exit(0);
		    	}catch (NullPointerException e) {
		    		JOptionPane.showMessageDialog(withdrawl, "Error: Please make your entry again");
		    	}
		    	
		    	//Ensure that the entry is in multiples of 20
		    	int num = Integer.parseInt(text);
		    	int divide = num%20;
		    	if (divide >0) {
		    		JOptionPane.showMessageDialog(withdrawl,"Please enter a number in increments of 20");
		    		System.exit(0);
		    	}
		    	
		    	
		    	//Withdraw amount from Checking/Savings
		    	if(evt.getSource()==withdrawl) {
		    		count++;
		    		if(checking.isSelected() ) {
		    		Account a = new Account(checkingAccBalance, entry, count);
		    		
		    		checkingAccBalance = a.doWithdrawl();
		    	
		    		
		    		}else if (savings.isSelected()) {
		    		Account b = new Account(savingsAccBalance, entry, count);
		    		
		    		savingsAccBalance = b.doWithdrawl();
		    		}
		    	}
		    	//Deposit amount to Checking/Savings
		    	if(evt.getSource()==deposit) {
		    		if (checking.isSelected()) {
		    			Account a = new Account(checkingAccBalance, entry);
			    		checkingAccBalance = a.doDeposit();
			    		}
		    		else if (savings.isSelected()) {
		    			Account b = new Account(savingsAccBalance, entry);
			    		savingsAccBalance = b.doDeposit();
			    		}
		    	}
		    	//Transfer amount between Checking/Savings
		    	if(evt.getSource()==transfer) {
		    		if (checking.isSelected()) {
		    			int cOptions = JOptionPane.showConfirmDialog(null, "Are you sure you want to transfer from CHECKING to SAVINGS?", null,  JOptionPane.YES_NO_OPTION);
		    			if(cOptions == JOptionPane.YES_OPTION) {
		    				Account c = new Account(checkingAccBalance, entry);
		    				Account s = new Account(savingsAccBalance, entry);
		    				c.doTransferFrom(c);
		    				s.doTransferTo(s);
		    				checkingAccBalance = c.getBalance();
		    				savingsAccBalance = s.getBalance();
		    				 JOptionPane.showMessageDialog(transfer, "You transfered " + entry + " New Balance is = \nAccount Transferred from: " + c.getBalance() + " Account recieving transfer: " + s.getBalance());
		    			}	
		    		}
		    		else if (savings.isSelected()) {
		    			int sOptions = JOptionPane.showConfirmDialog(null, "Are you sure you want to transfer from SAVINGS to CHECKING?", null, JOptionPane.YES_NO_OPTION);
		    			if(sOptions == JOptionPane.YES_OPTION) {
		    				Account c = new Account(checkingAccBalance, entry);
		    				Account s = new Account (savingsAccBalance, entry);
		    				c.doTransferTo(c);
		    				s.doTransferFrom(s);
		    				checkingAccBalance = c.getBalance();
		    				savingsAccBalance = s.getBalance();
		    				JOptionPane.showMessageDialog(transfer, "You transfered " + entry + " New Balance is = \nAccount Transferred from: " + s.getBalance() + " Account recieving transfer: " + c.getBalance());
		    			}	
									}
		    		}
			    				
		    	
		    	
		    	
		    	//Check the Balance of Checking/Savings
		    		if(evt.getSource()==balance) {
		        		if (checking.isSelected()) {
		        			JOptionPane.showMessageDialog(checking, "Checking Balance is: " + checkingAccBalance);
		        			
		        		}else if (savings.isSelected()) {
		        			JOptionPane.showMessageDialog(savings, "Savings Balance is: " + savingsAccBalance);
		        		
		        		
		        		}
		        	}
		    		
		  }
	 }


				
			 
		
			  
	
					
			
    	
	
		
	

