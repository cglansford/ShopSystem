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
	//Associated image
	
	public StockItem() {
		
	}
	
	public StockItem(String title, String manufacturer, int price, String category) {
		this.title = title;
		this.manufacturer = manufacturer;
		this.price = price;
		this.category = category;
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
}
