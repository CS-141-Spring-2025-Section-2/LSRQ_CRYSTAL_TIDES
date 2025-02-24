package map;

import java.awt.Graphics2D;

import _main_.GamePanel;
import _main_.KeyHandler;
import _main_.SoundManager;
import entity.Enemy;
import entity.Player;

public class Map { 
	////////////////////////////////////////////////////////////
	GamePanel gp;
	KeyHandler keyH;
	SoundManager soundM;
	Player player;
	Enemy enemy;
	
	//AREA
	public String currentArea;
	public int currentSubArea;
	
	//BORDERS
	private int topBorder, bottomBorder, leftBorder, rightBorder;
	
	//OFFSET
	private int OFFSET;
	
	//CRYSTAL
	public boolean warriorPlaced, archerPlaced, wizardPlaced;
	////////////////////////////////////////////////////////////
	public Map(GamePanel gp, KeyHandler keyH, SoundManager soundM, Player player, Enemy enemy) {
		this.gp = gp;
		this.keyH = keyH;
		this.soundM = soundM;
		this.player = player;
		this.enemy = enemy;
		
		setDefaultValues();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		//AREA
		currentArea = "Town";
		currentSubArea = 1;
		
		//Y_AXIS
		topBorder = gp.TILESIZE;
		bottomBorder = 13 * gp.TILESIZE;
		
		//X_AXIS
		leftBorder = gp.TILESIZE;
		rightBorder = 19 * gp.TILESIZE;
		
		//OFFSET
		OFFSET = gp.TILESIZE / 2;
		
		//CRYSTAL
		warriorPlaced = false;
		archerPlaced = false;
		wizardPlaced = false;
	}
	////////////////////////////////////////////////////////////
	public void update() {
		switch(currentArea) {
		case "Town": town(); break;
		case "Field": field(); break;
		case "Coast": coast(); break;
		case "Cove": cove(); break;
		}
	}
	////////////////////////////////////////////////////////////
	public void getBorders() {
		if(player.playerTop == topBorder) {
			player.y += player.speed;
		}
		
		if(player.playerBottom == bottomBorder) {
			player.y -= player.speed;
		}
		
		if(player.playerLeft == leftBorder) {
			player.x += player.speed;
		}
		
		if(player.playerRight == rightBorder) {
			player.x -= player.speed;
		}
	}
	////////////////////////////////////////////////////////////
	public void town() {
		switch(currentSubArea) {
		//TOWN 1
		case 1: 
			////////////////////////////////////////////////////////////
			//BOUNDS
			if(player.playerOriginY < (8 * gp.TILESIZE) && player.playerOriginY > (7 * gp.TILESIZE) && player.playerOriginX > (4 * gp.TILESIZE) && player.playerOriginX < (6 * gp.TILESIZE) && player.direction == "up") {
				player.y += player.speed;
			}
				
			if(player.playerOriginY < (9 * gp.TILESIZE) && player.playerOriginY > (8 * gp.TILESIZE) && player.playerOriginX > (6 * gp.TILESIZE) && player.direction == "up") {
				player.y += player.speed;
			}
			
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (2 * gp.TILESIZE) && player.playerOriginX < (3 * gp.TILESIZE) && player.playerOriginY > (2 * gp.TILESIZE) && player.playerOriginY < (3 * gp.TILESIZE) && player.direction == "up") {
				if(keyH.enterPressed == true && player.isChest1Open == false) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.potionAmount += 6;
					player.luminiteAmount += 300;
					player.isChest1Open = true;
				}
			}
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 2;
				player.x = gp.TILESIZE + OFFSET;
			} 
			
			if(player.playerBottom == bottomBorder && player.direction == "down" && player.playerOriginX < (2 * gp.TILESIZE)) {
				currentArea = "Field";
				currentSubArea = 1;
				player.y = OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//TOWN 2
		case 2:
			////////////////////////////////////////////////////////////
			//BOUNDS
			if(player.playerOriginY < (9 * gp.TILESIZE)) {
				player.y += player.speed;
			}
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (3 * gp.TILESIZE) && player.playerOriginX < (4 * gp.TILESIZE) && player.playerOriginY > (8 * gp.TILESIZE) && player.playerOriginY < (10 * gp.TILESIZE) && player.direction == "up") {
				if(keyH.enterPressed) {
					currentSubArea = 4;
					player.x = ((20 * gp.TILESIZE) / 2) - OFFSET;
					player.y = (8 * gp.TILESIZE);
				}
			}
			
			if(player.playerBottom == bottomBorder && player.direction == "down" && player.playerOriginX > (6 * gp.TILESIZE) && player.playerOriginX < (8 * gp.TILESIZE)) {
				currentArea = "Field";
				currentSubArea = 2;
				player.y = OFFSET;
			}
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 1;
				player.x = (18 * gp.TILESIZE) - OFFSET;
			}
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 3;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//TOWN 3
		case 3:
			////////////////////////////////////////////////////////////
			//BOUNDS
			if(player.playerOriginY > (7 * gp.TILESIZE) && player.playerOriginY < (9 * gp.TILESIZE) && player.playerOriginX < (16 * gp.TILESIZE)) {
				player.y += player.speed;
			}
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (17 * gp.TILESIZE) && player.playerOriginX < (18 * gp.TILESIZE) && player.playerOriginY > (4 * gp.TILESIZE) && player.playerOriginY < (5 * gp.TILESIZE) && player.direction == "up") {
				if(keyH.enterPressed == true && player.isChest2Open == false) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.potionAmount += 3;
					player.luminiteAmount += 150;
					player.isChest2Open = true;
				}
			}
			
			if(player.playerBottom == bottomBorder && player.direction == "down") {
				currentArea = "Field";
				currentSubArea = 3;
				player.y = OFFSET;
			}
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 2;
				player.x = (18 * gp.TILESIZE) - OFFSET;
			}
			
			if(player.playerRight == (16 * gp.TILESIZE) && player.direction == "right") {
				currentArea = "Cove";
				currentSubArea = 1;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//TOWN 4
		case 4:
			if(player.playerOriginX > (9 * gp.TILESIZE) && player.playerOriginX < (11 * gp.TILESIZE) && player.playerOriginY > (6 * gp.TILESIZE) && player.playerOriginY < (8 * gp.TILESIZE)) {
				if(keyH.enterPressed == true && player.isArcherCrystal == false) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.isArcherCrystal = true;
				}
			}
			
			if(player.playerOriginX > (9 * gp.TILESIZE) && player.playerOriginX < (11 * gp.TILESIZE) && player.playerOriginY > (8 * gp.TILESIZE) && player.playerOriginY < (10 * gp.TILESIZE) && player.direction == "down") {
				if(keyH.enterPressed) {
					currentSubArea = 2;
					player.x = (3 * gp.TILESIZE);
				}
			}
			
			if(player.playerTop == topBorder + (4 * gp.TILESIZE)) {
				player.y += player.speed;
			}
			
			if(player.playerBottom == bottomBorder - (3 * gp.TILESIZE)) {
				player.y -= player.speed;
			}
			
			if(player.playerLeft == leftBorder + (2 * gp.TILESIZE)) {
				player.x += player.speed;
			}
			
			if(player.playerRight == rightBorder - (2 * gp.TILESIZE)) {
				player.x -= player.speed;
			}
		}
	}
	////////////////////////////////////////////////////////////
	public void field() {
		switch(currentSubArea) {
		//FIELD 1
		case 1:
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (11 * gp.TILESIZE) && player.playerOriginX < (12 * gp.TILESIZE) && player.playerOriginY > (2 * gp.TILESIZE) && player.playerOriginY < (3 * gp.TILESIZE) && enemy.isOrc1Dead == false) {
				soundM.changeMusic("res/audio/battle.wav");
				player.battleState = 1;
				gp.gameState = 3;
			}

			if(player.playerOriginX > (7 * gp.TILESIZE) && player.playerOriginX < (8 * gp.TILESIZE) && player.playerOriginY > (6 * gp.TILESIZE) && player.playerOriginY < (7 * gp.TILESIZE) && enemy.isOrc2Dead == false) {
				soundM.changeMusic("res/audio/battle.wav");
				player.battleState = 2;
				gp.gameState = 3;

			}
			
			if(player.playerOriginX > (11 * gp.TILESIZE) && player.playerOriginX < (12 * gp.TILESIZE) && player.playerOriginY > (10 * gp.TILESIZE) && player.playerOriginY < (11 * gp.TILESIZE) && enemy.isOrc3Dead == false) {
				soundM.changeMusic("res/audio/battle.wav");
				player.battleState = 3;
				gp.gameState = 3;
			}
			
			if(player.playerOriginX > (11 * gp.TILESIZE) && player.playerOriginX < (12 * gp.TILESIZE) && player.playerOriginY > (6 * gp.TILESIZE) && player.playerOriginY < (7 * gp.TILESIZE)) {
				if(keyH.enterPressed == true && player.isWarriorCrystal == false && enemy.isOrcTribeDead == true) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.isWarriorCrystal = true;
				}
			}
			
			if(player.playerTop == topBorder && player.direction == "up") {
				currentArea = "Town";
				currentSubArea = 1;
				player.y = (11 * gp.TILESIZE) - OFFSET;
			}
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 2;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//FIELD 2
		case 2:
			////////////////////////////////////////////////////////////
			if(player.playerTop == topBorder && player.direction == "up") {
				currentArea = "Town";
				currentSubArea = 2;
				player.y = (11 * gp.TILESIZE) - OFFSET;
			}
			
			if(player.playerBottom == bottomBorder && player.direction == "down") {
				currentArea = "Coast";
				currentSubArea = 2;
				player.y = 0;
			}
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 1;
				player.x = (18 * gp.TILESIZE) - OFFSET;
			} 
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 3;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//FIELD 3
		case 3:
			////////////////////////////////////////////////////////////
			if(player.playerTop == topBorder && player.direction == "up") {
				currentArea = "Town";
				currentSubArea = 3;
				player.y = (11 * gp.TILESIZE) - OFFSET;
			} 
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 2;
				player.x = (16 * gp.TILESIZE) - OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		}
	}
	////////////////////////////////////////////////////////////
	public void coast() {
		switch(currentSubArea) {
		//COAST 1
		case 1:
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (10 * gp.TILESIZE) && player.playerOriginX < (11 * gp.TILESIZE) && player.playerOriginY > (3 * gp.TILESIZE) && player.playerOriginY < (4 * gp.TILESIZE) && player.direction == "up") {
				if(keyH.enterPressed == true && player.isChest3Open == false) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.potionAmount += 9;
					player.luminiteAmount += 450;
					player.isChest3Open = true;
				}
			}
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 2;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//COAST 2
		case 2:
			////////////////////////////////////////////////////////////
			if(player.playerTop == topBorder && player.direction == "up") {
				currentArea = "Field";
				currentSubArea = 2;
				player.y = (11 * gp.TILESIZE) - OFFSET;
			}
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 1;
				player.x = (18 * gp.TILESIZE) - OFFSET;
			} 
			
			if(player.playerRight == rightBorder && player.direction == "right") {
				currentSubArea = 3;
				player.x = gp.TILESIZE + OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		//COAST 3
		case 3:
			////////////////////////////////////////////////////////////
			if(player.playerOriginX > (11 * gp.TILESIZE) && player.playerOriginX < (12 * gp.TILESIZE) && player.playerOriginY > (5 * gp.TILESIZE) && player.playerOriginY < (7 * gp.TILESIZE) && enemy.isElf1Dead == false) {
				soundM.changeMusic("res/audio/battle.wav");
				player.battleState = 4;
				gp.gameState = 3;
			}
			
			if(player.playerOriginX > (13 * gp.TILESIZE) && player.playerOriginX < (14 * gp.TILESIZE) && player.playerOriginY > (5 * gp.TILESIZE) && player.playerOriginY < (7 * gp.TILESIZE) && enemy.isElf2Dead == false) {
				soundM.changeMusic("res/audio/battle.wav");
				player.battleState = 5;
				gp.gameState = 3;
			}
			
			if(player.playerOriginX > (15 * gp.TILESIZE) && player.playerOriginX < (17 * gp.TILESIZE) && player.playerOriginY > (5 * gp.TILESIZE) && player.playerOriginY < (7 * gp.TILESIZE)) {
				if(keyH.enterPressed == true && player.isWizardCrystal == false && enemy.isElfCultDead == true) {
					keyH.enterPressed = false;
					soundM.playSoundEffect("res/audio/select.wav");
					player.isWizardCrystal = true;
				}
			}
			
			if(player.playerLeft == leftBorder && player.direction == "left") {
				currentSubArea = 2;
				player.x = (18 * gp.TILESIZE) - OFFSET;
			} else {
				getBorders();
			}
			////////////////////////////////////////////////////////////
			break;
			////////////////////////////////////////////////////////////
		}
	}
	////////////////////////////////////////////////////////////
	public void cove() {
		////////////////////////////////////////////////////////////
		if(player.playerOriginX > (6 * gp.TILESIZE) && player.playerOriginX < (8 * gp.TILESIZE) && player.playerOriginY > (9 * gp.TILESIZE) && player.playerOriginY < (11 * gp.TILESIZE)) {
			if(keyH.enterPressed && player.isWizardCrystal == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				wizardPlaced = true;
			}
		}
		
		if(player.playerOriginX > (9 * gp.TILESIZE) && player.playerOriginX < (11 * gp.TILESIZE) && player.playerOriginY > (9 * gp.TILESIZE) && player.playerOriginY < (11 * gp.TILESIZE)) {
			if(keyH.enterPressed && player.isWarriorCrystal == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				warriorPlaced = true;
			}
		}
		
		if(player.playerOriginX > (12 * gp.TILESIZE) && player.playerOriginX < (14 * gp.TILESIZE) && player.playerOriginY > (9 * gp.TILESIZE) && player.playerOriginY < (11 * gp.TILESIZE)) {
			if(keyH.enterPressed && player.isArcherCrystal == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				archerPlaced = true;
			}
		}
		
		if(wizardPlaced == true && warriorPlaced == true && archerPlaced == true) {
			soundM.changeMusic("res/audio/youwin.wav");
			gp.gameState = 5;
		}
		
		if(player.playerLeft == leftBorder && player.direction == "left") {
			currentArea = "Town";
			currentSubArea = 3;
			player.x = (15 * gp.TILESIZE) - OFFSET;
		} else {
			getBorders();
		}
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
}
