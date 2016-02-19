/**
 * 
 */
package fr.maurel.rain.gfx;

import java.util.Random;

/**
 * Class which define and manage the screen of the game. The class diplays the
 * pixels of the game.
 * 
 * @author a617878
 *
 */
public class Screen {

	private int width;
	private int height;
	public int[] pixels;

	public int[] tiles = new int[64 * 64];

	private Random random = new Random();

	/**
	 * Constructor of the screen.
	 * 
	 * @param width
	 * @param height
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	/**
	 * Method which renders the screen.
	 */
	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			//if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				//if (xx < 0 || xx >= width) break;
				int tileIndex = ((xx >> 4) & 63) + ((yy >> 4) & 63) * 64;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}

	/**
	 * Method which clear my screen.
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	/**
	 * Method which updates the screen.
	 */
	public void update() {

	}

	// Getters Setters

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
