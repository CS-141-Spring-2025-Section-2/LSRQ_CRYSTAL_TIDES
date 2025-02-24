package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import _main_.GamePanel;
import _main_.KeyHandler;

public class Player extends Entity {
	////////////////////////////////////////////////////////////	
	GamePanel gp;
	KeyHandler keyH;
	
	private BufferedImage playerImg;	
	
	public String Area;
	
	public int SubArea;
	public int potionAmount;
	public int luminiteAmount;
	public int warriorHP, archerHP, wizardHP;
	public int battleState;
	public int playerOriginX, playerOriginY;
	public int playerTop, playerBottom, playerLeft, playerRight;
	
	public boolean isWarriorCrystal, isArcherCrystal, isWizardCrystal;
	public boolean isChest1Open, isChest2Open, isChest3Open;
	////////////////////////////////////////////////////////////
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		//HEALTH
		hp = 3;
		warriorHP = 3;
		archerHP = 3;
		wizardHP = 3;
		
		//ITEM AMOUNT
		potionAmount = 0;
		luminiteAmount = 0;
		
		//SPAWN LOCATION
		x = 7 * gp.TILESIZE;
		y = 8 * gp.TILESIZE + (gp.TILESIZE / 2);
		speed = 4;
		direction = "down";
		
		//CRYSTALS
		isWarriorCrystal = false;
		isArcherCrystal = false;
		isWizardCrystal = false;
		
		//CHESTS
		isChest1Open = false;
		isChest2Open = false;
		isChest3Open = false;
		
		//BATTLES
		battleState = 1;
	}
	////////////////////////////////////////////////////////////
	public void getPlayerImage() {
		try {
			//IDLE ANIMATION
			idle_up1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-idle1.png"));
			idle_up2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-idle2.png"));
			idle_down1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-idle1.png"));
			idle_down2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-idle2.png"));
			idle_left1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-idle1.png"));
			idle_left2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-idle2.png"));
			idle_right1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-idle1.png"));
			idle_right2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-idle2.png"));
			
			//WALKING ANIMATION
			up1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-walk1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightBack-walk2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-walk1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightFront-walk2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-walk1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideL-walk2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-walk1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/knight/knightSideR-walk2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {
		////////////////////////////////////////////////////////////
		//MOVEMENT
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
		////////////////////////////////////////////////////////////
		//SPRITE COUNTER
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
		////////////////////////////////////////////////////////////
		//HITBOX
		playerOriginX = x + (gp.TILESIZE / 2);
		playerOriginY = y + ((gp.TILESIZE / 2) + gp.TILESIZE);
		
		playerTop = y + gp.TILESIZE;
		playerBottom = y + (2 * gp.TILESIZE);
		playerLeft = x;
		playerRight = x + gp.TILESIZE;
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {		
		////////////////////////////////////////////////////////////
		//IDLE ANIMATION
		if(keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false) {
			switch(direction) {
			//UP
			case "up":
				if(spriteNumber == 1) {
					playerImg = idle_up1;
				}
				
				if(spriteNumber == 2) {
					playerImg = idle_up2;
				}
				break;
			//DOWN
			case "down":
				if(spriteNumber == 1) {
					playerImg = idle_down1;
				}
				
				if(spriteNumber == 2) {
					playerImg = idle_down2;
				}
				break;
			//LEFT
			case "left":
				if(spriteNumber == 1) {
					playerImg = idle_left1;
				}
				
				if(spriteNumber == 2) {
					playerImg = idle_left2;
				}
				break;
			//RIGHT
			case "right":
				if(spriteNumber == 1) {
					playerImg = idle_right1;
				}
				
				if(spriteNumber == 2) {
					playerImg = idle_right2;
				}
				break;
			}
		}
		////////////////////////////////////////////////////////////
		//WALKING ANIMATION
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			switch(direction) {
			//UP
			case "up":
				if(spriteNumber == 1) {
					playerImg = up1;
				}
				
				if(spriteNumber == 2) {
					playerImg = up2;
				}
				break;
			//DOWN
			case "down":
				if(spriteNumber == 1) {
					playerImg = down1;
				}
				
				if(spriteNumber == 2) {
					playerImg = down2;
				}
				break;
			//LEFT
			case "left":
				if(spriteNumber == 1) {
					playerImg = left1;
				}
				
				if(spriteNumber == 2) {
					playerImg = left2;
				}
				break;
			//RIGHT
			case "right":
				if(spriteNumber == 1) {
					playerImg = right1;
				}
				
				if(spriteNumber == 2) {
					playerImg = right2;
				}
				break;
			}
		}
		////////////////////////////////////////////////////////////
		g2.drawImage(playerImg, x, y, 48, 96, null);
		////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////
}
