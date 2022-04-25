package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionID;
	private String transactionBuyOrSell;
	private int userID;
	private int stockID;
	private int quantity;
	
	public TransactionHistory() {
		
	}
	
	public TransactionHistory(String transactionBuyOrSell, int userID, int stockID, int quantity) {
		this.transactionBuyOrSell = transactionBuyOrSell;
		this.userID = userID;
		this.stockID = stockID;
		this.quantity = quantity;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionBuyOrSell() {
		return transactionBuyOrSell;
	}

	public void setTransactionBuyOrSell(String transactionBuyOrSell) {
		this.transactionBuyOrSell = transactionBuyOrSell;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
