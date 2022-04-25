package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import DAOs.StockDAO;

@Entity
public class StockItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockItemID;
	private String title;
	private String manufacturer;
	private int price;
	private String category;
	private int quantity;
	//Associated image
	
	public StockItem() {
		
	}
	
	public StockItem(String title, int price, String category, String manufacturer, int quantity) {
		this.title = title;
		this.manufacturer = manufacturer;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void buyStock(int quantity) {
		this.quantity += quantity;
	}
	
	public void sellStock(int quantity) {
		this.quantity -= quantity;
	}
	
}
