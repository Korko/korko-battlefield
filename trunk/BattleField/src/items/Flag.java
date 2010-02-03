/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import bots.IBot;
import java.awt.Color;
import java.awt.Graphics;
import utils.Vector2d;

/**
 *
 * @author paul
 */
public class Flag implements IItem{

    private Vector2d pos;
    private int respawn_time;
    private int size;
    private Color team;

    public Flag(int x, int y) {
        pos = new Vector2d(x, y);
        size = 100;
        team = new Color(0,0,0);
    }

    public Flag(int x, int y, Color color){
        pos = new Vector2d(x, y);
        size = 100;
        team = color;

    }

    public int getRespawnTime() {
        return respawn_time;
    }

    
    public void setRespawnTime(int frames) {
        respawn_time = frames;
    }

    public void addCoordRespawn(int x, int y) {
        pos = new Vector2d(x,y);
    }

    @Override
    public Vector2d getCoord() {
        return pos;
    }

    @Override
    public float itemRadius() {
        return size;
    }

    @Override
    public void capturedByBot(IBot bot) {
        //changer la forme et la couleur du bot
    }

    @Override
    public void draw(Graphics g) {
        Color old_color = g.getColor();
        /* Hampe */
        g.setColor(Color.black);
        int[] tab = {(int)(pos.x-(size/4)),(int)(pos.x-(size/4)),(int)pos.x,(int)pos.x};
        int[] tab2 = {(int)(pos.y+(size/2)),(int)(pos.y-(size/2)),(int)(pos.y-(size/2)),(int)(pos.y+(size/2))};
        g.fillPolygon(tab , tab2, 4);
        /* Tissu */
        g.setColor(team);
        int[] tab3 = {(int)pos.x,(int)pos.x,(int)(pos.x+((size*3)/4)),(int)(pos.x+((size*3)/4))};
        int[] tab4 = {(int)pos.y,(int)(pos.y+(size/2)),(int)(pos.y+(size/2)),(int)pos.y};
        g.fillPolygon(tab3 , tab4, 4);

        Color color = new Color(0, 255, 0);
        g.setColor(color);
        g.fillOval((int) pos.x, (int) pos.y, 30, 30);

        g.setColor(old_color);
    }

    @Override
    public String toString(){
        return "Nothing\n";
    }

    @Override
    public void computeNextFrame() {
        
    }

}
