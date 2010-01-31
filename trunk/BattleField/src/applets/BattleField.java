package applets;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import bots.*;
import items.*;
import javax.swing.JFrame;
import utils.*;
import surface.*;

/**
 * Very simple applet to handle fundamentals of A.I. in games.
 * 
 * This is the main applet, it handles a "battle field" with objects on it,
 * Every frames the whole field is paint again, there is a (simple) GUI.
 * 
 * How it works? After initialization of Surface, Bots, ... the 
 * applet enters (in run()) an infinite loop that... just sleep to wait for next frame,
 * and then call updateBots(), then updateBelettes() then repaint().
 * The first and second calls update positions and animations of bots and bullets,
 * the last one simple repaint all the field from scratch.
 * 
 * You may want to extend this applet using openGL like JOGL in order to enter the third dimension
 * A very simple entry for this would be for instance http://jsolutions.se/DukeBeanEm
 * 
 * @author L. Simon, Univ. Paris Sud, 2008
 *
 */
public class BattleField extends Applet
		implements Runnable, MouseListener, MouseMotionListener {

	Flag flag1;
	private static final long serialVersionUID = 1L;
	Surface surface; // The surface that contains the objects...
	// Those constants are hard constants... Why? I don't know.
	static final public float MAXX = 10000F; // Size of the battlefield, in float (not pixels)
	static final public float MAXY = 7500F;
	static final public int PREF_VIEWER_XSIZE = 800; // size in pixels (in x, the y is automatically deduced)
	// Viewer variables
	float viewer_scale; // Ratio from size of surface to size of viewer
	int viewer_xsize;
	int viewer_ysize;
	// Canvas for double buffering
	Image buffer_canvasimage;
	Graphics buffer_canvas; // Where to draw (off-screen buffer)
	Graphics viewer_canvas; // What the user actually see (on-screen buffer)
	/**
	 * Thread that sleeps and update the screen.
	 */
	private Thread update;

	// Very simple constructor
	public BattleField() {
		viewer_scale = MAXX / PREF_VIEWER_XSIZE;
	}

	public void init() {
		super.init();

		viewer_xsize = PREF_VIEWER_XSIZE; // size in pixels
		viewer_ysize = (int) (MAXY / viewer_scale); // The y axe is automatically computed

		resize(viewer_xsize, viewer_ysize);
		buffer_canvasimage = createImage(viewer_xsize, viewer_ysize);
		buffer_canvas = buffer_canvasimage.getGraphics();
		viewer_canvas = this.getGraphics();

		addMouseListener(this);
		addMouseMotionListener(this);

		initSurface();
		initBots();
                initItems();
	}

	/**
	 * Called ones to init the surface. This is where
	 * all objects attached to the surface should be loaded.
	 * Dynamic objects like bots and bullet are handled elsewhere.
	 */
	public void initSurface() {
		surface = new Surface(viewer_xsize, viewer_ysize, viewer_scale);
	}

	/**
	 * Called ones to init all your bots.
	 */
	public void initBots() {
		// TODO
	}

	/**
	 * Called ones to init all your bots.
	 */
	public void initItems() {
            flag1 = new Flag((viewer_xsize - 1) / 2,(viewer_ysize - 1) / 2, Color.CYAN);
	}

	public boolean handleEvent(Event event) {
		boolean returnValue = false;
		return returnValue;
	}

	public void start() {
		if (update == null) {
			update = new Thread(this);
			update.start();
		}
	}

	public void stop() {
		update = null;
	}

	/*
	 * This is the main loop of the game. Sleeping, then updating positions then redrawing
	 * If you want a constant framerate, you should measure how much you'll have to sleep
	 * depending on the time eated by updates functions.
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		do {
			updateBots();
                        updateItems();
			repaint();
			try {
				Thread.sleep(33);
			} catch (InterruptedException _ex) {
			}
		} while (true);
	}

	// Use very simple double buffering technique...
	/**
	 * This is a very simple double buffering technique.
	 * Drawing are done offscreen, in the buffer_canvasimage canvas.
	 * Ones all drawings are done, we copy the whole canvas to
	 * the actual viewed canvas, viewer_canvas.
	 * Thus the player will only see a very fast update of its window.
	 * No flickering.
	 *
	 */
	private void showbuffer() {
		viewer_canvas.drawImage(buffer_canvasimage, 0, 0, this);
	}

	/*
	 * Called by repaint, to paint all the offscreen surface.
	 * We erase everything, then redraw each components.
	 *
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		// 1. We erase everything
		buffer_canvas.setColor(Color.lightGray); // Background color
		buffer_canvas.fillRect(0, 0, viewer_xsize, viewer_ysize);

		// 2. We draw the surface (and its objects)
		surface.draw(buffer_canvas);
		buffer_canvas.setColor(Color.black);
		buffer_canvas.drawRect(0, 0, viewer_xsize - 1, viewer_ysize - 1);

		GQ gq1 = new GQ(100, (viewer_ysize - 1) / 2);
		gq1.draw(buffer_canvas);

		GQ gq2 = new GQ((viewer_xsize - 1) - 100, (viewer_ysize - 1) / 2);
		gq2.draw(buffer_canvas);

                flag1.draw(g);
		// 3. TODO: Draw the bots in their position/direction
		drawHUD();
		showbuffer();
	}
	/**
	 * string printed in the simple hud. For debugging...
	 */
	String gui_string = "";

	/**
	 * Very simple GUI.. Just print the infos string on the bottom of the screen, in a rectangle.
	 */
	private void drawHUD() {
		buffer_canvas.setColor(Color.red);
		buffer_canvas.drawRect(20, viewer_ysize - 23, viewer_xsize - 41, 20);
		buffer_canvas.drawChars(gui_string.toCharArray(), 0, Math.min(80, gui_string.length()), 22, viewer_ysize - 7);
	}

	/**
	 * Must update bots position / decisions / health
	 * This is where your AI will be called.
	 *
	 */
	public void updateBots() {
		// TODO: You have to update all your bots here.
	}

        public void updateItems() {
            
	}

	// Simply repaint the battle field... Called every frame...
	public void update(Graphics g) {
		paint(g);
	}

	public static void main(String args[]) {
		BattleField app = new BattleField();

		JFrame window = new JFrame("BattleField");
		window.setContentPane(app);
		window.setVisible(true);
		window.setSize(800, 640);

		app.init();
		app.start();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// Two point2D to memorize mouse gestures (pointA first click, pointB second click)
	private Vector2d pointA = new Vector2d(-1, -1);
	private Vector2d pointB = new Vector2d(-1, -1);

	// Those methods have to be there... Even if they are empty.
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	/* Here we memorize the mouse position to draw lines where points can see eachother.
	 * TODO: you must handle mouse events in your game.
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		pointA.x = e.getX();
		pointA.y = e.getY();
	}

	/* TODO: use this method your own way.
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		if (pointA.x > -1) { // pointA has been defined
			pointB.x = e.getX();
			pointB.y = e.getY();
		}
	}
}
