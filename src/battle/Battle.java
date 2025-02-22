package battle;

import java.awt.Graphics2D;

import _main_.GamePanel;
import _main_.KeyHandler;
import entity.Enemy;
import entity.Player;
import gui.GUI;

public class Battle {

	GamePanel gp;
	KeyHandler keyH;
	GUI gui;
	Player player;
	Enemy enemy;
	
	private int enemyAmount;
	private int enemyHP1, enemyHP2, enemyHP3;
	private int skillChargeCount;
	private int attackOptionState;
		
	private boolean isTauntActive = false;	
	private boolean isBattleActive = false;
	private boolean playerTurn, enemyTurn;
	private boolean isWarriorDead, isArcherDead, isWizardDead;
	private boolean isEnemy1Dead, isEnemy2Dead, isEnemy3Dead;
	
	public Battle(GamePanel gp, KeyHandler keyH, GUI gui, Player player, Enemy enemy) {
		this.gp = gp;
		this.keyH = keyH;
		this.gui = gui;
		this.player = player;
		this.enemy = enemy;
	}
	
	public void setDefaultValues() {
		skillChargeCount = 0;
		
		isWarriorDead = false;
		isArcherDead = false;
		isWizardDead = false;
		
		isEnemy1Dead = false;
		isEnemy2Dead = false;
		isEnemy3Dead = false;
		
		playerTurn = true;
		enemyTurn = false;
	}
	
	public void startBattle() {
		setDefaultValues();
		checkPlayerHP();
		
		while(isBattleActive == true) {
			if(playerTurn == true) {
				if(keyH.leftPressed == true && gui.optionState > 1) {
					gui.optionState--;
				}
				
				if(keyH.rightPressed == true && gui.optionState < 4) {
					gui.optionState++;
				}
				
				if(keyH.enterPressed == true) {
					switch(gui.optionState) {
					//ATTACK
					case 1: attack(); break;
					//SKILL
					case 2: skill(); break; 
					//POTION
					case 3: potion(); break; 
					//RUN
					case 4: run(); break;
					}
				}
				
				playerTurn = false;
				enemyTurn = true;
			}
			
			if(enemyTurn == true) {
				for(int i = 0; i < enemyAmount; i++) {
					
				}
				
				enemyTurn = false;
				playerTurn = true;
			}
			
			checkPlayerHP();
			checkEnemyHP();
		}
	}
	
	public void checkPlayerHP() {
		if(player.warriorHP == 0) {
			isWarriorDead = true;
		}
		
		if(player.archerHP == 0) {
			isArcherDead = true;
		}
		
		if(player.wizardHP == 0) {
			isWizardDead = true;
		}
	}
	
	public void checkEnemyHP() {
		if(enemyHP1 == 0) {
			isEnemy1Dead = true;
		}
		
		if(enemyHP2 == 0) {
			isEnemy1Dead = true;
		}
		
		if(enemyHP3 == 0) {
			isEnemy1Dead = true;
		}
	}
	
	public void checkBattle() {
		if(isWarriorDead && isArcherDead && isWizardDead == true) {
			gp.gameState = 6;
		}
		
		if(isEnemy1Dead && isEnemy2Dead && isEnemy3Dead == true) {
			player.luminiteAmount = 
			gp.gameState = 2;
		}
	}
	
	public void attack() {
		if(keyH.leftPressed == true && attackOptionState > 1) {
			attackOptionState--;
		}
		
		if(keyH.rightPressed == true && attackOptionState < 3) {
			attackOptionState++;
		}
		
		if(keyH.enterPressed == true) {
			switch(attackOptionState) {
			case 1: enemyHP1 -= 1;
			case 2: enemyHP2 -= 1;
			case 3: enemyHP3 -= 1;
			}
		}
	}
	
	public void getAttackOptionImage() {
		try {
			
		}
	}
	
	public void skill() {
		if(skillChargeCount == 3) {
			skillChargeCount = 0;
			
			switch(gui.playerTurnState) {
			//WARRIOR
			case 1: warriorSkill(); break; 
			//ARCHER
			case 2: archerSkill(); break;
			//WIZARD
			case 3: wizardSkill(); break;
			}
		} else {
			//play error sound here
		}
	}
	
	public void warriorSkill() {
		isTauntActive = true;
	}
	
	public void archerSkill() {
		player.warriorHP += 1;
		player.archerHP += 1;
		player.wizardHP += 1;
	}
	
	public void wizardSkill() {
		if(enemyAmount == 1) {
			enemyHP1 -= 1;
		}
		
		if(enemyAmount == 2) {
			enemyHP1 -= 1;
			enemyHP2 -= 1;
		}
		
		if(enemyAmount == 3) {
			enemyHP1 -= 1;
			enemyHP2 -= 1;
			enemyHP3 -= 1;
		}
	}
	
	public void potion() {
		if(player.potionAmount > 0) {
			switch(gui.optionState) {
			case 1: player.warriorHP += 2; player.potionAmount -= 1; break;
			case 2: player.archerHP += 2; player.potionAmount -= 1; break;
			case 3: player.wizardHP += 2; player.potionAmount -= 1; break;
			}	
		} else {
			//play error sound here
		}
	}
	
	public void run() {
		gp.gameState = 1;
	}
	
	public void draw(Graphics2D g2) {
		
	}
}
