package battle;

import java.util.Random;

import _main_.GamePanel;
import _main_.KeyHandler;
import _main_.SoundManager;
import entity.Enemy;
import entity.Player;
import map.Map;

public class Battle {
	////////////////////////////////////////////////////////////
	GamePanel gp;
	KeyHandler keyH;
	SoundManager soundM;
	Player player;
	Enemy enemy;
	Map map;
		
	private int enemyAmount;
	private int enemyTarget;
	public int skillChargeCount;
	public int enemyHP1, enemyHP2, enemyHP3;
	public int battleOptionState;
	public int turnState;
	public int battleState;
		
	private boolean isTauntActive = false;	
	private boolean playerTurn, enemyTurn;
	public boolean isWarriorDead, isArcherDead, isWizardDead;
	public boolean isEnemy1Dead, isEnemy2Dead, isEnemy3Dead;
	
	Random random = new Random();
	////////////////////////////////////////////////////////////
	public Battle(GamePanel gp, KeyHandler keyH, SoundManager soundM, Player player, Enemy enemy, Map map) {
		this.gp = gp;
		this.keyH = keyH;
		this.soundM = soundM;
		this.player = player;
		this.enemy = enemy;
		this.map = map;
		
		setDefaultValues();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		battleOptionState = 1;
		skillChargeCount = 0;
		
		isWarriorDead = false;
		isArcherDead = false;
		isWizardDead = false;
		
		player.warriorHP = 3;
		player.archerHP = 3;
		player.wizardHP = 3;
		
		isEnemy1Dead = false;
		isEnemy2Dead = false;
		isEnemy3Dead = false;
		
		enemyAmount = 3;
		
		enemyHP1 = 3;
		enemyHP2 = 3;
		enemyHP3 = 3;
		
		turnState = 1;
		playerTurn = true;
		enemyTurn = false;
	}
	////////////////////////////////////////////////////////////
	public void startBattle(int i) {
		battleState = i;
		
		if(playerTurn == true) {
			if(keyH.leftPressed == true && battleOptionState > 1) {
				keyH.leftPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				battleOptionState--;
			}
			
			if(keyH.rightPressed == true && battleOptionState < 4) {
				keyH.rightPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				battleOptionState++;
			}
			
			if(keyH.enterPressed == true) {
				keyH.enterPressed = false;
				soundM.playSoundEffect("res/audio/select.wav");
				switch(battleOptionState) {
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
		}
		
		System.out.println(turnState);
		
		if(turnState >= 4) {
			turnState = 1;
			playerTurn = false;
			enemyTurn = true;
		}
		
		if(enemyTurn == true) {
			if(skillChargeCount < 3) {
				skillChargeCount += 1;
			}
			
			enemyTurn();
		}
		
		checkPlayerHP();
		checkEnemyHP();
		checkBattle();
	}
	////////////////////////////////////////////////////////////
	public void enemyTurn() {
		for(int i = 0; i < enemyAmount; i++) {
			if(isTauntActive == true) {
				player.warriorHP -= 1;
			} else {
				enemyTarget = random.nextInt(1, 4);
				switch(enemyTarget) {
				case 1: player.warriorHP -= 1; soundM.playSoundEffect("res/audio/attack.wav"); break;
				case 2: player.archerHP -= 1; soundM.playSoundEffect("res/audio/attack.wav"); break;
				case 3: player.wizardHP -= 1; soundM.playSoundEffect("res/audio/attack.wav"); break;
				}
			}
		}
		
		isTauntActive = false;
		enemyTurn = false;
		playerTurn = true;
	}
	////////////////////////////////////////////////////////////
	public void checkPlayerHP() {
		//WARRIOR
		if(player.warriorHP <= 0) {
			isWarriorDead = true;
		}
		//ARCHER
		if(player.archerHP <= 0) {
			isArcherDead = true;
		}
		//WIZARD
		if(player.wizardHP <= 0) {
			isWizardDead = true;
		}
	}
	////////////////////////////////////////////////////////////
	public void checkEnemyHP() {
		if(enemyHP1 <= 0) {
			isEnemy1Dead = true;
			enemyAmount = 2;
		}
		
		if(enemyHP2 <= 0) {
			isEnemy2Dead = true;
			enemyAmount = 1;
		}
		
		if(enemyHP3 <= 0) {
			isEnemy3Dead = true;
		}
	}
	////////////////////////////////////////////////////////////
	public void checkBattle() {
		if(isWarriorDead && isArcherDead && isWizardDead == true) {
			setDefaultValues();
			player.setDefaultValues();
			map.setDefaultValues();
			
			if(player.hp >= 1) {
				player.hp -= 1;
				soundM.changeMusic("res/audio/overworld.wav");
				gp.gameState = 2;
			} else {
				soundM.changeMusic("res/audio/gameover.wav");
				gp.gameState = 4;
			}
		}
		
		if(isEnemy1Dead && isEnemy2Dead && isEnemy3Dead == true) {
			player.potionAmount += 3;
			player.luminiteAmount += 150;
			setDefaultValues();
			completeBattle(player.battleState);
			soundM.changeMusic("res/audio/overworld.wav");
			gp.gameState = 2;
		}
	}
	////////////////////////////////////////////////////////////
	public void completeBattle(int i) {
		switch(i) {
		case 1: enemy.isOrc1Dead = true; break;
		case 2: enemy.isOrc2Dead = true; break;
		case 3: enemy.isOrc3Dead = true; break;
		case 4: enemy.isElf1Dead = true; break;
		case 5: enemy.isElf2Dead = true; break;
		}
	}
	////////////////////////////////////////////////////////////
	public void attack() {
		if(enemyHP1 != 0) {
			enemyHP1 -= 1;
		} else {
			if(enemyHP2 != 0) {
				enemyHP2 -= 1;
			} else {
				enemyHP3 -= 1;
			}
		}
		turnState++;
	}
	////////////////////////////////////////////////////////////
	public void skill() {
		if(skillChargeCount == 3) {
			skillChargeCount = 0;
			
			switch(turnState) {
			//WARRIOR
			case 1: warriorSkill(); break; 
			//ARCHER
			case 2: archerSkill(); break;
			//WIZARD
			case 3: wizardSkill(); break;
			}
		} 
		turnState++;
	}
	////////////////////////////////////////////////////////////
	public void warriorSkill() {
		System.out.println("warrior");
		soundM.playSoundEffect("res/audio/attack.wav");
		isTauntActive = true;
	}
	////////////////////////////////////////////////////////////
	public void archerSkill() {
		System.out.println("archer");
		soundM.playSoundEffect("res/audio/attack.wav");
		player.warriorHP += 1;
		player.archerHP += 1;
		player.wizardHP += 1;
	}
	////////////////////////////////////////////////////////////
	public void wizardSkill() {
		System.out.println("wizard");

		soundM.playSoundEffect("res/audio/attack.wav");
		enemyHP1 -= 1;
		enemyHP2 -= 1;
		enemyHP3 -= 1;
	}
	////////////////////////////////////////////////////////////
	public void potion() {
		if(player.potionAmount > 0) {
			if(player.warriorHP >= 1 && player.warriorHP <= 3) {
				player.warriorHP += 1;
			}
			
			if(player.archerHP >= 1 && player.archerHP <= 3) {
				player.archerHP += 1;
			}
			
			if(player.wizardHP >= 1 && player.wizardHP <= 3) {
				player.wizardHP += 1;
			}
			
			player.potionAmount -= 1;
		}
		turnState++;
	}
	////////////////////////////////////////////////////////////
	public void run() {
		soundM.changeMusic("res/audio/overworld.wav");
		gp.gameState = 2;
	}
	////////////////////////////////////////////////////////////
}
