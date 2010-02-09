package applets;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import utils.*;
import maps.*;

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
public class BattleField extends Applet implements Runnable {
	// Those constants are hard constants... Why? I don't know.
	public static final int XSIZE = 850; // Size of the battlefield, in float (not pixels)
	public static final int YSIZE = 700;
	public static final int STEP = 33;

	private static final long serialVersionUID = 1L;
	private Map map; // The surface that contains the objects...
	private ArrayList<IEnJeu> list_en_jeu = new ArrayList<IEnJeu>();

	// Canvas for double buffering
	private Image buffer_canvasimage;
	private Graphics buffer_canvas; // Where to draw (off-screen buffer)
	private Graphics viewer_canvas; // What the user actually see (on-screen buffer)

	// Thread that sleeps and update the screen
	private Thread update;

	// Very simple constructor
	public BattleField() { super(); }

	@Override
	public void init() {
		super.init();

		resize(XSIZE, YSIZE);
		buffer_canvasimage = createImage(XSIZE, YSIZE);
		buffer_canvas = buffer_canvasimage.getGraphics();
		viewer_canvas = this.getGraphics();

		initMap();
		initBots();

		for(int i = 0; i < list_en_jeu.size(); i++)
			list_en_jeu.get(i).init();
	}

	/**
	 * Called ones to init the surface. This is where
	 * all objects attached to the surface should be loaded.
	 * Dynamic objects like bots and bullet are handled elsewhere.
	 */
	public void initMap() {
		map = new Map(XSIZE, YSIZE);
	}

	/**
	 * Called ones to init all your bots.
	 */
	public void initBots() {
		// TODO
	}

	@Override
	public void start() {
		if (update == null) {
			update = new Thread(this);
			update.start();
		}
	}

	@Override
	public void stop() {
		update = null;
	}

	/**
	 * This is the main loop of the game. Sleeping, then updating positions then redrawing
	 * If you want a constant framerate, you should measure how much you'll have to sleep
	 * depending on the time eated by updates functions.
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		do {
			updateBots();
			repaint();
			try {
				Thread.sleep(33);
			} catch (InterruptedException _ex) {
			}
		} while (true);
	}

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

	/**
	 * Called by repaint, to paint all the offscreen surface.
	 * We erase everything, then redraw each components.
	 *
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// 1. We erase everything
		buffer_canvas.setColor(Color.lightGray); // Background color
		buffer_canvas.fillRect(0, 0, XSIZE, YSIZE);

		// 2. We draw the surface (and its objects)
		map.draw(buffer_canvas);
		buffer_canvas.setColor(Color.black);
		buffer_canvas.drawRect(0, 0, XSIZE - 1, YSIZE - 1);

		// 3. TODO: Draw the bots in their position/direction

		showbuffer();
	}

	/**
	 * Must update bots position / decisions / health
	 * This is where your AI will be called.
	 *
	 */
	public void updateBots() {
		// TODO: You have to update all your bots here.
	}

	// Simply repaint the battle field... Called every frame...
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public static void main(String args[]) {
		JFrame f = new JFrame();
		BattleField app = new BattleField();
		f.getContentPane().add("Center", app);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		app.init();
		app.start();

		f.setSize(XSIZE, YSIZE);
	}
}
