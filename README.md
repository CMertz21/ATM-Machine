# ATM-Machine

This project involves writing a program that implements an ATM machine. The interface to the
program should be a Java GUI.
The program should consist of three classes.
1. The first class should define the GUI. In addition to the main method and a constructor to
build the GUI, event handlers will be needed to handle each of the four buttons shown
above. When the Withdraw button is clicked, several checks must be made. The first
check is to ensure the value in the text field is numeric. Next a check must be made to
ensure the amount is in increments of $20. At that point an attempt to withdraw the funds
is made from the account selected by the radio buttons. The attempt might result in an
exception being thrown for insufficient funds, If any of those three errors occur a
JOptionPane window should be displayed explaining the error. Otherwise a window
should be displayed confirming that the withdrawal has succeeded. When the Deposit
button is clicked the only necessary check is to ensure that the amount input in the
textfield is numeric. Clicking the Transfer button signifies transferring funds to the
selected account from the other account. The checks needed are to confirm that the
amount supplied is numeric and that there are sufficient funds in the account from which
the funds are being transferred. Clicking the Balance button will cause a JOptionPane
window to be displayed showing the current balance in the selected account. The main
class must contain two Account objects, one for the checking account and another for the
savings account.
2. The second class is Account. It must have a constructor plus a method that corresponds
to each of the four buttons in the GUI. It must also incorporate logic to deduct a service
charge of $1.50 when more than four total withdrawals are made from either account.
Note that this means, for example, if two withdrawals are made from the checking and
two from the savings, any withdrawal from either account thereafter incurs the service
charge. The method that performs the withdrawals must throw an InsufficientFunds
exception whenever an attempt is made to withdraw more funds than are available in the 
2
account. Note that when service charges apply, there must also be sufficient funds to pay
for that charge.
3. The third class is InsufficientFunds, which is a user defined checked exception.
