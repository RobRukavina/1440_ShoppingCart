/**
 * @author michaelevans
 */
public class Product {

	private String name;
	// TODO change to type ImageIcon
	private String image;
	private double price;

	public Product(String name, String image, double price) {
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}
	
	public double getPrice() {
		return price;
	}
}
