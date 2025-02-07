package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import menus.HUD;
import menus.Map;

public class GamePanel extends JPanel implements Runnable{
	////////////////////////////////////////////////////////////
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 20;
	final int maxScreenRow = 15;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	////////////////////////////////////////////////////////////
	Thread gameThread;
	
	KeyHandler keyH = new KeyHandler();
	
	public CollisionChecker CollisionC = new CollisionChecker(this);
	
	HUD hud = new HUD();
	
	Map map = new Map();
	
	Player player = new Player(this, keyH);
	////////////////////////////////////////////////////////////
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	////////////////////////////////////////////////////////////
	public void start() {
		gameThread = new Thread(this); //pass GamePanel to Thread()
		gameThread.start();
	}
	////////////////////////////////////////////////////////////
	public void run() {
		double drawInterval = 1_000_000_000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {
		hud.update();
		map.update();
		player.update();
	}
	////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		hud.draw(g2);
		map.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	////////////////////////////////////////////////////////////
}
