package main;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ShoppingCart extends JPanel {

	private static final long serialVersionUID = 1L;
	public static Product[] products = new Product[100];
	public static int productCount = 0;

	public static void addProduct(Product product, double price) {

		if (productCount >= products.length) {
			return;
		}

		Product existingProduct = Arrays.stream(products).filter(Objects::nonNull)
				.filter(p -> p.getName().equals(product.getName())) // Match by name
				.findFirst().orElse(null);

		if (existingProduct != null) {

			product.updateQty(product.getQty() + 1);
			product.calcSubtotal();

		} else {
			products[productCount++] = product; // add to the next index
		}

//		updateTotalPrice();

		printProducts();
	}
	
	public static void removeProduct(Product product) {
		for (int i = 0; i < productCount; i++) {
			if (products[i] != null && products[i].equals(product)) {
				products[i] = null;
				productCount--;
				break;
			}
		}
	}

	/**
	 * Total price of all the items in cart
	 */
	public static double calculateTotalPrice() {
		double totalPrice = 0.0;

		for (int i = 0; i < productCount; i++) {
			totalPrice += products[i].getSubtotal();
		}

		return totalPrice;
	}

//	public static void updateTotalPrice() {
//		double total = calculateTotalPrice();
//		System.out.println(total);
//		totalLabel.setText(String.format("Total Price: $%.2f", total));
//	}

	// TODO TESTING -- Method to print the current product array -- TESTING
	private static void printProducts() {
		System.out.println("Current Products in Cart:");
		for (int i = 0; i < productCount; i++) {
			System.out.println(products[i].getName() + "  " + products[i].getQty() + "  - $" + products[i].getPrice()
					+ "  -  $" + products[i].getSubtotal());
		}

		System.out.println("TOTAL PRICE: " + String.format("%.2f", calculateTotalPrice()));
	}

}
