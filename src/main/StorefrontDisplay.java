package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StorefrontDisplay extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private ImageIcon productIcon;
	private final int width = 1200;
	private final int height = 800;

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

		// setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize);

		setBounds(100, 100, width, height);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setPreferredSize(new Dimension(width, height));

		JLabel shopTitle = titleLabel();
		shopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(shopTitle, BorderLayout.NORTH);

		JPanel productContainer = createProductContainer();
		productContainer.setPreferredSize(new Dimension((int) (width * 0.7), height));
		getContentPane().add(productContainer, BorderLayout.WEST);

		LayoutManager itemContainerLayout = new GridLayout(0, 3, 50, 5); // layout dimensions

		JPanel beverageContainer = createBeverageContainer();
		productContainer.add(beverageContainer);

		JLabel beverageLbl = beverageLabel();
		beverageContainer.add(beverageLbl, BorderLayout.NORTH);

		JPanel beverageItemContainer = new JPanel();
		beverageContainer.add(beverageItemContainer, BorderLayout.CENTER);
		beverageItemContainer.setLayout(new GridLayout(0, 3, 50, 5));

		// Example product data
		products = new ArrayList<>();

		// Snacks
		products.add(new Snack("Banana Bread", "bananabread.png", 5.99, 15));
		products.add(new Snack("Blueberry Muffin", "blueberrymuffin.png", 4.99, 12));
		products.add(new Snack("Choccy Muffin", "chocolatemuffin.png", 4.99, 10));
		products.add(new Snack("Croissant", "croissant.png", 3.99, 18));
		products.add(new Snack("Paninini", "panini.png", 6.49, 14));
//		products.add(new Snack("Cookies", "cookies.png", 2.99, 20));// DOESNT EXIST
//		products.add(new Snack("Cake Slice", "cakeslice.png", 5.49, 10)); //DOESNT EXIST

		// Beverages
		products.add(new Beverage("Boba Milk Tea", "bobatea.png", 4.49, 12, 16));
		products.add(new Beverage("Frappuccino", "frappuccino.png", 5.99, 15, 12));
		products.add(new Beverage("Macchiato", "macchiato.png", 4.99, 18, 8));
		products.add(new Beverage("Black", "black.png", 5.29, 20, 16));
		products.add(new Beverage("Latte", "cream.png", 3.99, 10, 2));
//		products.add(new Beverage("Americano", "americano.png", 3.49, 15, 12)); // DOESNT EXIST
//		products.add(new Beverage("Cold Brew", "coldbrew.png", 4.59, 14, 16)); // DOESNT EXIST

		JPanel snackContainer = new JPanel();
		snackContainer.setLayout(new BorderLayout(0, 0));
		productContainer.add(snackContainer);

		JLabel snackTitle = snackLabel();
		snackContainer.add(snackTitle, BorderLayout.NORTH);

		JPanel snackItemContainer = new JPanel();
		snackItemContainer.setLayout(new GridLayout(0, 3, 50, 5));

		for (Product product : products) { // fill the snack and beverage containers
			URL imgPath = getClass().getResource("/" + product.getImage());
			JPanel productItem = createProductItem(product, imgPath);

			if (product instanceof Snack) {
				snackItemContainer.add(productItem);
			} else if (product instanceof Beverage) {
				beverageItemContainer.add(productItem);
			}
		}

		snackContainer.add(snackItemContainer);

		// TODO add a background to the contentPane - make this work
//		StorefrontBackground bgPanel = new StorefrontBackground();
//		bgPanel.setLayout(null);
//		bgPanel.setBounds(0, 0, getWidth(), getHeight()); // Fill the frame
//		bgPanel.setOpaque(true);

//		getContentPane().add(bgPanel);// set bg

		// SHOPPING CART

		JPanel cartContainer = new JPanel();
		cartContainer.setPreferredSize(new Dimension((int) (height * 0.3), 1000));

		getContentPane().add(cartContainer);

		pack();
		setVisible(true);

	}

	private JLabel titleLabel() {
		JLabel shopTitle = new JLabel("LE BEAN ZONE");
		shopTitle.setPreferredSize(new Dimension(width, 50));
		shopTitle.setFont(new Font("Didot", Font.BOLD, 32));
//		shopTitle.setOpaque(true);
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
	private JPanel createProductItem(Product product, URL imgPath) {

		JPanel productItem = new JPanel();
		productItem.setLayout(new BorderLayout(0, 0));

		JButton productImg = new JButton();
		productImg.setIcon(new ImageIcon(imgPath));
		productImg.setOpaque(true);
		productImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductToCart(product);
			}
		});

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
		addToCartBtn.setPreferredSize(new Dimension(50, 14));
		addToCartBtn.setBorder(new EmptyBorder(10, 0, 10, 0));
		addToCartBtn.addActionListener(e -> addProductToCart(product));
		return addToCartBtn;
	}

//TODO
	protected void addProductToCart(Product product) {
		System.out.println();
		System.out.println("you added " + product.getName() + " to the cart");
		System.out.println();
		ShoppingCart.addProduct(product, product.getPrice());

	}

}
