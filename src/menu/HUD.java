package menu;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

public class HUD {
	
	BufferedImage hud, map;
	
	Player player = new Player(null, null);
	
	String area;
	int hp;
	int potionCounter;
	int luminiteCounter;

	public HUD() {
		
		getHUDImage();
		
	}
	
	public void getHUDImage() {
		
		try {
			
			hud = ImageIO.read(getClass().getResourceAsStream("/hud/game-hud.png"));
			map = ImageIO.read(getClass().getResourceAsStream("/town/map-town01.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
				
		area = player.currentArea;
		hp = player.hp;
		potionCounter = player.potionCounter;
		luminiteCounter = player.luminiteCounter;
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image1, image2 = null;
		
		image1 = hud;
		image2 = map;
		
		g2.drawImage(image1, 0, 0, 960, 720, null);
		g2.drawImage(image2, 0, 0, 960, 720, null);
		
		//g2.setFont(new Font("Alkhemikal", 48, 48));
		//g2.drawString(area, 200, 200);
		
	}
	
}
