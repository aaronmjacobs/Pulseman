package edu.calpoly.csc.pulseman;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import edu.calpoly.csc.pulseman.gameobject.Player;


public class Camera {
	private Image parallax;
	private Image bg;
	public Camera(Image bg, Image parallax) {
		this.parallax = parallax;
		this.bg = bg;
	}
	
	/**
	 * Calculates where we want the camera to be and gradually interpolates the
	 * camera toward the destination
	 */
	public void render(GameContainer gc, Graphics g, Player p) {
		g.drawImage(bg, 0, 0);
		/*g.drawImage(parallax, -(float)(p.getHitBox().getX() - Main.getScreenWidth()), 
					-(float)(p.getHitBox().getY() - Main.getScreenHeight()));*/
		g.translate(-(float)(p.getHitBox().getX() - Main.getScreenWidth() / 2.0), 
					-(float)(p.getHitBox().getY() - Main.getScreenHeight() / 2.0));
	}
	
	/**
	 * Force the camera to the given position rather than a gradual movement
	 */
	public void setCamera(Vector2f position) {
	}
}
