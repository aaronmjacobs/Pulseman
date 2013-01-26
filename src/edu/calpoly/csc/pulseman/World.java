package edu.calpoly.csc.pulseman;

public class World {
	private static World world = new World();
	
	private World() {}
	
	static World GetWorld() {
		return world;
	}
	
	
	
}
