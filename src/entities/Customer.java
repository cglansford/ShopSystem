package entities;

import java.util.ArrayList;

public class Customer {
	String firstName = "";
	String surname = "";
	String address = "";
	String customerID = "";
	String password = "";
	
	ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();

	//Blank constructor
	public Customer()
	{
		this.firstName = "";
		this.surname = "";
		this.address = "";
		this.customerID = "";
		this.password = "";
		this.accounts = null;

	}
	
	//Constructor with details
	public Customer(String firstName, String surname, String address, String customerID, String password, ArrayList<CustomerAccount> accounts)
	{

		this.surname = surname;
		this.firstName = firstName;
		this.address = address;
		this.customerID = customerID;
		this.password = password;
		this.accounts = accounts;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public ArrayList<CustomerAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<CustomerAccount> accounts) {
		this.accounts = accounts;
	}

	public String toString()
	{
		return "First Name = " + this.firstName + "\n" + 
				" Surname = " + this.surname + "\n"
				+ "Customer ID = " + this.customerID;
			
	}
	
}
