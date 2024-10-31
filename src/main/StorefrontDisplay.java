package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StorefrontDisplay extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private ImageIcon productIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StorefrontDisplay frame = new StorefrontDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StorefrontDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		setBounds(100, 100, width, height);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel shopTitle = new JLabel("LE BEAN ZONE");
		shopTitle.setPreferredSize(new Dimension(600, 50));
		shopTitle.setOpaque(true);
		shopTitle.setBackground(Color.BLUE);

		shopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(shopTitle, BorderLayout.NORTH);

		JPanel productContainer = createProductContainer();
		productContainer.setPreferredSize(new Dimension((int) (width * 0.7), height));

		getContentPane().add(productContainer, BorderLayout.WEST);

		JPanel beverageContainer = createBeverageContainer();
		productContainer.add(beverageContainer);

		JLabel beverageLbl = beverageLabel();
		beverageContainer.add(beverageLbl, BorderLayout.NORTH);

		JPanel beverageItemContainer = new JPanel();
		beverageContainer.add(beverageItemContainer, BorderLayout.CENTER);
		beverageItemContainer.setLayout(new GridLayout(0, 3, 0, 0));

		// Example product data
		products = new ArrayList<>();

		// Snacks
		products.add(new Snack("Banana Bread", "bananabread.png", 5.99, 15));
		products.add(new Snack("Blueberry Muffin", "blueberrymuffin.png", 4.99, 12));
		products.add(new Snack("Chocolate Muffin", "chocolatemuffin.png", 4.99, 10));
		products.add(new Snack("Croissant", "croissant.png", 3.99, 18));
		products.add(new Snack("Panini", "panini.png", 6.49, 14));
		products.add(new Snack("Cookies", "cookies.png", 2.99, 20));
		products.add(new Snack("Cake Slice", "cakeslice.png", 5.49, 10));

		// Beverages
		products.add(new Beverage("Boba Tea", "bobatea.png", 4.49, 12, 16));
		products.add(new Beverage("Frappuccino", "frappuccino.png", 5.99, 15, 12));
		products.add(new Beverage("Macchiato", "macchiato.png", 4.99, 18, 8));
		products.add(new Beverage("Latte", "latte.png", 5.29, 20, 16));
		products.add(new Beverage("Espresso", "espresso.png", 3.99, 10, 2));
		products.add(new Beverage("Americano", "americano.png", 3.49, 15, 12));
		products.add(new Beverage("Cold Brew", "coldbrew.png", 4.59, 14, 16));

		JPanel snackContainer = new JPanel();
		productContainer.add(snackContainer);
		snackContainer.setLayout(new BorderLayout(0, 0));

		JLabel snackTitle = new JLabel("SNACKS");
		snackTitle.setBackground(Color.GRAY);

		snackContainer.add(snackTitle, BorderLayout.NORTH);

		JPanel snackItemContainer = new JPanel();
		snackItemContainer.setLayout(new GridLayout(0, 3, 10, 10));

		for (Product product : products) {
			JPanel productItem = createProductItem(product);
			// System.out.println(product.getImage()); // returns bananabread.png
			System.out.println(getClass().getResource("/resources/" + product.getImage())); // returns

			URL imgPath = StorefrontDisplay.class.getResource("/resources/" + product.getImage());

			if (product instanceof Snack) {
//				System.out.println("snack " + product.getName());

				JButton productIconBtn = new JButton();
				productIconBtn.setIcon(new ImageIcon(imgPath));

				snackItemContainer.add(productItem);
			} else if (product instanceof Beverage) {
				System.out.println("beverage " + product.getName());
				beverageItemContainer.add(productItem);
			}
		}

		snackContainer.add(snackItemContainer);

		pack();
		setVisible(true);

	}

	private JLabel beverageLabel() {
		JLabel beverageLbl = new JLabel("COFFEE");
		beverageLbl.setOpaque(true);
		beverageLbl.setBackground(Color.GRAY);
		return beverageLbl;
	}

	private JPanel createBeverageContainer() {
		JPanel beverageContainer = new JPanel();
		beverageContainer.setLayout(new BorderLayout(0, 0));
		return beverageContainer;
	}

	private JPanel createProductContainer() {
		JPanel productContainer = new JPanel();
		productContainer.setPreferredSize(new Dimension(500, 350));
		productContainer.setLayout(new GridLayout(0, 1, 0, 0));
		return productContainer;
	}

	/**
	 * Panel includes image and name of snack/beverage
	 * 
	 * @param product product object
	 */
	private JPanel createProductItem(Product product) {

		JPanel productItem = new JPanel();
		productItem.setLayout(new BorderLayout(0, 0));

		JLabel productImg = new JLabel(product.getImage());
		productItem.add(productImg, BorderLayout.CENTER);

		JLabel productLbl = new JLabel(product.getName());
		productItem.add(productLbl, BorderLayout.SOUTH);

		return productItem;

	}

}
