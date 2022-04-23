package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerID;
	private String firstName;
	private String surname;
	private String address;
	private String password;
	
	


	//Blank constructor
	public Customer(){
	}
	
	//Constructor with details
	public Customer(String firstName, String surname, String address, String password)
	{
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.password = password;

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

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String toString()
	{
		return "First Name = " + this.firstName + "\n" + 
				" Surname = " + this.surname + "\n"
				+ "Customer ID = " + this.customerID;
			
	}
	
}
