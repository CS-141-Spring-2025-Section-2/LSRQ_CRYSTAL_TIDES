package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import _main_.GamePanel;
import _main_.KeyHandler;
import _main_.SoundManager;
import battle.Battle;
import entity.Enemy;
import entity.Player;
import map.Map;

public class GUI {
	////////////////////////////////////////////////////////////
	GamePanel gp;
	KeyHandler keyH;
	SoundManager soundM;
	Player player;
	Enemy enemy;
	Map map;
	Battle battle;
	
	private BufferedImage titleImg, tutorialImg, hudImg, battleImg, gameOverImg, endingImg;
	private BufferedImage optionImg, turnImg;
	private BufferedImage warriorImg, archerImg, wizardImg;
	private BufferedImage warriorStatusImg, archerStatusImg, wizardStatusImg;
	private BufferedImage healthFull, healthEmpty, battleHealthFull, battleHealthEmpty;
	private BufferedImage skillCharge1, skillCharge2, skillCharge3;
	
	public BufferedImage chestClosedImg, chestOpenImg, crystalImg;
	public BufferedImage mapUnderImg, mapUpperImg;
	
	private String mapUnderPath, mapUpperPath;
	private String itemFormat, luminiteFormat;
	
	private int itemLength, luminiteLength;
	public int optionState, battleState;
	
	private BufferedImage cat;
	private int meow;
	private boolean meowCat;
	
	Random random = new Random();
	
