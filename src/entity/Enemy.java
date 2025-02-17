package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Entity {

	public String name; //enemy name
	public int expWorth; //determines the amount of experience points given
	
	int enemyAmount;
	int enemyType;
	String enemyPosition;
	
	public Enemy() {
		getEnemyImage();
	}
	
	public void getEnemyImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream());
			up2 = ImageIO.read(getClass().getResourceAsStream());
			down1 = ImageIO.read(getClass().getResourceAsStream());
			down2 = ImageIO.read(getClass().getResourceAsStream());
			left1 = ImageIO.read(getClass().getResourceAsStream());
			left2 = ImageIO.read(getClass().getResourceAsStream());
			right1 = ImageIO.read(getClass().getResourceAsStream());
			right2 = ImageIO.read(getClass().getResourceAsStream());
			
			idle_up1 = ImageIO.read(getClass().getResourceAsStream());
			idle_up2 = ImageIO.read(getClass().getResourceAsStream());
			idle_down1 = ImageIO.read(getClass().getResourceAsStream());
			idle_down2 = ImageIO.read(getClass().getResourceAsStream());
			idle_left1 = ImageIO.read(getClass().getResourceAsStream());
			idle_left2 = ImageIO.read(getClass().getResourceAsStream());
			idle_right1 = ImageIO.read(getClass().getResourceAsStream());
			idle_right2 = ImageIO.read(getClass().getResourceAsStream());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2) {

	}
	
}
