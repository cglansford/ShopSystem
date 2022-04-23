package entities;

import java.util.ArrayList;

public class OrderSystem {
	
	private ArrayList<Order> allOrders = new ArrayList<Order>();
	
	public void createOrder(Order order) {
		allOrders.add(order);
	}
	
	public void executeOrder() {
		for(Order order : allOrders) {
			order.execute();
		}
		allOrders.clear();
	}

}
