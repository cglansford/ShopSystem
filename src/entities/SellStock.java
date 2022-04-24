package entities;

public class SellStock implements Order{

	private StockTransactionControl stockItem;
	
	public SellStock(StockTransactionControl stockItem) {
		this.stockItem = stockItem;
	}
	
	@Override
	public void execute() {
		stockItem.sellStock();
	}

}
