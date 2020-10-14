package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FallingObject3 extends FallingObject {
	
	private Random r = new Random();
	private Handler handler;
	int time = 0;
	int seconds = 0;
	
	static float bounce = .6f;
	static float gravity = .2f;
	static int bottom = Sim.HEIGHT - 40;

	public FallingObject3(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velY = r.nextInt(2);
		velX = r.nextInt(10 + 10) - 10;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}
	
	public void tick() {
		y += velY;
		x += velX;
		
		velY += gravity;
		
		collision();
		hitBottom();
		hitWalls();
		
		if (time == 500) {
			Spawn.numObjects--;
			handler.removeObject(this);
 		}
 		time++;
	}
	
	public void decellerationX() {
		if (velX < 0) {
			velX += 1;
		} else if (velX > 0) {
			velX -= 1;
		} else { 
			velX = 0;
		}
	}
	
	public void hitWalls() {
		if (x <= 0 || x >= Sim.WIDTH - 20) { 
			velX *= -1;
		}
	}
	
	public void hitBottom() {
		if (y > bottom) {
			y = bottom;
			velY = -(velY * bounce);
			decellerationX();
		}
	}
	
	public boolean onBottom() {
		if (velY == 0 && y == bottom) {
			return true;
		}
		return false;
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			FallingObject tempObject = handler.object.get(i);
			if (tempObject.getId() != ID.FallingObject3) {
				if(getBounds().intersects(tempObject.getBounds())) {
					velX *= -1;
				}
			}
		}
	}
			
	
	public void render(Graphics g) {
		 g.setColor(Color.green);
		 g.fillRoundRect((int)x, (int)y, 20, 20, 20, 20);
		 g.setColor(Color.black);
		 g.fillRoundRect((int)x + 1, (int)y + 1, 18, 18, 18, 18);
	}
}
