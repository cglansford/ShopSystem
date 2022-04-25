package entities;

public class BuyStock implements Order{

	private StockTransactionControl stockItem;
	
	public BuyStock (StockTransactionControl stockItem) {
		this.stockItem = stockItem;
	}
	@Override
	public void execute() {
		stockItem.buyStock();
		
	}
	
	

}
