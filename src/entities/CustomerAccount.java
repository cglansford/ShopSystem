package entities;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane; 

public class CustomerAccount  {
   
	String accountNumber;


	//Blank Constructor
	public CustomerAccount()
	{
		this.accountNumber = "";
		
	}
	
	//Constructor with Details
	public CustomerAccount(String accountNumber)
	{
		this.accountNumber = accountNumber;
		
	}
	

	
	public String getNumber()
	{
		return this.accountNumber;
	}
	
	

	
	

	//Mutator methods
	public void setNumber(String number)
	{
		this.accountNumber = number;
	}
	

	
	public void addLodgement(Component f, double lodgementAmount) {
		
	}
	
	public void addWithdraw(Component f, double withdrawAmount) {
		
	}
	
	
}
