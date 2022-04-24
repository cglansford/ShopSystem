package entities;

import DAOs.StockDAO;

public class StockTransactionControl {
	
	private int stockItemID;
	private int quantity;
	StockDAO stockDAO = new StockDAO();
	
	public StockTransactionControl(int stockItemID, int quantity) {
		this.stockItemID = stockItemID;
		this.quantity = quantity;
	}
	
	public void buyStock() {
		stockDAO.buyStock(stockItemID, quantity);
	}
	
	public void sellStock() {
		stockDAO.sellStock(stockItemID, quantity);
	}

}
