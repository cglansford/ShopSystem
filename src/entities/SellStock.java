package entities;

public class SellStock implements Order{

	private StockItem stockItem;
	
	public SellStock(StockItem stockItem) {
		this.stockItem = stockItem;
	}
	
	@Override
	public void execute() {
		stockItem.sellStock();
	}

}
