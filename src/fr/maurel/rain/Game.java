/**
 * 
 */
package fr.maurel.rain;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fr.maurel.rain.display.Display;
import fr.maurel.rain.gfx.Image;
import fr.maurel.rain.gfx.Screen;

/**
 * The main class of the game.
 * 
 * @author a617878
 *
 */
public class Game implements Runnable {

	// Screen size
	public static int width = 800 / 3;
	public static int height = width * 9 / 16;
	public static int scale = 3;
	private Display display;

	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;
	private Screen screen;
	Image image = new Image();

	/**
	 * Game constructor.
	 */
	public Game() {

	}

	/**
	 * Method which initailizes our game. It add the display, the screen, the
	 * states, etc.
	 */
	private void init() {
		screen = new Screen(width, height);
		display = new Display("Rain", width * scale, height * scale);
	}

	/**
	 * Method who create a new thread object and start it.
	 */
	public synchronized void start() {
		if (running) return;

		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	/**
	 * Methode who stop the thread and stop an applet.
	 */
	public synchronized void stop() {
		if (!running) return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		while (running) {
			update();
			render();
		}
	}

	/**
	 * Method which updates the game while it's running.
	 */
	public void update() {

	}

	/**
	 * Method which manages the rendering of the game.
	 */
	public void render() {
		// Creation of the buffer (3 of them).
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// Clearing my screen
		screen.clear();
		
		// Rendering my screen.
		screen.render();
		for(int i=0;i<image.getPixels().length;i++){
			image.getPixels()[i] = screen.pixels[i];
		}

		// Creating the drawing
		g = bs.getDrawGraphics();

		// Clearing the display
		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		// Start of the drawing
		g.drawImage(image.getImage(), 0, 0, display.getWidth(), display.getHeight(), null);

		// End of the drawing
		bs.show();
		g.dispose();
	}
}
