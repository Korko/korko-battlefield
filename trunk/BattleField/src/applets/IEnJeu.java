/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applets;

import java.awt.Graphics;
import utils.Vector2d;

public interface IEnJeu {
    public boolean is_touch(IEnJeu toucheur);

    public float size_of();

    public Vector2d get_position();

    public void draw(Graphics g);

    public boolean is_affiche();

    public void init();
}
