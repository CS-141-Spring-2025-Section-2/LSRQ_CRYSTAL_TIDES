package _main_;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import battle.Battle;
import entity.Enemy;
import entity.Player;
import gui.GUI;
import map.Map;

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
	
	public final int TUTORIAL_STATE = 0;
	public final int TITLE_STATE = 1;
	public final int PLAY_STATE = 2;
	public final int BATTLE_STATE = 3;
	public final int GAME_OVER_STATE = 4;
	public final int ENDING_STATE = 5;
	
	////////////////////////////////////////////////////////////
	Thread gameThread;
		
	KeyHandler keyH = new KeyHandler(this);

	SoundManager soundM = new SoundManager();
					
	Player player = new Player(this, keyH);
	
	Enemy enemy = new Enemy();
	
	Map map = new Map(this, keyH, soundM, player, enemy);
			
	Battle battle = new Battle(this, keyH, soundM, player, enemy, map);
	
	GUI gui = new GUI(this, keyH, soundM, player, enemy, map, battle);
	////////////////////////////////////////////////////////////
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		soundM.playMusic("res/audio/title.wav");
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
		////////////////////////////////////////////////////////////
		//TUTORIAL STATE
		if(gameState == TUTORIAL_STATE) {
			gui.update();
		}
		////////////////////////////////////////////////////////////
		//TITLE STATE
		if(gameState == TITLE_STATE) {
			gui.update();
		}
		////////////////////////////////////////////////////////////
		//PLAY STATE
		if(gameState == PLAY_STATE) {
			gui.update();
			map.update();
			player.update();
			enemy.update();
		}
		////////////////////////////////////////////////////////////
		//BATTLE STATE
		if(gameState == BATTLE_STATE) {
			gui.update();
		}
		////////////////////////////////////////////////////////////
		//GAMEOVER STATE
		if(gameState == GAME_OVER_STATE) {
			gui.update();
		}
		////////////////////////////////////////////////////////////
		//ENDING STATE
		if(gameState == ENDING_STATE) {
			gui.update();
		}
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		////////////////////////////////////////////////////////////
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		////////////////////////////////////////////////////////////
		//TUTORIAL STATE
		if(gameState == TUTORIAL_STATE) {
			gui.draw(g2);
		}
		////////////////////////////////////////////////////////////
		//TITLE STATE
		if(gameState == TITLE_STATE) {
			gui.draw(g2);
		}
		////////////////////////////////////////////////////////////
		//PLAY STATE
		if(gameState == PLAY_STATE) {
			gui.draw(g2);
			g2.drawImage(gui.mapUnderImg, 0, 0, 960, 720, null);
			gui.drawEntityImage(g2);
			player.draw(g2);
			g2.drawImage(gui.mapUpperImg, 0, 0, 960, 720, null);
		}
		////////////////////////////////////////////////////////////
		//BATTLE STATE
		if(gameState == BATTLE_STATE) {
			gui.draw(g2);
			battle.startBattle(player.battleState);
		}
		////////////////////////////////////////////////////////////
		//GAME OVER STATE
		if(gameState == GAME_OVER_STATE) {
			gui.draw(g2);
		}
		////////////////////////////////////////////////////////////
		//ENDING STATE
		if(gameState == ENDING_STATE) {
			gui.draw(g2);
		}
		////////////////////////////////////////////////////////////
		g2.dispose();
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
}
