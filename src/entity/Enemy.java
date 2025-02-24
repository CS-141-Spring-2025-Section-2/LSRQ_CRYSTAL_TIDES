package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Entity{
	////////////////////////////////////////////////////////////
	public BufferedImage orcFront, orcBack, orcLeft, orcRight;
	public BufferedImage elfFront, elfBack, elfLeft, elfRight;
	
	public boolean isOrcTribeDead, isElfCultDead;
	
	public boolean isOrc1Dead, isOrc2Dead, isOrc3Dead;
	public boolean isElf1Dead, isElf2Dead;
	////////////////////////////////////////////////////////////
	public Enemy() {
		setDefaultValues();
		getOrcImage();
		getElfImage();
	}
	////////////////////////////////////////////////////////////
	public void setDefaultValues() {
		isOrcTribeDead = false;
		isElfCultDead = false;
		
		isOrc1Dead = false;
		isOrc2Dead = false;
		isOrc3Dead = false;
		
		isElf1Dead = false;
		isElf2Dead = false;
	}
	////////////////////////////////////////////////////////////
	public void getOrcImage() {
		try {
			orcFront = ImageIO.read(getClass().getResourceAsStream("/orc/orc_front.png"));
			orcBack = ImageIO.read(getClass().getResourceAsStream("/orc/orc_back.png"));
			orcLeft = ImageIO.read(getClass().getResourceAsStream("/orc/orc_left.png"));
			orcRight = ImageIO.read(getClass().getResourceAsStream("/orc/orc_right.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void getElfImage() {
		try {
			elfFront = ImageIO.read(getClass().getResourceAsStream("/elf/elf_front.png"));
			elfBack = ImageIO.read(getClass().getResourceAsStream("/elf/elf_back.png"));
			elfLeft = ImageIO.read(getClass().getResourceAsStream("/elf/elf_left.png"));
			elfRight = ImageIO.read(getClass().getResourceAsStream("/elf/elf_right.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////
	public void update() {
		if(isOrc1Dead && isOrc2Dead && isOrc3Dead == true) {
			isOrcTribeDead = true;
		}
		
		if(isElf1Dead && isElf2Dead == true) {
			isElfCultDead = true;
		}
	}
	////////////////////////////////////////////////////////////
}
