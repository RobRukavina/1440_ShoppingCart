package main;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class ShoppingCartV2 extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Product> products = new ArrayList<>();

	
	/**
	 * 
	 */
	public ShoppingCartV2(ArrayList<Product> products) {
		this.products.addAll(products);
		setLayout(new GridBagLayout());
		
	}
	
	public void buildShoppingCart() {
		for(Product p : this.products) {
			
		}
	}
}