	////////////////////////////////////////////////////////////
	public GUI(GamePanel gp, KeyHandler keyH, SoundManager soundM, Player player, Enemy enemy, Map map, Battle battle) {
		this.gp = gp;
		this.keyH = keyH;
		this.soundM = soundM;
		this.player = player;
		this.enemy = enemy;
		this.map = map;
		this.battle = battle;
		
		setDefaultValues();
		getGUIImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		gp.gameState = 1;
		optionState = 1;
		battleState = 1;
	}
	////////////////////////////////////////////////////////////
	public void getGUIImage() {
		try {
			//TITLE
			titleImg = ImageIO.read(getClass().getResourceAsStream("/title/title.png"));
			//TUTORIAL
			tutorialImg = ImageIO.read(getClass().getResourceAsStream("/tutorial/tutorial.png"));
			//HUD
			hudImg = ImageIO.read(getClass().getResourceAsStream("/hud/hud.png"));
			//BATTLE
			battleImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle.png"));
			//ENDING
			endingImg = ImageIO.read(getClass().getResourceAsStream("/ending/ending.png"));
			//GAMEOVER
			gameOverImg = ImageIO.read(getClass().getResourceAsStream("/gameOver/gameOver.png"));
			//CAT
			cat = ImageIO.read(getClass().getResourceAsStream("/easteregg/sillycateasteregg.png"));
			
			//HEALTH
			healthFull = ImageIO.read(getClass().getResourceAsStream("/hud/hud_heartFull.png"));
			healthEmpty = ImageIO.read(getClass().getResourceAsStream("/hud/hud_heartEmpty.png"));
			
			//MISC
			chestClosedImg = ImageIO.read(getClass().getResourceAsStream("/misc/chest_closed.png"));
			chestOpenImg = ImageIO.read(getClass().getResourceAsStream("/misc/chest_open.png"));
			crystalImg = ImageIO.read(getClass().getResourceAsStream("/misc/crystal.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	//TUTORIAL STATE
	////////////////////////////////////////////////////////////
	public void getTutorialOptionImage() {
		try {
			optionImg = ImageIO.read(getClass().getResourceAsStream("/tutorial/tutorial_exitSelect.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	//TITLE STATE
	////////////////////////////////////////////////////////////
	public void getTitleOptionImage() {
		try {
			switch(optionState) {
			//START
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/title_startSelect.png")); break;
			//TUTORIAL
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/title_tutorialSelect.png")); break;
			//EXIT
			case 3: optionImg = ImageIO.read(getClass().getResourceAsStream("/title/title_exitSelect.png")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	//PLAY STATE
	////////////////////////////////////////////////////////////
	public void drawHudValues(Graphics2D g2) {
		////////////////////////////////////////////////////////////
		//FONT
		g2.setFont(new Font("Alkhemikal", Font.PLAIN, 48)); 
		g2.setColor(Color.white);
		////////////////////////////////////////////////////////////
		//AREA
		g2.drawString(map.currentArea, 63, 710);
		////////////////////////////////////////////////////////////
		//HEALTH
		for(int i = 1; i < 4; i++) {
			if(player.hp >= i) {
				g2.drawImage(healthFull, 288 + ((i - 1) * gp.TILESIZE), 672, 48, 48, null);
			} else {
				g2.drawImage(healthEmpty, 288 + ((i - 1) * gp.TILESIZE), 672, 48, 48, null);
			}
		}
		////////////////////////////////////////////////////////////
		//ITEMS
		itemFormat = "";
		itemLength = String.valueOf(player.potionAmount).length();

		for(int i = 0; i < 2 - itemLength; i++) {
			itemFormat += "0";
		}
		itemFormat += player.potionAmount;
		
		g2.drawString(itemFormat, 591, 708);
		////////////////////////////////////////////////////////////	
		//LUMINITE
		luminiteFormat = "";
		luminiteLength = String.valueOf(player.luminiteAmount).length();
		
		for(int i = 0; i < 4 - luminiteLength; i++) {
			luminiteFormat += "0";
		}
		luminiteFormat += player.luminiteAmount;
		
		g2.drawString(luminiteFormat, 783, 708);
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
	public void getMapImage() {
		mapUnderPath = "/mapUnder/" + map.currentArea + map.currentSubArea + ".png";
		mapUpperPath = "/mapUpper/" + map.currentArea + map.currentSubArea + ".png";
		
		try {
			//MAP UNDER
			mapUnderImg = ImageIO.read(getClass().getResourceAsStream(mapUnderPath));
			//MAP UPPER
			mapUpperImg = ImageIO.read(getClass().getResourceAsStream(mapUpperPath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void drawEntityImage(Graphics2D g2) {
		////////////////////////////////////////////////////////////
		//TOWN
		if(map.currentArea == "Town") {
			switch(map.currentSubArea) {
			case 1:
				drawChest(g2, player.isChest1Open, (2 * gp.TILESIZE), gp.TILESIZE); 
				break;
			case 3:
				drawChest(g2, player.isChest2Open, (17 * gp.TILESIZE), (3 * gp.TILESIZE));
				break;
			case 4:
				if(player.isArcherCrystal == false) {
					drawCrystal(g2, true, (10 * gp.TILESIZE) - (gp.TILESIZE / 2), (6 * gp.TILESIZE) + (gp.TILESIZE / 2));
				}
			}
		}
		////////////////////////////////////////////////////////////
		//FIELD
		if(map.currentArea == "Field") {
			switch(map.currentSubArea) {
			case 1:
				if(player.isWarriorCrystal == false) {
					drawCrystal(g2, enemy.isOrcTribeDead, (11 * gp.TILESIZE), (6 * gp.TILESIZE));
				}
				drawMonster(g2, enemy.orcFront, enemy.isOrc1Dead, (11 * gp.TILESIZE), (2 * gp.TILESIZE));
				drawMonster(g2, enemy.orcRight, enemy.isOrc2Dead, (7 * gp.TILESIZE), (6 * gp.TILESIZE));
				drawMonster(g2, enemy.orcBack, enemy.isOrc3Dead, (11 * gp.TILESIZE), (10 * gp.TILESIZE));
				break;
			}
		}
		////////////////////////////////////////////////////////////
		//COAST
		if(map.currentArea == "Coast") {
			switch(map.currentSubArea) {
			case 1:
				drawChest(g2, player.isChest3Open, (10 * gp.TILESIZE), (2 * gp.TILESIZE));
				break;
			case 3:
				if(player.isWizardCrystal == false) {
					drawCrystal(g2, enemy.isElfCultDead, ((16 * gp.TILESIZE) - (gp.TILESIZE / 2)), ((6 * gp.TILESIZE) - (gp.TILESIZE / 2)));
				}
				drawMonster(g2, enemy.elfFront, enemy.isElf1Dead, (11 * gp.TILESIZE), (4 * gp.TILESIZE));
				drawMonster(g2, enemy.elfBack, enemy.isElf1Dead, (11 * gp.TILESIZE), (7 * gp.TILESIZE));
				drawMonster(g2, enemy.elfFront, enemy.isElf2Dead, (13 * gp.TILESIZE), (4 * gp.TILESIZE));
				drawMonster(g2, enemy.elfBack, enemy.isElf2Dead, (13 * gp.TILESIZE), (7 * gp.TILESIZE));
				break;
			}
		}
		////////////////////////////////////////////////////////////
		//COVE
		if(map.currentArea == "Cove") {
			if(map.wizardPlaced) {
				drawCrystal(g2, player.isWizardCrystal, ((7 * gp.TILESIZE) - (gp.TILESIZE / 2)), ((10 * gp.TILESIZE) - (gp.TILESIZE / 2)));
			}
			
			if(map.warriorPlaced) {
				drawCrystal(g2, player.isWarriorCrystal, ((10 * gp.TILESIZE) - (gp.TILESIZE / 2)), ((10 * gp.TILESIZE) - (gp.TILESIZE / 2)));
			}
			
			if(map.archerPlaced) {
				drawCrystal(g2, player.isArcherCrystal, ((13 * gp.TILESIZE) - (gp.TILESIZE / 2)), ((10 * gp.TILESIZE) - (gp.TILESIZE / 2)));
			}
		}
	}
	////////////////////////////////////////////////////////////
	public void drawChest(Graphics2D g2, boolean chestOpen, int x, int y) {
		if(chestOpen == true) {
			g2.drawImage(chestOpenImg, x, y, gp.TILESIZE, gp.TILESIZE, null);
		} else {
			g2.drawImage(chestClosedImg, x, y, gp.TILESIZE, gp.TILESIZE, null);
		}
	}
	////////////////////////////////////////////////////////////
	public void drawMonster(Graphics2D g2, BufferedImage monster, boolean monsterDead, int x, int y) {
		if(monsterDead == false) {
			g2.drawImage(monster, x, y, gp.TILESIZE, gp.TILESIZE, null);
		}
	}
	////////////////////////////////////////////////////////////
	public void drawCrystal(Graphics2D g2, boolean crystalStatus, int x, int y) {
		if(crystalStatus == true) {
			g2.drawImage(crystalImg, x, y, gp.TILESIZE, gp.TILESIZE, null);
		}
	}
	////////////////////////////////////////////////////////////
	//BATTLE STATE
	////////////////////////////////////////////////////////////
	public void getBattleImage() {
		try {
			battleHealthFull = ImageIO.read(getClass().getResourceAsStream("/battle/battle_heartFull.png"));
			battleHealthEmpty = ImageIO.read(getClass().getResourceAsStream("/battle/battle_heartEmpty.png"));
			
			skillCharge1 = ImageIO.read(getClass().getResourceAsStream("/battle/battle_skillCharge1.png"));
			skillCharge2 = ImageIO.read(getClass().getResourceAsStream("/battle/battle_skillCharge2.png"));
			skillCharge3 = ImageIO.read(getClass().getResourceAsStream("/battle/battle_skillCharge3.png"));

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getBattlePlayerImage() {
		try {
			//WARRIOR
			warriorImg = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-idle1.png"));
			//ARCHER
			archerImg = ImageIO.read(getClass().getResourceAsStream("/archer/archerSideR.png"));
			//WIZARD
			wizardImg = ImageIO.read(getClass().getResourceAsStream("/wizard/wizardSideR.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getBattleOptionImage() {
		try {
			switch(optionState) {
			//ATTACK
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_attackSelect.png")); break;
			//SKILL
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_skillSelect.png")); break;
			//POTION
			case 3: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_potionSelect.png")); break;
			//RUN
			case 4: optionImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_runSelect.png")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getTurnOptionImage() {
		try {
			switch(battle.turnState) {
			//WARRIOR
			case 1: 
				if(battle.isWarriorDead == true) {
					battle.turnState++;
				} else {
					turnImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_warriorIcon.png")); break;
				}
			//ARCHER
			case 2: 
				if(battle.isArcherDead == true) {
					battle.turnState++;
				} else {
					turnImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_archerIcon.png")); break;
				}
			//WIZARD
			case 3: 
				if(battle.isWizardDead == true) {
					battle.turnState++;
				} else {
					turnImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_wizardIcon.png")); break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getBattleStatusImage() {
		try {
			if(battle.isWarriorDead == true) {
				warriorStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleDead.png"));
			} else {
				warriorStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleAlive.png"));
			}
			
			if(battle.isArcherDead == true) {
				archerStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleDead.png"));
			} else {
				archerStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleAlive.png"));
			}
			
			if(battle.isWizardDead == true) {
				wizardStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleDead.png"));
			} else {
				wizardStatusImg = ImageIO.read(getClass().getResourceAsStream("/battle/battle_battleAlive.png"));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void drawBattleEntity(Graphics2D g2) {
		g2.drawImage(warriorImg, 288, 336, 48, 96, null);
		g2.drawImage(archerImg, 192, 336, 48, 96, null);
		g2.drawImage(wizardImg, 96, 336, 48, 96, null);

		
		if(map.currentArea == "Field") {
			g2.drawImage(enemy.orcLeft, 624, 384, 48, 48, null);
			g2.drawImage(enemy.orcLeft, 720, 384, 48, 48, null);
			g2.drawImage(enemy.orcLeft, 816, 384, 48, 48, null);
		}
		
		if(map.currentArea == "Coast") {
			g2.drawImage(enemy.elfLeft, 624, 384, 48, 48, null);
			g2.drawImage(enemy.elfLeft, 720, 384, 48, 48, null);
			g2.drawImage(enemy.elfLeft, 816, 384, 48, 48, null);
		}
	}
	////////////////////////////////////////////////////////////	
	public void drawBattleValues(Graphics2D g2) {
		////////////////////////////////////////////////////////////
		//FONT
		g2.setFont(new Font("Alkhemikal", Font.PLAIN, 48)); 
		g2.setColor(Color.gray);
		////////////////////////////////////////////////////////////
		//HEALTH
		for(int i = 1; i < 4; i++) {
			if(player.warriorHP >= i) {
				g2.drawImage(battleHealthFull, 720 + ((i - 1) * gp.TILESIZE), 528, 48, 48, null);
			} else {
				g2.drawImage(battleHealthEmpty, 720 + ((i - 1) * gp.TILESIZE), 528, 48, 48, null);
			}
			
			if(player.archerHP >= i) {
				g2.drawImage(battleHealthFull, 720 + ((i - 1) * gp.TILESIZE), 576, 48, 48, null);
			} else {
				g2.drawImage(battleHealthEmpty, 720 + ((i - 1) * gp.TILESIZE), 576, 48, 48, null);
			}
			
			if(player.wizardHP >= i) {
				g2.drawImage(battleHealthFull, 720 + ((i - 1) * gp.TILESIZE), 624, 48, 48, null);
			} else {
				g2.drawImage(battleHealthEmpty, 720 + ((i - 1) * gp.TILESIZE), 624, 48, 48, null);
			}
		}
		////////////////////////////////////////////////////////////
		//STATUS
		g2.drawImage(warriorStatusImg, 864, 528, 48, 48, null);
		g2.drawImage(archerStatusImg, 864, 576, 48, 48, null);
		g2.drawImage(wizardStatusImg, 864, 624, 48, 48, null);
		////////////////////////////////////////////////////////////
		//SKILL CHARGE
		switch(battle.skillChargeCount) {
		case 1:
			g2.drawImage(skillCharge1, 0, 0, 960, 720, null); break;
		case 2:
			g2.drawImage(skillCharge2, 0, 0, 960, 720, null); break;
		case 3:
			g2.drawImage(skillCharge3, 0, 0, 960, 720, null); break;
		}
		////////////////////////////////////////////////////////////
		//ITEMS
		itemFormat = "";
		itemLength = String.valueOf(player.potionAmount).length();

		for(int i = 0; i < 2 - itemLength; i++) {
			itemFormat += "0";
		}
		itemFormat += player.potionAmount;
		
		g2.drawString(itemFormat, 266, 654);
		////////////////////////////////////////////////////////////
		//ENEMY HEALTH
		g2.setColor(Color.black);
		g2.drawString(String.valueOf(battle.enemyHP1), 634, 336);
		g2.drawString(String.valueOf(battle.enemyHP2), 740, 336);
		g2.drawString(String.valueOf(battle.enemyHP3), 826, 336);


		////////////////////////////////////////////////////////////	
	}
	////////////////////////////////////////////////////////////
	//GAMEOVER STATE
	////////////////////////////////////////////////////////////
	public void getGameOverOptionImage() {
		try {
			switch(optionState) {
			//CONTINUE
			case 1: optionImg = ImageIO.read(getClass().getResourceAsStream("/gameover/gameOver_continueSelect.png")); break;
			//EXIT
			case 2: optionImg = ImageIO.read(getClass().getResourceAsStream("/gameover/gameOver_exitSelect.png")); break;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	//ENDING STATE
	////////////////////////////////////////////////////////////
	public void getEndingOptionImage() {
		try {
			//EXIT
			optionImg = ImageIO.read(getClass().getResourceAsStream("/ending/ending_exitSelect.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {	
		////////////////////////////////////////////////////////////
		//TUTORIAL STATE
		if(gp.gameState == gp.TUTORIAL_STATE) {
			getTutorialOptionImage();
			
			if(keyH.enterPressed == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState = 1;
				gp.gameState = 1;
			}
		}
		////////////////////////////////////////////////////////////
		//TITLE STATE
		if(gp.gameState == gp.TITLE_STATE) {
			getTitleOptionImage();
			
			if(keyH.upPressed == true && optionState > 1) {
				keyH.upPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState--;
			}
			
			if(keyH.downPressed == true && optionState < 3) {
				keyH.downPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState++;
			} 
			
			if(keyH.enterPressed == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				switch(optionState) {
				case 1: player.setDefaultValues(); map.setDefaultValues(); soundM.changeMusic("res/audio/overworld.wav"); gp.gameState = 2; break; //returns to play state
				case 2: gp.gameState = 0; break;
				case 3: System.exit(0); break; //exits program
				}
			}
			
			if(keyH.mPressed == true) {
				keyH.mPressed = false;
				meow = random.nextInt(1, 4);
				switch(meow) {
				case 1: soundM.playSoundEffect("res/easteregg/meow.wav"); break;
				case 2: soundM.playSoundEffect("res/easteregg/mew.wav"); break;
				case 3: soundM.playSoundEffect("res/easteregg/mewow.wav"); break;
				}
				meowCat = true;
			}
		}
		////////////////////////////////////////////////////////////
		//PLAY STATE
		if(gp.gameState == gp.PLAY_STATE) {
			getMapImage();
		}
		////////////////////////////////////////////////////////////
		//BATTLE STATE
		if(gp.gameState == gp.BATTLE_STATE) {
			getBattleImage();
			getBattlePlayerImage();
			getBattleOptionImage();
			getTurnOptionImage();
			getBattleStatusImage();
			
			battle.startBattle(player.battleState);
			optionState = battle.battleOptionState;
		}
		////////////////////////////////////////////////////////////
		//GAMEOVER STATE
		if(gp.gameState == gp.GAME_OVER_STATE) {
			getGameOverOptionImage();
			
			player.setDefaultValues();
			map.setDefaultValues();
			battle.setDefaultValues();
			
			if(keyH.upPressed == true && optionState > 1) {
				keyH.upPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState--;
			}
			
			if(keyH.downPressed == true && optionState < 2) {
				keyH.downPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState++;
			} 
						
			if(keyH.enterPressed == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				switch(optionState) {
				case 1: optionState = 1; soundM.changeMusic("res/audio/overworld.wav"); gp.gameState = 2; break;
				case 2: optionState = 1; soundM.changeMusic("res/audio/title.wav"); gp.gameState = 1; break;
				}
			}
		}
		////////////////////////////////////////////////////////////
		//ENDING STATE		
		if(gp.gameState == gp.ENDING_STATE) {
			getEndingOptionImage();

			if(keyH.enterPressed == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				optionState = 1;
				soundM.changeMusic("res/audio/title.wav");
				gp.gameState = 1;
			}
		}
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {
		////////////////////////////////////////////////////////////
		if(gp.gameState == gp.TUTORIAL_STATE) {
			g2.drawImage(tutorialImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		////////////////////////////////////////////////////////////
		//TITLE STATE
		if(gp.gameState == gp.TITLE_STATE) {
			g2.drawImage(titleImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
			
			if(meowCat == true) {
				g2.drawImage(cat, 0, 0, 960, 720, null);
			}
		}
		////////////////////////////////////////////////////////////
		//PLAY STATE
		if(gp.gameState == gp.PLAY_STATE) {
			g2.drawImage(hudImg, 0, 0, 960, 720, null);
			drawHudValues(g2);
		}
		////////////////////////////////////////////////////////////
		//BATTLE STATE
		if(gp.gameState == gp.BATTLE_STATE) {
			g2.drawImage(battleImg, 0, 0, 960, 720, null);
			g2.drawImage(turnImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
			
			drawBattleEntity(g2);
			drawBattleValues(g2);
		}
		////////////////////////////////////////////////////////////
		//GAMEOVER STATE
		if(gp.gameState == gp.GAME_OVER_STATE) {
			g2.drawImage(gameOverImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		////////////////////////////////////////////////////////////
		//ENDING STATE
		if(gp.gameState == gp.ENDING_STATE) {
			g2.drawImage(endingImg, 0, 0, 960, 720, null);
			g2.drawImage(optionImg, 0, 0, 960, 720, null);
		}
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
}
