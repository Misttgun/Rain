/**
 * 
 */
package fr.maurel.rain;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fr.maurel.rain.display.Display;
import fr.maurel.rain.gfx.Image;
import fr.maurel.rain.gfx.Screen;
import fr.maurel.rain.input.KeyManager;

/**
 * The main class of the game.
 * 
 * @author a617878
 *
 */
public class Game implements Runnable {

	// Screen size
	public static int WIDTH = 800 / 3;
	public static int HEIGHT = WIDTH * 9 / 16;
	public static int scale = 3;

	private static String TITLE = "Rain";
	private Display display;

	private Thread thread;
	private KeyManager keyboard;
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
		screen = new Screen(WIDTH, HEIGHT);
		display = new Display(TITLE, WIDTH * scale, HEIGHT * scale);

		keyboard = new KeyManager();
		display.getFrame().addKeyListener(keyboard);
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

		// Number of times we want to update per secondes.
		final int fps = 60;

		// Max time in nanoseconds to update and render.
		final double timePerTick = 1000000000 / fps;

		// Requisite time before an update and render.
		double delta = 0;

		// Current clock of the computer in nanoseconds.
		long now;

		// Clock of the computer in nanoseconds on the last loop statement.
		long lastTime = System.nanoTime();

		long timer = 0;
		int ticks = 0;
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				display.getFrame().setTitle(TITLE + " | " + ticks + " Ticks");
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	int x = 0, y = 0;

	/**
	 * Method which updates the game while it's running.
	 */
	public void update() {
		keyboard.update();
		if (keyboard.up) y--;
		if (keyboard.down) y++;
		if (keyboard.left) x--;
		if (keyboard.right) x++;
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
		screen.render(x, y);
		for (int i = 0; i < image.getPixels().length; i++) {
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
