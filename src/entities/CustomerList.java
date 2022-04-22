package entities;

import java.util.ArrayList;

public class CustomerList {
	
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	public CustomerList() {
		this.customerList = new ArrayList<Customer>();
	}
	
	
	public boolean isEmpty() {
		return customerList.isEmpty();
	}

	public Customer findByID(String customerID) {

		for (Customer aCustomer : customerList) {

			if (aCustomer.getCustomerID().equals(customerID)) {
				return aCustomer;
				
			}
		}
			return null;
		
	}
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	
}