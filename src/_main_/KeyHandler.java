package _main_;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	////////////////////////////////////////////////////////////
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	public boolean isKeyPressed;
	////////////////////////////////////////////////////////////
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	////////////////////////////////////////////////////////////
	public void keyTyped(KeyEvent e) {
		//NOTHING
	}
	////////////////////////////////////////////////////////////
	public void keyPressed(KeyEvent e) {
		isKeyPressed = true;
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.TITLE_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		
		//PLAY STATE
		if(gp.gameState == gp.PLAY_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
		}
		
		//MENU STATE
		if(gp.gameState == gp.MENU_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		
		//BATTLE STATE
		if(gp.gameState == gp.BATTLE_STATE) {
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		
		//ENDING STATE
		if(gp.gameState == gp.ENDING_STATE) {
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
	}
	////////////////////////////////////////////////////////////
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.TITLE_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = false;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = false;
			}
		}
		
		//PLAY STATE
		if(gp.gameState == gp.PLAY_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = false;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
		}
		
		//MENU STATE
		if(gp.gameState == gp.MENU_STATE) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				upPressed = false;
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = false;
			}
		}
		
		//BATTLE STATE
		if(gp.gameState == gp.BATTLE_STATE) {
			if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			
			if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = false;
			}
		}
		
		//ENDING STATE
		if(gp.gameState == gp.ENDING_STATE) {
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = false;
			}
		}
	}
	////////////////////////////////////////////////////////////
}
