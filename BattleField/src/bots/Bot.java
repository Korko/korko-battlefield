package bots;

import java.awt.Graphics;
import utils.Vector2d;

public class Bot implements IBot {

	protected Vector2d coords;
	protected int size = 5;
	
	public Bot() {
		coords = new Vector2d(10, 10);
	}

	public Bot(float x, float y) {
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

	@Override
	public void AI() {

	}

	@Override
	public void updatePosition() {
		
	}
}
