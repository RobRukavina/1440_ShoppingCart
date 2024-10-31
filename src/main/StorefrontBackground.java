package main;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StorefrontBackground extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon mainBackground;

	public StorefrontBackground() {
		setMainBackground();
	}

	/**
	 * set background
	 */
	public void setMainBackground() {
		System.out.println(getClass().getResource("/coffeeBG1.jpg"));
		mainBackground = new ImageIcon(getClass().getResource("/coffeeBG1.jpg"));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (mainBackground != null) {
			g.drawImage(mainBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}

}
