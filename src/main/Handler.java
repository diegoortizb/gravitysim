package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<FallingObject> object = new LinkedList<FallingObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			FallingObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			FallingObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearFallingObject() {
		for(int i = 0; i < object.size(); i++) {
			FallingObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.FallingObject1) { //MAKE INTO MOUSE 
				removeObject(tempObject);
				i--;
			}
		}
	}
	
	public void addObject(FallingObject object) {
		this.object.add(object);
	}
	
	public void removeObject(FallingObject object) {
		this.object.remove(object);
	}
}
