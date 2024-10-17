/**
 * @author michaelevans
 */
public class Product {

	private String name;
	// TODO change to type ImageIcon
	private String image;

	public Product(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}
}
