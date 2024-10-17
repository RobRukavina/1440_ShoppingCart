import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StorefrontDisplay extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<Product> products;

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
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel shopTitle = new JLabel("LE COFFEE BEAN");
		shopTitle.setPreferredSize(new Dimension(600, 50));
		shopTitle.setOpaque(true);
		shopTitle.setBackground(Color.BLUE);

		shopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(shopTitle, BorderLayout.NORTH);

		JPanel productContainer = createProductContainer();
		getContentPane().add(productContainer, BorderLayout.WEST);

		JPanel beverageContainer = createBeverageContainer();
		productContainer.add(beverageContainer);

		JLabel beverageLbl = beverageLabel();
		beverageContainer.add(beverageLbl, BorderLayout.NORTH);

		JPanel beverageItemContainer = new JPanel();
		beverageContainer.add(beverageItemContainer, BorderLayout.CENTER);
		beverageItemContainer.setLayout(new GridLayout(2, 2, 0, 0));

		// Example product data
		products = new ArrayList<>();
		products.add(new Snack("Chips", "chips.png"));
		products.add(new Snack("Cookies", "cookies.png"));
		products.add(new Beverage("Soda", "soda.png", 32));
		products.add(new Snack("Candy", "candy.png"));
		products.add(new Beverage("Fruit Juice", "fruitjuice.png", 12));
		products.add(new Snack("Popcorn", "popcorn.png"));

		JPanel snackContainer = new JPanel();
		productContainer.add(snackContainer);
		snackContainer.setLayout(new BorderLayout(0, 0));

		JLabel snackTitle = new JLabel("SNACKS");
		snackTitle.setBackground(Color.GRAY);

		snackContainer.add(snackTitle, BorderLayout.NORTH);

		JPanel snackItemContainer = new JPanel();
		snackItemContainer.setLayout(new GridLayout(2, 3, 10, 10));

		for (Product product : products) {
			JPanel productItem = createProductItem(product);
			if (product instanceof Snack) {
				System.out.println("snack " + product.getName());
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
