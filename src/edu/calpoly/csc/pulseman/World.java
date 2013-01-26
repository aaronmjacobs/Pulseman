package edu.calpoly.csc.pulseman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import edu.calpoly.csc.pulseman.gameobject.Collidable;
import edu.calpoly.csc.pulseman.gameobject.GameObject;
import edu.calpoly.csc.pulseman.gameobject.Tile;

public class World {
	public static int kPixelsPerTile = 32;
	
	private static World world = new World();
	private static int lvlWidth, lvlHeight;
	private static List<Collidable> collidables = new ArrayList<Collidable>();
	private static List<GameObject> nonCollidables = new ArrayList<GameObject>();
	private static Vector2f playerSpawn = new Vector2f(0.0f, 0.0f);
	
	private static enum TileType {kNothing, kTile, kPlayerSpawn, kEnemy, kFlippedEnemy};
	private static Map<Color, TileType> ColorMap = new HashMap<Color, TileType>();
	
	static {
		ColorMap.put(new Color(1.0f, 1.0f, 1.0f), TileType.kNothing);
		ColorMap.put(new Color(0, 120.0f / 255.0f, 0), TileType.kPlayerSpawn);
		ColorMap.put(new Color(2.0f / 255.0f, 50.0f / 255.0f , 50.0f / 255.0f), TileType.kTile);
		ColorMap.put(new Color(100.0f / 255.0f, 20.0f / 255.0f , 20.0f / 255.0f), TileType.kEnemy);
		ColorMap.put(new Color(110.0f / 255.0f, 20.0f / 255.0f , 20.0f / 255.0f), TileType.kFlippedEnemy);
	}
	
	private World() {}
	
	static World getWorld() {
		return world;
	}
	
	static List<Collidable> getCollidables() {
		return collidables;
	}
	
	static void loadLevel(String fileName) throws SlickException {
		Image level = new Image(fileName);
		int width = level.getWidth(), height = level.getHeight();
		
		collidables.clear();
		nonCollidables.clear();
		lvlWidth = width * kPixelsPerTile;
		lvlHeight = height * kPixelsPerTile;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				PixelToObject(level.getColor(x, y), x * kPixelsPerTile, y * kPixelsPerTile);
			}
		}
	}
	
	private static void PixelToObject(Color color, int xPos, int yPos) throws SlickException {
		TileType type = ColorMap.get(color); 
		if (type == null) {
			throw new RuntimeException("Color not found in color map, color: " + color);
		}
		switch(type) {
		case kNothing:
			break;
		case kPlayerSpawn:
			playerSpawn.set(xPos + 10, yPos - 10);
			break;
		case kTile:
			collidables.add(new Tile(xPos, yPos));
			break;
		}
	}
	
	static int getLevelWidth() {
		return lvlWidth;
	}
	
	static int getLevelHeight() {
		return lvlHeight;
	}
	
	public static Vector2f getPlayerSpawn() {
		return playerSpawn;
	}
	
	
}
