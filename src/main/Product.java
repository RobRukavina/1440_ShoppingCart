package main;

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
		this.qty = 1;
		this.subtotal = price;
	}

	/**
	 * Update the quantity of a singular item.
	 * 
	 * @param newQty new quantity of an item
	 */
	public void updateQty(int newQty) {
		this.qty = newQty;
	}

	/**
	 * Calculate the subtotal of a particular item.
	 */
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
