/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import bots.Bot;
import items.GQ;
import java.util.ArrayList;

/**
 *
 * @author Jérémy LEMESLE <jeremy.lemesle@korko.fr>
 */
public class Faction {
	private String name;
	private GQ gq;
	private final int NB_BOTS = 3;
	private ArrayList<Bot> bots;
	
	public Faction(GQ gq) {
		name = "Sans Nom";
		this.gq = gq;
	}

	public Faction(GQ gq, String name) {
		this.name = name;
		this.gq = gq;
	}

	private void initBots() {
		/*
		int rayon = 40;

		float x, y;
		for(int i=0; i<NB_BOTS; i++){
			x = (float) Math.random()*rayon + f1.getGQ().getPos().x;
			y = (float) Math.random()*rayon + f1.getGQ().getPos().y;

			bots.add(new BasicBot(f1, x, y));
		}

		for(int i=0; i<NB_BOTS; i++){
			x = (float) Math.random()*rayon + f2.getGQ().getPos().x;
			y = (float) Math.random()*rayon + f2.getGQ().getPos().y;

			bots.add(new BasicBot(f2, x, y));
		}*/
	}

	public String getName() {
		return name;
	}

	public GQ getGQ() {
		return gq;
	}

	public void update() {

	}
	
	public ArrayList<Bot> getBots() {
		return bots;
	}
}
