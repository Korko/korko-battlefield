/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Graphics;
import java.util.Vector;

/**
 *
 * @author Jérémy LEMESLE <jeremy.lemesle@korko.fr>
 */
public class WayPoint {
	private Vector2d pos;
	private Vector<WayPoint> wp;
	
	public WayPoint() {
		pos = new Vector2d(0,0);
		wp = new Vector<WayPoint>();
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

	public void link(WayPoint p) {
		// Assert : p != this
		wp.add(p);
		p.link(this);
	}
}
