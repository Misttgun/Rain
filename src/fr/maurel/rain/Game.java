/**
 * 
 */
package fr.maurel.rain;

import java.awt.image.BufferStrategy;

import fr.maurel.rain.display.Display;

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

	private Thread thread;
	private boolean running = false;
	private Display display;
	private BufferStrategy bs;

	/**
	 * Game constructor.
	 */
	public Game() {
		display = new Display("Rain", width, height);
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
	}
}
