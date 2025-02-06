package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	public String currentArea;
	public int potionCounter;
	public int luminiteCounter;
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		currentArea = "Town";
		potionCounter = 0;
		luminiteCounter = 0;
		
		hp = 100;
		mp = 100;
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcBack-walk01.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcBack-walk02.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcFront-walk01.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcFront-walk02.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideL-walk01.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideL-walk02.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideR-walk01.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideR-walk02.png"));
			
			idle_up1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcBack-idle01.png"));
			idle_up2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcBack-idle02.png"));
			idle_down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcFront-idle01.png"));
			idle_down2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcFront-idle02.png"));
			idle_left1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideL-idle01.png"));
			idle_left2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideL-idle02.png"));
			idle_right1 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideR-idle01.png"));
			idle_right2 = ImageIO.read(getClass().getResourceAsStream("/npc/npcSideR-idle02.png"));

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		
		else if(keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		
		else if(keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		
		else if(keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		
		if(spriteCounter > 12) {
			
			if(spriteNumber == 1) {
				spriteNumber = 2;
			}
			
			else if(spriteNumber == 2) {
				spriteNumber = 1;
			}
			
			spriteCounter = 0;
			
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		if(keyH.isKeyPressed == false) {
			
			switch(direction) {
			
			case "up":
				if(spriteNumber == 1) {
					image = idle_up1;
				}
				
				if(spriteNumber == 2) {
					image = idle_up2;
				}
				break;
				
			case "down":
				if(spriteNumber == 1) {
					image = idle_down1;
				}
				
				if(spriteNumber == 2) {
					image = idle_down2;
				}
				break;
				
			case "left":
				if(spriteNumber == 1) {
					image = idle_left1;
				}
				
				if(spriteNumber == 2) {
					image = idle_left2;
				}
				break;
				
			case "right":
				if(spriteNumber == 1) {
					image = idle_right1;
				}
				
				if(spriteNumber == 2) {
					image = idle_right2;
				}
				break;
				
			}
			
		}
		
		if(keyH.isKeyPressed == true) {
			
			switch(direction) {
			
			case "up":
				if(spriteNumber == 1) {
					image = up1;
				}
				
				if(spriteNumber == 2) {
					image = up2;
				}
				break;
				
			case "down":
				if(spriteNumber == 1) {
					image = down1;
				}
				
				if(spriteNumber == 2) {
					image = down2;
				}
				break;
				
			case "left":
				if(spriteNumber == 1) {
					image = left1;
				}
				
				if(spriteNumber == 2) {
					image = left2;
				}
				break;
				
			case "right":
				if(spriteNumber == 1) {
					image = right1;
				}
				
				if(spriteNumber == 2) {
					image = right2;
				}
				break;
				
			}
			
		}
		
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
	}
	
}
