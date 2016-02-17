/**
 * 
 */
package fr.maurel.rain;

/**
 * The main class of the game.
 * 
 * @author a617878
 *
 */
public class Game implements Runnable {

	// Screen size
	public static int width = 300;
	public static int height = width * 16 / 9;
	public static int scale = 3;

	// Thread
	private Thread thread;

	/**
	 * Method who create a new thread object and start it.
	 */
	public synchronized void start() {
		thread = new Thread(this, "Display");
		thread.start();
	}

	/**
	 * Methode who stop the thread and stop an applet.
	 */
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

	}

}
