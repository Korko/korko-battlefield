/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.awt.Graphics;
import utils.Vector2d;

/**
 *
 * @author Jérémy LEMESLE <jeremy.lemesle@korko.fr>
 */
public class GQ {

	static final public int SIZE = 10;
	private Vector2d pos;

	public GQ(int x, int y) {
		pos = new Vector2d(x, y);
	}

	public void draw(Graphics g) {
		g.fillOval((int) pos.x, (int) pos.y, SIZE, SIZE);
	}
}
