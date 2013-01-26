package edu.calpoly.csc.pulseman;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import edu.calpoly.csc.pulseman.gameobject.Player;


public class Camera {
	private Vector2f pos;
	public Camera() {
		pos = new Vector2f(0, 0);
	}
	
	/**
	 * Calculates where we want the camera to be and gradually interpolates the
	 * camera toward the destination
	 */
	public void update(GameContainer gc, Graphics g, Player p) {
		g.translate(-pos.x, -pos.y);
	}
	
	/**
	 * Force the camera to the given position rather than a gradual movement
	 */
	public void setCamera(Vector2f position) {
	}
}
