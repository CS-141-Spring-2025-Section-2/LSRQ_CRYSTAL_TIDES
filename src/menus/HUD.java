package menus;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

public class HUD extends Menu{
	////////////////////////////////////////////////////////////
	Player player = new Player(null, null);
	
	String area;
	int hp;
	int potionCounter;
	int luminiteCounter;
	////////////////////////////////////////////////////////////
	public HUD() {
		getHUDImage();
	}
	////////////////////////////////////////////////////////////
	public void getHUDImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/hud/game-hud.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {	
		area = player.currentArea;
		hp = player.hp;
		potionCounter = player.potionCounter;
		luminiteCounter = player.luminiteCounter;
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {
		g2.drawImage(image, 0, 0, 960, 720, null);
	}
	////////////////////////////////////////////////////////////
}
