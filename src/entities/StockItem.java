package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockItemID;
	private String title;
	private String manufacturer;
	private int price;
	private String category;
	private int quanitity;
	//Associated image
	
	public StockItem() {
		
	}
	
	public StockItem(String title, int price, String category, String manufacturer) {
		this.title = title;
		this.manufacturer = manufacturer;
		this.price = price;
		this.category = category;
		this.quanitity = 10;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getStockItemID() {
		return stockItemID;
	}

	public void setStockItemID(int stockItemID) {
		this.stockItemID = stockItemID;
	}

	public int getQuanitity() {
		return quanitity;
	}

	public void setQuanitity(int quanitity) {
		this.quanitity = quanitity;
	}
	
	public void buyStock() {
		
	}
	
	public void sellStock() {
		
	}
	
}
