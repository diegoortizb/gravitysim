package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Sim extends Canvas implements Runnable{

	private static final long serialVersionUID = -2357570657394423119L;	
	//12 * 9 means it is giving it a good aspect ratio
	public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread thread;
	private boolean running = false;
		
	//used to deallocate objects spawns
	private Handler handler;
	
	//spawning falling objects
	private Spawn spawner;
	
	public Sim() {
		handler = new Handler();
		
		new Window(WIDTH, HEIGHT, "Gravity Sim", this);
		
		spawner = new Spawn(handler);
	}
	
	
	
	public synchronized void start() { 
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void tick() {
		handler.tick();
		spawner.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//editing the background
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		handler.render(g);	
		spawner.displayNumObjects(g);
		g.dispose();
		bs.show(); 
		
	}
	
	public synchronized void stop() { //this in case a bug happens
		try {
			thread.join(); //stopping tread, killing the game
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			
			if (running) {
				render();
			}
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	
	
	
	public static void main(String[] args) {
		new Sim();
	}
	
}
