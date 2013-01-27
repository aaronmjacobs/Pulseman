package edu.calpoly.csc.pulseman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class SchemeLoader {
	private static Map<String, Scheme> schemeMap = new HashMap<String, Scheme>();
	private static String curScheme;
	
	private static class Scheme {
		private Animation[] props;
		private Image[] backgrounds;
		private Color col;
		
		public Scheme(Animation[] props, Image[] backgrounds, Color col) {
			this.backgrounds = backgrounds;
			this.props = props;
			this.col = col;
		}
		
		public Animation[] getProps() {
			return props;
		}
		
		public Image[] getBackgrounds() {
			return backgrounds;
		}
		
		public Color getColor() {
			return col;
		}
	}
	
	public static void createScheme(String name, Animation[] props, Image[] backgrounds, Color color) {
		schemeMap.put(name, new Scheme(props, backgrounds, color));
	}
	
	public static void loadScheme(String scheme) {
		curScheme = scheme;
	}
	
	public static Animation getProp() {
		Animation[] props = schemeMap.get(curScheme).getProps();
		int idx = new Random().nextInt(props.length);
		return props[idx];
	}
	
	public static Image[] getBackgrounds() {
		return schemeMap.get(curScheme).getBackgrounds();
	}
	
	public static Color getColor() {
		return schemeMap.get(curScheme).getColor();
	}
}
