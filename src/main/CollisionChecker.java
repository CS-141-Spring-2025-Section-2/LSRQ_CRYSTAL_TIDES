package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	int[][] gameGrid;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
		setGameGrid();
	}
	
	public void setGameGrid() {
		for(int x = 0; x < 15; x++) {
			System.out.println(x*gp.tileSize); //top side of box
		}
		
		for(int x = 1; x < 16; x++) {
			System.out.println(x*gp.tileSize); //bottom side of box
		}
		
		for(int x = 1; x < 21; x++) {
			System.out.println(x*gp.tileSize); //right side of box
		}
		
		for(int x = 0; x < 20; x++) {
			System.out.println(x*gp.tileSize); //left side of box
		}
	}
	
	public void getCollisions(Entity entity) {
		
	}
	
	public void checkCollisions(Entity entity) {
		
		
	}
}
