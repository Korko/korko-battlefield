/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Graphics;

/**
 *
 * @author Jérémy LEMESLE <jeremy.lemesle@korko.fr>
 */
public class WayPoint {
	private Vector2d pos;

	public WayPoint() {
		pos = new Vector2d(0,0);
	}

	public WayPoint(float x, float y) {
		pos = new Vector2d(x, y);
	}
	
	public Vector2d getPos() {
		return pos;
	}

	public void draw(Graphics g) {
		g.drawArc((int) pos.x, (int) pos.y, 20, 20, 0, 360);
	}
}
