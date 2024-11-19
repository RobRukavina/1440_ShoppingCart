package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int cornerRadius;
	private Color bgColor;
	private Color borderColor;
	private int borderWidth;

	public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth) {
		this.cornerRadius = radius;
		this.bgColor = bgColor;
		this.borderColor = borderColor;
		this.borderWidth = borderWidth;
		setOpaque(false);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the background with rounded corners
		g2.setColor(bgColor);
		g2.fillRoundRect(borderWidth, borderWidth, getWidth() - 2 * borderWidth, getHeight() - 2 * borderWidth,
				cornerRadius, cornerRadius);

		// Draw the border
		g2.setColor(borderColor);
		g2.setStroke(new BasicStroke(borderWidth));
		g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth,
				cornerRadius, cornerRadius);
	}
}
