package _main_;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import battle.Battle;
import entity.Enemy;
import entity.Player;
import gui.GUI;
import maps.Map;

public class GamePanel extends JPanel implements Runnable {
	////////////////////////////////////////////////////////////
	//SCREEN RESOLUTION
	final int ORIGINAL_TILESIZE = 16;
	final int scale = 3;
	
	public final int TILESIZE = ORIGINAL_TILESIZE * scale;
	final int SCREEN_COL = 20;
	final int SCREEN_ROW = 15;
	final int SCREEN_WIDTH = TILESIZE * SCREEN_COL;
	final int SCREEN_HEIGHT = TILESIZE * SCREEN_ROW;
	
	//FRAMES PER SECOND
	final int FPS = 60;
	
	//GAME STATE
	public int gameState = 1;
	
	public final int TITLE_STATE = 1;
	public final int PLAY_STATE = 2;
	public final int MENU_STATE = 3;
	public final int SETTING_STATE = 4;
	public final int BATTLE_STATE = 5;
	public final int GAME_OVER_STATE = 6;
	public final int ENDING_STATE = 7;
	////////////////////////////////////////////////////////////
	Thread gameThread;
		
	KeyHandler keyH = new KeyHandler(this);
					
	Player player = new Player(this, keyH);
	
	Enemy enemy = new Enemy();
			
	Map map = new Map(this, player);
		
	GUI gui = new GUI(this, keyH, player, map);
	
	Battle battle = new Battle(this, keyH, gui, player, enemy);
	////////////////////////////////////////////////////////////
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	////////////////////////////////////////////////////////////
	public void start() {
		gameThread = new Thread(this);
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
		if(gameState == TITLE_STATE) {
			gui.update();
		}
		
		if(gameState == PLAY_STATE) {
			gui.update();
			map.update();
			player.update();
		}
		
		if(gameState == MENU_STATE) {
			gui.update();
		}
		
		if(gameState == SETTING_STATE) {
			gui.update();
		}
		
		if(gameState == BATTLE_STATE) {
			gui.update();
		}
		
		if(gameState == GAME_OVER_STATE) {
			gui.update();
		}
		
		if(gameState == ENDING_STATE) {
			gui.update();
		}
	}
	////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
				
		if(gameState == TITLE_STATE) {
			gui.draw(g2);
		}
		
		if(gameState == PLAY_STATE) {
			gui.draw(g2);
			player.draw(g2);
		}
		
		if(gameState == MENU_STATE) {
			gui.draw(g2);
		}
		
		if(gameState == SETTING_STATE) {
			gui.draw(g2);
		}
		
		if(gameState == BATTLE_STATE) {
			gui.draw(g2);
		}
		
		if(gameState == GAME_OVER_STATE) {
			gui.draw(g2);
		}
		
		if(gameState == ENDING_STATE) {
			gui.draw(g2);
		}
		
		g2.dispose();
	}
	////////////////////////////////////////////////////////////
}
