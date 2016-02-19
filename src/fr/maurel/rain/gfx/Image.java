/**
 * 
 */
package fr.maurel.rain.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import fr.maurel.rain.Game;

/**
 * Class which manage my game images.
 * 
 * @author a617878
 *
 */
public class Image {

	private BufferedImage image = new BufferedImage(Game.WIDTH, Game.HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	/**
	 * Converting our image in an array of pixels.
	 */
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// Getters Setters

	/**
	 * @return the pixels
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * 
	 * @param pixels
	 *            the pixels to set
	 */
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

}
