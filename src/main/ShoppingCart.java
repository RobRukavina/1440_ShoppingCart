package main;

import java.util.ArrayList;

import javax.swing.JPanel;

public class ShoppingCart extends JPanel {

	private static final long serialVersionUID = 1L;
	protected static ArrayList<Product> products = new ArrayList<Product>();

	public static void addProduct(Product product, double price) {

//		Product existingProduct = Arrays.stream(products).filter(Objects::nonNull)
//				.filter(p -> p.getName().equals(product.getName())) // Match by name
//				.findFirst().orElse(null);
		Boolean existingProduct = false;

		for (Product p : products) {
			if (p.equals(product)) {
				existingProduct = true;
			}
		}

		if (!existingProduct) {
			products.add(product);
			product.updateQty(product.getQty());
			product.calcSubtotal();

		} else {
			product.updateQty(product.getQty() + 1);
			System.out.println(product.getQty());

			product.calcSubtotal();
		}

//		updateTotalPrice();

		printProducts();
	}

	public static void removeProduct(Product product) {

		if (product.getQty() > 0) {
			product.updateQty(product.getQty() - 1);
			product.calcSubtotal();
		}
		printProducts();
	}

	/**
	 * Total price of all the items in cart
	 */
	public static double calculateTotalPrice() {
		double totalPrice = 0.0;

		for (int i = 0; i < products.size(); i++) {
			totalPrice += products.get(i).getSubtotal();
		}

		return totalPrice;
	}

	// TODO TESTING -- Method to print the current product array -- TESTING
	private static void printProducts() {
		System.out.println("Current Products in Cart:");
		for (int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).getName() + "  " + products.get(i).getQty() + "  - $"
					+ products.get(i).getPrice() + "  -  $" + products.get(i).getSubtotal());
		}

		System.out.println("TOTAL PRICE: " + String.format("%.2f", calculateTotalPrice()));
	}

}
