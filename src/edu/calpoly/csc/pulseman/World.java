package edu.calpoly.csc.pulseman;

import java.util.ArrayList;
import java.util.List;

import edu.calpoly.csc.pulseman.gameobject.Collidable;
import edu.calpoly.csc.pulseman.gameobject.GameObject;

public class World {
	private static World world = new World();
	private static List<Collidable> collidables = new ArrayList<Collidable>();
	private static List<GameObject> nonCollideables = new ArrayList<GameObject>();
	
	private World() {}
	
	static World getWorld() {
		return world;
	}
	
	static List<Collidable> getCollidables() {
		return collidables;
	}
	
	static void loadLevel(String fileName) {
		
	}
	
	
}
