/**
 * 
 */
package fr.maurel.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class which manage the keyboard and game inputs.
 * 
 * @author a617878
 *
 */
public class KeyManager implements KeyListener {

	private boolean[] keys = new boolean [256];
	public boolean up, down, left, right, enter;
	
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
