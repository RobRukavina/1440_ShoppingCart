package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * StorefrontDisplay class sets up the main user interface for an online store
 * that displays products and allows customers to add items to a shopping cart.
 * It includes features for snack and beverage displays, a shopping cart, and
 * checkout functionality.
 * @author #1 Michael Evans
 * @author #2 Sam Raleigh
 * @author #3 Rob Rukavina
 */
public class StorefrontDisplay extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private final int width = 1200;
	private final int height = 800;
	private JPanel cartContainer;
	static JLabel totalLabel;

	private int containerWidth = (int) (width * 0.27);
	private int cornerRadius = 10;
	private Color outlineColor = null;
	private int outlineWidth = 0;

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

		setMinimumSize(new Dimension(width, height));

		setBounds(100, 100, width, height);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setPreferredSize(new Dimension(width, height));

		JLabel shopTitle = titleLabel();
		shopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(shopTitle, BorderLayout.NORTH);

		JPanel productContainer = createProductContainer();
		productContainer.setPreferredSize(new Dimension((int) (width * 0.7), height));
		productContainer.setBorder(new EmptyBorder(10, 20, 0, 10));

		getContentPane().add(productContainer, BorderLayout.WEST);

		JPanel beverageContainer = createBeverageContainer();
		productContainer.add(beverageContainer);

		JLabel beverageLbl = beverageLabel();
		beverageContainer.add(beverageLbl, BorderLayout.NORTH);

		JPanel beverageItemContainer = new JPanel();
		beverageItemContainer.setOpaque(false);
		beverageContainer.add(beverageItemContainer, BorderLayout.CENTER);
		beverageItemContainer.setLayout(new GridLayout(0, 3, 40, 5));

		productsAvailable();

		JPanel snackContainer = new JPanel();
		snackContainer.setLayout(new BorderLayout(0, 0));
		productContainer.add(snackContainer);

		JLabel snackTitle = snackLabel();
		snackContainer.add(snackTitle, BorderLayout.NORTH);

		JPanel snackItemContainer = new JPanel();
		snackItemContainer.setLayout(new GridLayout(0, 3, 40, 5));

		/**
		 * Fills the snack and beverage containers with images.
		 */
		for (Product product : products) {
			URL imgPath = getClass().getResource("/" + product.getImage());
			JPanel productItem = createProductItem(product, imgPath);

			if (product instanceof Snack) {
				snackItemContainer.add(productItem);
			} else if (product instanceof Beverage) {
				beverageItemContainer.add(productItem);
			}
		}

		snackContainer.add(snackItemContainer);

		// SHOPPING CART

		cartContainer(containerWidth);
		getContentPane().add(cartContainer, BorderLayout.EAST);
		cartContainer.setLayout(new BoxLayout(cartContainer, BoxLayout.Y_AXIS));

		RoundedPanel roundedInnerPanel = createRoundedPanel(containerWidth, cornerRadius, outlineColor, outlineWidth);
		roundedInnerPanel.setLayout(new BoxLayout(roundedInnerPanel, BoxLayout.Y_AXIS));

		JPanel cartItemPanel = ShoppingCart.cartItemPanel(containerWidth, cornerRadius, outlineColor, outlineWidth);
		roundedInnerPanel.add(cartItemPanel);

		totalLabel = ShoppingCart.totalLabel(containerWidth);
		roundedInnerPanel.add(totalLabel);

		cartContainer.add(roundedInnerPanel);

		cartContainer.add(Box.createVerticalStrut(10));

		JButton checkoutBtn = ShoppingCart.checkoutButton(containerWidth);
		cartContainer.add(checkoutBtn);

		// END SHOPPING CART

		// TODO add a background to the contentPane - make this work
