package main;

import java.text.DecimalFormat;

/**
 * @author michaelevans
 */
public class Product {

	private String name;
	private int qty;
	private final double price;
	private double subtotal;
	private int stock;
	private String image;

	public Product(String name, String image, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.qty = 1;
		this.subtotal = 0.00;
	}

	/**
	 * Updates the quantity of a singular item.
	 * This method also updates the subtotal field.
	 * @param newQty new quantity of an item
	 */
	public void updateQty(int newQty) {
		this.qty = newQty;
		this.subtotal = (double) this.qty * this.price;
	}

	public void updateStock() {
		this.stock -= qty;
	}

	public String getName() {
		return this.name;
	}

	public double getSubtotal() {
		DecimalFormat df = new DecimalFormat("#,###.##");
		String formatted = df.format(this.subtotal);
		return Double.parseDouble(formatted);
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
