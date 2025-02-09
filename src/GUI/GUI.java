package GUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

public class GUI {
	////////////////////////////////////////////////////////////
	Player player;
	
	public BufferedImage image1, image2;
	public String guiPath;
	public String mapPath;
	
	public int gameState;
	public int titleState = 1;
	public int playState = 2;
	public int menuState = 3;
	public int pauseState = 4;
	////////////////////////////////////////////////////////////
	public GUI(Player player) {
		this.player = player;
		
		setDefaultValues();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		gameState = 2;
	}
	////////////////////////////////////////////////////////////
	public void getGUIImage(String imagePath) {
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream(imagePath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getMapImage(String imagePath) {
		try {
			image2 = ImageIO.read(getClass().getResourceAsStream(imagePath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getMapPath(Player player) {
		mapPath = "/map/map-" + player.currentArea + player.currentSubArea + ".png";
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {
		if(gameState == titleState) {
			guiPath = "/title/title.png";
			getGUIImage(guiPath);

			g2.drawImage(image1, 0, 0, 960, 720, null);
		}
		
		if(gameState == playState) {
			guiPath = "/hud/game-hud.png";
			getMapPath(player);
			
			getGUIImage(guiPath);
			getMapImage(mapPath);
			
			g2.drawImage(image1, 0, 0, 960, 720, null);
			g2.drawImage(image2, 0, 0, 960, 720, null);
		}
		
		if(gameState == menuState) {
			//menu logic goes here
			//basically your party, status, inventory, settings, and exit
		}
		if(gameState == pauseState) {
			//pause logic goes here
			//reserved for events that do not require player input
		}
	}
	////////////////////////////////////////////////////////////
}
