package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import _main_.GamePanel;
import _main_.KeyHandler;
import entity.Player;
import maps.Map;

public class GUI {
	////////////////////////////////////////////////////////////
	GamePanel gp;
	KeyHandler keyH;
	Player player;
	Map map;
	
	private BufferedImage titleImg, hudImg, menuImg, battleImg, gameOverImg, endingImg;
	private BufferedImage mapImg, areaImg, optionImg, playerTurnImg;
	private BufferedImage healthFull, healthHalf, healthEmpty;
	
	private String mapPath, areaPath;
	private String itemFormat, luminiteFormat;
	
	private int itemNumber, luminiteNumber;
	private int itemLength, luminiteLength;
	public int optionState, playerTurnState;
	
	////////////////////////////////////////////////////////////
	public GUI(GamePanel gp, KeyHandler keyH, Player player, Map map) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		this.map = map;
		
		setDefaultValues();
		getGUIImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		gp.gameState = 1;
		optionState = 1;
		
		itemNumber = 2;
		luminiteNumber = 6;
	}
	////////////////////////////////////////////////////////////
	public void getGUIImage() {
		try {
			//TITLE
			titleImg = ImageIO.read(getClass().getResourceAsStream("/title/title.png"));
			//HUD
			hudImg = ImageIO.read(getClass().getResourceAsStream("/hud/game-hud.png"));
			//MENU
			menuImg = ImageIO.read(getClass().getResourceAsStream("/menu/menu.png"));
			//BATTLE
			battleImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle.png"));
			//ENDING
			endingImg = ImageIO.read(getClass().getResourceAsStream("/ending/ending.png"));
			//GAMEOVER
			gameOverImg = ImageIO.read(getClass().getResourceAsStream("/gameOver/gameOver.png"));
			
			//HEALTH
			healthFull = ImageIO.read(getClass().getResourceAsStream("/hud/healthFull.png"));
			healthHalf = ImageIO.read(getClass().getResourceAsStream("/hud/healthHalf.png"));
			healthEmpty = ImageIO.read(getClass().getResourceAsStream("/hud/healthEmpty.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getTitleOptionImage() {
		try {
			switch(optionState) {
			//START
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/startSelect.png")); break;
			//CONTINUE
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/continueSelect.png")); break;
			//EXIT
			case 3: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/exitSelect.png")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getMapImage() {
		mapPath = "/map/map-" + map.currentArea + map.currentSubArea + ".png";
		areaPath = "/area/" + map.currentArea + ".png";
		
		try {
			//MAP
			mapImg = ImageIO.read(getClass().getResourceAsStream(mapPath));
			//AREA
			areaImg = ImageIO.read(getClass().getResourceAsStream(areaPath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getMenuOptionImage() {
		try {
			switch(optionState) {
			//RESUME
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/menu/resumeSelect.png")); break;
			//CRYSTALS
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/menu/crystalSelect.png")); break;
			//SETTINGS
			case 3: optionImg = ImageIO.read(getClass().getResourceAsStream("/menu/settingSelect.png")); break;
			//EXIT
			case 4: optionImg = ImageIO.read(getClass().getResourceAsStream("/menu/exitSelect")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getBattleOptionImage() {
		try {
			switch(optionState) {
			//ATTACK
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/attackSelect.png")); break;
			//SKILL
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/skillSelect.png")); break;
			//POTION
			case 3: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/potionSelect.png")); break;
			//RUN
			case 4: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/runSelect")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getTurnOptionImage() {
		try {
			switch(playerTurnState) {
			//WARRIOR
			case 1: playerTurnImg = ImageIO.read(getClass().getResourceAsStream("/battle/warriorIcon.png")); break;
			//ARCHER
			case 2: playerTurnImg = ImageIO.read(getClass().getResourceAsStream("/battle/archerIcon.png")); break;
			//WIZARD
			case 3: playerTurnImg = ImageIO.read(getClass().getResourceAsStream("/battle/wizardIcon.png")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void drawHudHealth(Graphics2D g2) {
		if(player.hp <= 100 && player.hp > 80) {
			g2.drawImage(healthFull, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			
			if(player.hp <= 90) {
				g2.drawImage(healthHalf, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
			} else {
				g2.drawImage(healthFull, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
			}
		}
		
		if(player.hp <= 80 && player.hp > 60) {
			g2.drawImage(healthFull, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			
			if(player.hp <= 70) {
				g2.drawImage(healthHalf, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			} else {
				g2.drawImage(healthFull, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			}
			
			g2.drawImage(healthEmpty, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
		}
		
		if(player.hp <= 60 && player.hp > 40) {
			g2.drawImage(healthFull, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthFull, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			
			if(player.hp <= 50) {
				g2.drawImage(healthHalf, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			} else {
				g2.drawImage(healthFull, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			}
			
			g2.drawImage(healthEmpty, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
		}
		
		if(player.hp <= 40 && player.hp > 20) {
			g2.drawImage(healthFull, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			
			if(player.hp <= 30) {
				g2.drawImage(healthHalf, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			} else {
				g2.drawImage(healthFull, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			}
			
			g2.drawImage(healthEmpty, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
		}
		
		if(player.hp <= 20 && player.hp > 0) {
			if(player.hp <= 10) {
				g2.drawImage(healthHalf, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			} else {
				g2.drawImage(healthFull, 240, 672, gp.TILESIZE, gp.TILESIZE, null);
			}

			g2.drawImage(healthEmpty, 288, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 336, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 384, 672, gp.TILESIZE, gp.TILESIZE, null);
			g2.drawImage(healthEmpty, 432, 672, gp.TILESIZE, gp.TILESIZE, null);
		}
	}
	////////////////////////////////////////////////////////////
	public void drawHudNumbers(Graphics2D g2) {
		itemFormat = "";
		luminiteFormat = "";
		itemLength = String.valueOf(player.potionAmount).length();
		luminiteLength = String.valueOf(player.luminiteAmount).length();
		
		for(int i = 0; i < itemNumber - itemLength; i++) {
			itemFormat += "0";
		}
		itemFormat += player.potionAmount;
				
		for(int i = 0; i < luminiteNumber - luminiteLength; i++) {
			luminiteFormat += "0";
		}
		luminiteFormat += player.luminiteAmount;
		
		g2.setFont(new Font("Alkhemikal", Font.PLAIN, 48)); 
		g2.setColor(Color.white);
		
		g2.drawString(itemFormat, 615, 708);
		g2.drawString(luminiteFormat, 732, 708);
	}
	////////////////////////////////////////////////////////////
	public void update() {		
		if(gp.gameState == gp.TITLE_STATE) {
			getTitleOptionImage();
			
			if(keyH.upPressed == true && optionState > 1) {
				optionState--;
			}
			
			if(keyH.downPressed == true && optionState < 3) {
				optionState++;
			}
			
			if(keyH.enterPressed == true) {
				switch(optionState) {
				case 1: gp.gameState = 2; break;
				case 2: gp.gameState = 2; break; //save logic goes here
				case 3: gp.gameState = 0; break; //exits game
				}
			}
		}
		
		if(gp.gameState == gp.MENU_STATE) {
			getMenuOptionImage();
			
			if(keyH.upPressed == true && optionState > 1) {
				optionState--;
			}
			
			if(keyH.downPressed == true && optionState < 4) {
				optionState++;
			}
			
			if(keyH.enterPressed == true) {
				switch(optionState) {
				//RESUME
				case 1: gp.gameState = 2; break;
				//CRYSTALS
				case 2: gp.gameState = 3; break; 
				//SETTINGS
				case 3: gp.gameState = 0; break; 
				//EXIT
				case 4: gp.gameState = 1; break;
				}
			}
		}
		
		if(gp.gameState == gp.BATTLE_STATE) {
			getBattleOptionImage();
			getTurnOptionImage();
			
			
		}
		
		if(gp.gameState == gp.ENDING_STATE) {
			if(keyH.enterPressed == true) {
				gp.gameState = 1;
			}
		}
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {
		if(gp.gameState == gp.TITLE_STATE) {
			g2.drawImage(titleImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		
		if(gp.gameState == gp.PLAY_STATE) {
			g2.drawImage(hudImg, 0, 0, 960, 720, null);
			g2.drawImage(mapImg, 0, 0, 960, 720, null);
			g2.drawImage(areaImg, 0, 0, 960, 720, null);
			drawHudHealth(g2);
			drawHudNumbers(g2);
		}
		
		if(gp.gameState == gp.MENU_STATE) {
			g2.drawImage(menuImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		
		if(gp.gameState == gp.BATTLE_STATE) {
			g2.drawImage(battleImg, 0, 0, 960, 720, null);
			g2.drawImage(playerTurnImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		
		if(gp.gameState == gp.GAME_OVER_STATE) {
			g2.drawImage(gameOverImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		
		if(gp.gameState == gp.ENDING_STATE) {
			g2.drawImage(endingImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
	}
	////////////////////////////////////////////////////////////
}
