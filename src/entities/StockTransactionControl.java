package entities;

import DAOs.StockDAO;
import DAOs.TransactionHistoryDAO;

public class StockTransactionControl {
	
	private int customerID;
	private int stockItemID;
	private int quantity;
	private int price;
	StockDAO stockDAO = new StockDAO();
	TransactionHistoryDAO thDAO = new TransactionHistoryDAO();
	
	public StockTransactionControl(int customerID, int stockItemID, int quantity, int price) {
		this.customerID = customerID;
		this.stockItemID = stockItemID;
		this.quantity = quantity;
		this.price = price;
	}
	
	//Admin ID 9999
	//Only admins can buy stock
	public void buyStock() {
		stockDAO.buyStock(stockItemID, quantity);
		thDAO.persistObject(new TransactionHistory("buy",9999, stockItemID, quantity));
	}
	
	public void sellStock() {
		stockDAO.sellStock(stockItemID, quantity);
		thDAO.persistObject(new TransactionHistory("sell",customerID, stockItemID, quantity));
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getStockItemID() {
		return stockItemID;
	}

	public void setStockItemID(int stockItemID) {
		this.stockItemID = stockItemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public TransactionHistoryDAO getThDAO() {
		return thDAO;
	}

	public void setThDAO(TransactionHistoryDAO thDAO) {
		this.thDAO = thDAO;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
