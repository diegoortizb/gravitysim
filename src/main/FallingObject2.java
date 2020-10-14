package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FallingObject2 extends FallingObject {
	
	private Random r = new Random();
	private Handler handler;
	int numHitGround = 0;
	int timer = 0;

	public FallingObject2(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velY = 2;
		velX = r.nextInt(15 + 15) - 15 ;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		y += velY;
		x += velX;
		
		collision();
		
		if (y <= 0 || y >= Sim.HEIGHT - 32) {
			velY *= -1;
			numHitGround++;
		}
		
		if (x <= 0 || x >= Sim.WIDTH - 16) { 
			velX *= -1;
		}
		
		//ACCELERATION OF GRAVITY ( y )
		if (numHitGround == 0) {
			velY += 0.2f;
			decellerationX(velX);
		} else if (numHitGround < 2) {
			velY += 0.3f;
			decellerationX(velX);
		} else if (numHitGround < 3) {
			velY += 0.5f;
			decellerationX(velX);
		} else if (numHitGround < 5) {
			velY += 0.7f;
			decellerationX(velX);
		} else if (numHitGround < 7) {
			velY += 0.9f;
			decellerationX(velX);
		} else {
			velY = 0;
			y = Sim.HEIGHT - 42;
			decellerationX(velX);
		}
		
		//DECCELERATION OF VELOCITY ( x )
		if (velX < 0) {
			velX += 0.02f;
		} else if (velX > 0) {
			velX -= 0.02f;
		} else { //once velX = 0
			velX = 0;
		}
		
		//removes object once this num hits
 		if (timer == 500) {
 			Spawn.numObjects -= 1;
 			handler.removeObject(this);
 		}
 		
		timer++;		
	}
	
	public void decellerationX(float velX) {
		if (velX < 0) {
			velX += 1;
		} else if (velX > 0) {
			velX -= 1;
		} else { //once velX = 0
			velX = 0;
		}
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			FallingObject tempObject = handler.object.get(i);
			if (tempObject.getId() != ID.FallingObject2) {
				if(getBounds().intersects(tempObject.getBounds())) {
					velX *= -1;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		 g.setColor(Color.white);
		 g.fillRoundRect((int)x, (int)y, 20, 20, 20, 20);
		 g.setColor(Color.black);
		 g.fillRoundRect((int)x + 1, (int)y + 1, 18, 18, 18, 18);
	}
}
