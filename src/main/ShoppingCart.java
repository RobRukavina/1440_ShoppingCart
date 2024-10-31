package main;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class ShoppingCart extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Product[] products = new Product[10]; //static array to be changed later.
	private static int productCount = 0;
	private static JLabel totalLabel;

	/**
	 * Create the panel.
	 */
	public ShoppingCart() {
		setLayout(new BorderLayout(0, 0));
		totalLabel = new JLabel("Total Price: $0.00");
		totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(totalLabel, BorderLayout.SOUTH);
		updateTotalPrice();
	}
	
	public static void addProduct(Product product, double price) {
		if (productCount < products.length) {
			products[productCount++] = product;
			updateTotalPrice();
		} else {
			System.out.println("Cannot add more products.");
		}
	}
	
	public static double calculateTotalPrice() {
		double totalPrice = 0.0;
		
		for (int i = 0; i < productCount; i++) {
			totalPrice += products[i].getPrice();
		}
		return totalPrice;
	}
	
	public static void updateTotalPrice() {
		double total = calculateTotalPrice();
		totalLabel.setText(String.format("Total Price: $%.2f", total));
	}

}
