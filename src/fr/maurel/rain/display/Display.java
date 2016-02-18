/**
 * 
 */
package fr.maurel.rain.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import fr.maurel.rain.Game;

/**
 * Class which manages the display of the game.
 * 
 * @author a617878
 *
 */
public class Display {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width = Game.width * Game.scale;
	private int height = Game.height * Game.scale;

	/**
	 * Display constructor.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	/**
	 * Method which creates our game's display.
	 */
	private void createDisplay() {
		// Creation of the frame
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Create the canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		// Add the canvas to the frame
		frame.add(canvas);
		frame.pack();
	}

	// Getters Setters

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

}
