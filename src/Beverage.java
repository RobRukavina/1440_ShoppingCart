/**
 * @author michaelevans
 */

public class Beverage extends Product {
	/**
	 * size of beverage in oz(ounces).
	 */
	private int size = -1;

	public Beverage(String name, String image, double price, int stock, int size) {
		super(name, image, price, stock);
		this.setSize(size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
