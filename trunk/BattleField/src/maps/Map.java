/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maps;

import items.GQ;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import surface.Surface;
import utils.WayPoint;

/**
 *
 * @author Jérémy LEMESLE <jeremy.lemesle@korko.fr>
 */
public abstract class Map {
	protected ArrayList<WayPoint> wp;
	protected Surface surface;
	
	public Map() {
		wp = new ArrayList<WayPoint>();
	}

	/**
	 * Called ones to init the surface. This is where
	 * all objects attached to the surface should be loaded.
	 * Dynamic objects like bots and bullet are handled elsewhere.
	 */
	public void initSurface() {
		surface = new Surface(500, 400, 1.f); // TODO: Size
	}
	
	public void draw(Graphics g, int minx, int miny, int maxx, int maxy) {
		surface.draw(g);
		g.setColor(Color.black);
		g.drawRect(0, 0, maxx, maxy); // TODO: Caution !
		
		for(WayPoint waypoint : wp) {
			waypoint.draw(g);
		}
	}

	public ArrayList<WayPoint> getWayPoints() {
		return wp;
	}

	public GQ getGQ(int rank) {
		return null;
	}
}
