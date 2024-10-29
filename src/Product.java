/**
 * @author michaelevans
 */
public class Product {
	
	private String name;
	private int qty;
	private final double price;
	private double subtotal;
	private int stock;
	// TODO change to type ImageIcon
	private String image;
	
	public Product(String name, String image, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.image = image;
	}
	
	public void updateQty(int newQty) {
		this.qty = newQty;
	}
	
	public void calcSubtotal() {
		String formatted = String.format("%.2f", this.qty * this.price);
		this.subtotal = Double.parseDouble(formatted);
	}
	
	public void updateStock() {
		this.stock -= qty;
	}
	
	public String getName() {
		return this.name;
	}

	public double getSubtotal() {
		return this.subtotal;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public int getQty() {
		return this.qty;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public double getPrice() {
		return price;
	}
}