//		StorefrontBackground bgPanel = new StorefrontBackground();
//		bgPanel.setLayout(null);
//		bgPanel.setBounds(0, 0, getWidth(), getHeight()); // Fill the frame
//		bgPanel.setOpaque(true);
//
//		getContentPane().add(bgPanel);// set bg

		pack();
		setVisible(true);

	}

	/**
	 * Populates the available products list with predefined products.
	 */
	private void productsAvailable() {
		// Example product data
		products = new ArrayList<>();

		// Snacks
		products.add(new Snack("Banana Bread", "bananabread.png", 5.99, 15));
		products.add(new Snack("Blueberry Muffin", "blueberrymuffin.png", 4.99, 12));
		products.add(new Snack("Choccy Muffin", "chocolatemuffin.png", 4.99, 10));
		products.add(new Snack("Croissant", "croissant.png", 3.99, 18));
		products.add(new Snack("Paninini", "panini.png", 6.49, 14));
		// TODO lets add a cinnamon roll

		// Beverages
		products.add(new Beverage("Boba Milk Tea", "bobatea.png", 4.49, 12, 16));
		products.add(new Beverage("Frappuccino", "frappuccino.png", 5.99, 15, 12));
		products.add(new Beverage("Macchiato", "macchiato.png", 4.99, 18, 8));
		products.add(new Beverage("Black", "black.png", 5.29, 20, 16));
		products.add(new Beverage("Latte", "cream.png", 3.99, 10, 2));
		// TODO lets add a cold brew
//		products.add(new Beverage("Cold Brew", "coldbrew.png", 4.59, 14, 16)); // DOESNT EXIST

	}

	/**
	 * Initializes the shopping cart container panel with the specified width.
	 *
	 * @param containerWidth width of the cart container
	 */
	private void cartContainer(int containerWidth) {
		cartContainer = new JPanel();

		cartContainer.setPreferredSize(new Dimension(containerWidth, height - 50));

		cartContainer.setBorder(new EmptyBorder(20, 0, 100, 20));
	}
	
	/**
	 * Returns the shopping cart container panel.
	 * 
	 * @return the cart container panel
	 */
	public JPanel getCartContainer() {
	    return cartContainer;
	}

	/**
	 * Creates a rounded inner panel for the shopping cart with specified
	 * dimensions.
	 *
	 * @param containerWidth width of the container
	 * @param cornerRadius   corner radius of the rounded panel
	 * @param outlineColor   outline color of the panel
	 * @param outlineWidth   width of the outline
	 * @return rounded panel
	 */
	private RoundedPanel createRoundedPanel(int containerWidth, int cornerRadius, Color outlineColor,
			int outlineWidth) {
		RoundedPanel roundedInnerPanel = new RoundedPanel(cornerRadius, outlineColor, outlineWidth);
		roundedInnerPanel.setBackground(Color.WHITE);
		roundedInnerPanel.setPreferredSize(new Dimension(containerWidth, height - 150));
		roundedInnerPanel.setMinimumSize(new Dimension(containerWidth, height - 150));
		return roundedInnerPanel;
	}

	private JLabel titleLabel() {
		JLabel shopTitle = new JLabel("LE BEAN ZONE");
		shopTitle.setPreferredSize(new Dimension(width, 50));
		shopTitle.setFont(new Font("Didot", Font.BOLD, 32));
		shopTitle.setBackground(new Color(100, 10, 0, 100));
		return shopTitle;
	}

	private JLabel snackLabel() {
		JLabel snackTitle = new JLabel("SNACKS");
		snackTitle.setFont(new Font("Serif", Font.BOLD, 24));
		return snackTitle;
	}

	private JLabel beverageLabel() {
		JLabel beverageLbl = new JLabel("COFFEE");
		beverageLbl.setFont(new Font("Serif", Font.BOLD, 24));

		return beverageLbl;
	}

	/**
	 * Creates a beverage container panel.
	 *
	 * @return beverage container panel
	 */
	private JPanel createBeverageContainer() {
		JPanel beverageContainer = new JPanel();
		beverageContainer.setOpaque(false);
		beverageContainer.setLayout(new BorderLayout(0, 0));
		return beverageContainer;
	}

	/**
	 * Creates a product container for organizing beverage and snack items.
	 *
	 * @return product container panel
	 */
	private JPanel createProductContainer() {
		JPanel productContainer = new JPanel();
		productContainer.setOpaque(false);
		productContainer.setPreferredSize(new Dimension(500, 350));
		productContainer.setLayout(new GridLayout(0, 1, 0, 0));
		return productContainer;
	}

	/**
	 * Creates a product item panel with an image and adds an action listener for
	 * adding items to the cart.
	 *
	 * @param product the product to be displayed
	 * @param imgPath URL path to the product's image
	 * @return product item panel
	 */
	private JPanel createProductItem(Product product, URL imgPath) {

		JPanel productItem = new JPanel();
		productItem.setLayout(new BorderLayout(0, 0));

		JButton productImg = new JButton();
		productImg.setIcon(new ImageIcon(imgPath));
		productImg.setContentAreaFilled(false);
		productImg.setBackground(Color.LIGHT_GRAY);
		productImg.setBorderPainted(false);
		productImg.setFocusable(false);
		productImg.setOpaque(true);

		productItem.add(productImg, BorderLayout.CENTER);

		JPanel productLblContainer = new JPanel();
		productLblContainer.setBorder(new EmptyBorder(5, 0, 5, 0));
		productLblContainer.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel productLbl = new JLabel(product.getName());
		productLbl.setFont(new Font("Serif", Font.ITALIC, 14));

		JButton addToCartBtn = addToCartButton(product);
		productLblContainer.add(productLbl);
		productLblContainer.add(addToCartBtn);

		productItem.add(productLblContainer, BorderLayout.SOUTH);

		return productItem;

	}

	private JButton addToCartButton(Product product) {
		JButton addToCartBtn = new JButton();
		addToCartBtn.setOpaque(true);
		addToCartBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		addToCartBtn.setText("add to cart");
		addToCartBtn.setBackground(Color.WHITE);
		addToCartBtn.setFocusPainted(false);
		addToCartBtn.setOpaque(true);
		addToCartBtn.setPreferredSize(new Dimension(50, 14));
		addToCartBtn.addActionListener(e -> addProductToCart(product));
		return addToCartBtn;
	}

	protected void addProductToCart(Product product) {
		System.out.println();
		System.out.println("you added " + product.getName() + " to the cart");
		System.out.println();
		ShoppingCart.addProduct(product, product.getPrice());
		ShoppingCart.updateCartDisplay();
	}


}
