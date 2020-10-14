package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	
	protected float x, y;
	protected ID id;
	
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
				
	}
	
	public void mouseEntered(MouseEvent e) {
				
	}
	
	public void mouseExited(MouseEvent e) {
				
	}


}
