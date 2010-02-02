package bots;

import java.awt.Graphics;
import utils.Faction;

import utils.Vector2d;

public abstract class Bot implements IBot {

	protected Vector2d coords;
	protected int size = 5;
	protected Faction f;
	
	public Bot(Faction f) {
		this.f = f;
		coords = new Vector2d(10, 10);
	}

	public Bot(Faction f, float x, float y) {
		this.f = f;
		coords = new Vector2d(x, y);
	}

	@Override
	public float botRadius() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void draw(Graphics g) {
		g.drawArc((int) coords.x, (int) coords.y, (int) botRadius(), (int) botRadius(), 0, 360);
	}

	@Override
	public Vector2d getCoord() {
		// TODO Auto-generated method stub
		return coords;
	}
}
