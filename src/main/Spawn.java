package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Spawn {
	private Handler handler;
	private Random r = new Random();
	
	int tracker = 0;
	
	static int RATE = 25;
	
	static int numObjects = 0;
		
	public Spawn(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		tracker++;
		if (tracker == RATE) {
			handler.addObject(new FallingObject4(r.nextInt(Sim.WIDTH - 50), 0, ID.FallingObject4,handler));
			numObjects++;
			tracker = 0;
		}
		else if (tracker == (RATE/2) ) {
			handler.addObject(new FallingObject3(r.nextInt(Sim.WIDTH - 50), 0, ID.FallingObject3,handler));
		 	numObjects++;
		}
	}
	
	public void displayNumObjects(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Num of Objects: " + numObjects, 10, 15);
	}
}

