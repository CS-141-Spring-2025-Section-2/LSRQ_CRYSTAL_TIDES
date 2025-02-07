package menus;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;

public class Map extends Menu{
	////////////////////////////////////////////////////////////
	Player player = new Player(null, null);
	
	String mapArea;
	int mapNumber;
	
	String mapPath;
	////////////////////////////////////////////////////////////
	public Map() {
		setDefaultValues();
		getMapImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		update();
	}
	////////////////////////////////////////////////////////////
	public void getMapImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(mapPath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {
		mapArea = player.currentArea;
		mapNumber = player.areaNumber;
		
		mapPath = "/" + mapArea + "/map-" + mapArea + "0" + mapNumber + ".png";
	}
	////////////////////////////////////////////////////////////
	public void draw(Graphics2D g2) {
		g2.drawImage(image, 0, 0, 960, 720, null);
	}
	////////////////////////////////////////////////////////////
}
