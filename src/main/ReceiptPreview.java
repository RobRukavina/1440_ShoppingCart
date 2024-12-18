package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Provides the user with a preview of the receipt before printing.
 * @author Sam Raleigh
 */
public class ReceiptPreview extends JPanel {
	private static final long serialVersionUID = 7268338721197873103L;
	private List<Product> products;
    private int containerWidth;
    private StorefrontDisplay storefrontDisplay;

    /**
     * Constructs a new ReceiptPreview object with the given parameters.
     *
     * @param products        List of products in the shopping cart
     * @param containerWidth  Width of the container to fit the receipt preview
     * @param storefrontDisplay Reference to the storefront display for navigation
     */
    public ReceiptPreview(List<Product> products, int containerWidth, StorefrontDisplay storefrontDisplay) {
        this.products = products;
        this.containerWidth = containerWidth;
        this.storefrontDisplay = storefrontDisplay;
    }

    /**
     * Creates and returns a JPanel displaying the receipt preview.
     * The receipt includes product names, quantities, subtotals, total price,
     * and a button to return to the shopping cart.
     *
     * @return JPanel displaying the receipt preview
     */
    public JPanel createReceiptPanel() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBackground(Color.WHITE);
        receiptPanel.setPreferredSize(new Dimension(containerWidth, 500));
        receiptPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Receipt Preview");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        receiptPanel.add(title);

        // Add each product to the receipt
        for (Product product : products) {
            if (product.getQty() > 0) {
                JLabel productLabel = new JLabel(
                		String.format("%s x%d - $%.2f", product.getName(), product.getQty(), product.getSubtotal())
                );
                productLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                receiptPanel.add(productLabel);
            }
        }

        // Add total price
        JLabel totalLabel = new JLabel(String.format("Total: $%.2f", ShoppingCart.calculateTotalPrice()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        totalLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        receiptPanel.add(totalLabel);
        return receiptPanel; 
        
    } 
}

