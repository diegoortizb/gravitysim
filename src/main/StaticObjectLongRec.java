package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StaticObjectLongRec extends StaticObject {

	private Handler handler;

	public StaticObjectLongRec(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 100, 10);
	}

	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 100, 10);
	}

}
