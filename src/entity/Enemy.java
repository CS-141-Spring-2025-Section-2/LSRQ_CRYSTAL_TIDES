package entity;

import java.util.Random;

import monsters.Wolf;

public class Enemy extends Entity{

	public String name; //enemy name
	public int expWorth; //determines the amount of experience points given
	
	int enemyAmount;
	int enemyType;
	String enemyPosition;
	
	Random random = new Random();
	
	public void createBattle() {
		enemyAmount = random.nextInt(4) + 1;
		
		for(int i = 0; i < enemyAmount; i++) {
			enemyType = random.nextInt(3) + 1;
			
			switch(enemyType) {
			case 1:
			
				Enemy enemyPosition = new Wolf();
			case 2:
				enemyPosition = "direWolf" + i;
				Enemy enemyPosition = new Wolf();
			case 3:
				enemyPosition = "fieldBoss" + i;
				Enemy enemyPosition = new Wolf();
			}
		}
		
	}
}
