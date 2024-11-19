package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Represents the ShoppingCart object which extends JPanel
 * 
 * @author #1 Sam Raleigh
 * @author #2 Rob Rukavina
 * @author #3 Michael Evans
 */
public class ShoppingCart extends JPanel {
	static JPanel cartItemsPanel;
	static JLabel totalLabel;
	private static final long serialVersionUID = 1L;
	public static ArrayList<Product> products = new ArrayList<Product>();

	/**
	 * Adds a product to the ShoppingCart products array
	 * 
	 * @param product
	 * @param price
	 */
	public static void addProduct(Product product, double price) {
		Boolean existingProduct = false;

		for (Product p : products) {
			if (p.equals(product)) {
				existingProduct = true;
			}
		}

		if (!existingProduct) {
			products.add(product);
			product.updateQty(product.getQty());

		} else {
			product.updateQty(product.getQty() + 1);
			System.out.println(product.getQty());
		}

		printProducts();
	}


	/**
	 * Removes a product from the ShoppingCart products array
	 * 
	 * @param product
	 */
	public static void removeProduct(Product product) {
		if (product.getQty() > 0) {
			product.updateQty(product.getQty() - 1);
		}
		}
		printProducts();
	}


	/**
	 * Sets up the panel for displaying items in the shopping cart.
	 *
	 * @param containerWidth width of the panel
	 * @param cornerRadius   corner radius for the panel
	 * @param outlineColor   color of the outline
	 * @param outlineWidth   width of the outline
	 */
	static JPanel cartItemPanel(int containerWidth, Color bgColor, int cornerRadius, Color outlineColor,
			int outlineWidth) {
		cartItemsPanel = new RoundedPanel(cornerRadius, bgColor, outlineColor, outlineWidth);
		cartItemsPanel.setSize(new Dimension(containerWidth, 800));
		cartItemsPanel.setOpaque(true);
		cartItemsPanel.setPreferredSize(new Dimension(containerWidth, 500));
		cartItemsPanel.setMinimumSize(new Dimension(containerWidth, 500));
		cartItemsPanel.setMaximumSize(new Dimension(containerWidth, 750));
		cartItemsPanel.setBackground(Color.WHITE);
		cartItemsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

		return cartItemsPanel;
	}

	public static JPanel getCartItemPanel() {
		return cartItemsPanel;
	}


	/**
	 * Updates the shopping cart display by clearing and repopulating the cart items
	 * panel with the current products in the cart. For each product, displays the
	 * product name, quantity, subtotal, and quantity adjustment buttons (plus and
	 * minus).
	 * 
	 * The minus button decreases the product quantity by one, or removes the
	 * product if quantity reaches zero. The plus button increases the product
	 * quantity by one. Updates the cart total price after modifying product
	 * quantities.
	 */
	static void updateCartDisplay() {
		cartItemsPanel.removeAll();
		for (Product product : ShoppingCart.products) {
			if (product != null && product.getQty() > 0) {
				JPanel itemPanel = new JPanel();
				itemPanel.setLayout(new BorderLayout(0,  0));
				itemPanel.setMaximumSize(new Dimension(400, 25));
				itemPanel.setBackground(Color.WHITE);
				itemPanel.setAlignmentX(LEFT_ALIGNMENT);
				itemPanel.setBorder(new EmptyBorder(0, 0, 0, 0));


				JLabel itemLabel = new JLabel(
						product.getName() + " x" + product.getQty() + "     $" + product.getSubtotal());
						product.getName() + " x" + product.getQty() + "     $" + product.getSubtotal());
				itemLabel.setFont(new Font("Arial", Font.ITALIC, 16));
				itemLabel.setPreferredSize(new Dimension(125, 25));
				itemLabel.setMaximumSize(new Dimension(125, 25));
				itemLabel.setBorder(new EmptyBorder(0, 0, 0, 5));


				itemPanel.add(itemLabel, BorderLayout.CENTER);


				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BorderLayout());
				buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
				JButton minusButton = new JButton("-");
				minusButton.setPreferredSize(new Dimension(45, 25));
				minusButton.setBackground(Color.WHITE);
				minusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				minusButton.setFont(new Font("Arial", Font.BOLD, 12));
				minusButton.addActionListener(e -> {
					if (product.getQty() > 1) {
						product.updateQty(product.getQty() - 1);
					} else {
						ShoppingCart.removeProduct(product);
					}
					updateCartDisplay();
					updateCartTotal();
				});


				buttonPanel.add(minusButton, BorderLayout.WEST);


				JButton plusButton = new JButton("+");
				plusButton.setPreferredSize(new Dimension(45, 25));
				plusButton.setFont(new Font("Arial", Font.BOLD, 12));
				plusButton.setBackground(Color.WHITE);
				plusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				plusButton.addActionListener(e -> {
					product.updateQty(product.getQty() + 1);
					updateCartDisplay();
					updateCartTotal();
				});


				buttonPanel.add(plusButton, BorderLayout.EAST);
				itemPanel.add(buttonPanel, BorderLayout.EAST);
				cartItemsPanel.add(itemPanel);
			}
		}

		updateCartTotal();
		cartItemsPanel.revalidate();
		cartItemsPanel.repaint();
	}


	/**
	 * 
	 * Updates the total label with the current shopping cart total
	 * Updates the total label with the current shopping cart total
	 * 
	 */
	private static void updateCartTotal() {
		double total = ShoppingCart.calculateTotalPrice();
		StorefrontDisplay.totalLabel.setText(String.format("Total Price: $%.2f", total));
	}


	/**
	 * Configures and initializes the label displaying the total price of items in
	 * the cart.
	 *
	 * @param containerWidth width of the label
	 * @return
	 * @return
	 */
	static JLabel totalLabel(int containerWidth) {
		totalLabel = new JLabel("Total Price: $0.00 ");
		totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
		totalLabel.setOpaque(false);
		totalLabel.setPreferredSize(new Dimension(containerWidth, 30));
		totalLabel.setMinimumSize(new Dimension(containerWidth, 30));
		totalLabel.setMaximumSize(new Dimension(containerWidth, 30));
		totalLabel.setBorder(new EmptyBorder(10, 10, 20, 10));
		return totalLabel;
	}

	/**
	 * Creates a JButton for checkoutButton
	 * 
	 * @param containerWidth
	 * @return
	 */
	static JButton checkoutButton(int containerWidth) {
		JButton checkoutBtn = new JButton("Checkout");
		checkoutBtn.setContentAreaFilled(false);
		checkoutBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		checkoutBtn.setForeground(Color.BLACK);
		checkoutBtn.setFocusable(false);
		checkoutBtn.setOpaque(false);
		checkoutBtn.setBorderPainted(false);
		checkoutBtn.setPreferredSize(new Dimension(containerWidth, 50)); // was 50
		checkoutBtn.setMinimumSize(new Dimension(containerWidth, 50));
		checkoutBtn.setMaximumSize(new Dimension(containerWidth, 50));
		checkoutBtn.addActionListener(e -> {
			// Generate the receipt string
			String receipt = ShoppingCart.generateReceipt();

			// Show the receipt in a message dialog
			JOptionPane.showMessageDialog(null, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
		});
		return checkoutBtn;
	}

	/**
	 * Generates a formated receipt of products in the cart.
	 * Includes item names, quantities, subtotals, and the total price.
	 */
	public static String generateReceipt() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("Le Bean Zone\n");
		receipt.append("Barista: Chan\n");
		receipt.append("========================\n");
		for (Product product : products) {
			if (product.getQty() > 0) {
				receipt.append(
				String.format("%s x%d - $%.2f\n", product.getName(), product.getQty(), product.getSubtotal()));
			}
		}
		receipt.append("========================\n");
		receipt.append(String.format("Total: $%.2f\n", calculateTotalPrice()));
		return receipt.toString();
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
