package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	////////////////////////////////////////////////////////////	
	GamePanel gp;
	KeyHandler keyH;
	
	public String currentArea;
	public int currentSubArea;
	////////////////////////////////////////////////////////////
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		hp = 100;
		statPower = 5;
		
		currentArea = "town";
		currentSubArea = 1;
		
		x = 336;
		y = 384;
		speed = 4;
		direction = "down";
	}
	////////////////////////////////////////////////////////////
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-walk1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-walk2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-walk1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-walk2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-walk1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-walk2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-walk1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-walk2.png"));
			
			idle_up1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-idle1.png"));
			idle_up2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-idle2.png"));
			idle_down1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-idle1.png"));
			idle_down2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-idle2.png"));
			idle_left1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-idle1.png"));
			idle_left2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-idle2.png"));
			idle_right1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-idle1.png"));
			idle_right2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-idle2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
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
	////////////////////////////////////////////////////////////
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
		
		g2.drawImage(image, x, y, 48, 96, null);
	}
	////////////////////////////////////////////////////////////
}
