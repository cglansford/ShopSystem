package entities;

public class BuyStock implements Order{

	private StockItem stockItem;
	
	public BuyStock (StockItem stockItem) {
		this.stockItem = stockItem;
	}
	@Override
	public void execute() {
		stockItem.buyStock();
	}

}
