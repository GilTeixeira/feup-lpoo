package GUI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Logic.Game;
import Logic.Hero;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private Image wall;
	//private Image floor;
	private Image stairs;
	private Image doorClosed;
	private Image keyHit;
	private Image keyNotHit;
	private Image leverActive;
	private Image leverInactive;
	private Image heroUnarmed;
	private Image heroArmed;
	private Image guardAsleep;
	private Image guardAwake;
	private Image ogre;
	private Image ogreCube;
	private Image ogreStunned;
	private Image backGround;

	private Game game;

	public Game getGame() {
		return game;
	}



	private boolean drawMap = false;
	
	public GamePanel() {
		backGround =  new ImageIcon("dkeep/Images/BackGround.png").getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (drawMap) {
			displayGame(g);
		}
		else g.drawImage(backGround, 0, 0, getWidth(), getHeight(), null);
		

	}
	
	public void displayGame(Graphics g) {
		displayMap(g);
				
		if (game.levelIsOn() == 1) {			
			displayGuard(g);			
			displayLever(g);			
		} else {		
			displayOgres(g);			
			displayKey(g);

		}
		
		displayDoors(g);
		displayHero(g);
	}

	void displayHero(Graphics g) {

		int deltax = getHeight() /  game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;
		
		if (game.levelIsOn() == 1) {			
			g.drawImage(heroUnarmed, deltax * game.getHero().getX(), deltay * game.getHero().getY(), deltax, deltay, null);			
		} else {		
			g.drawImage(heroArmed, deltax * game.getHero().getX(), deltay * game.getHero().getY(), deltax, deltay, null);

		}

	}

	void displayDoors(Graphics g) {
		int deltax = getHeight() / game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;

		for (int i = 0; i < game.getDoors().size(); i++)
			if (game.getDoors().get(i).isOpen())
				g.drawImage(stairs, deltax * game.getDoors().get(i).getX(), deltay * game.getDoors().get(i).getY(),
						deltax, deltay, null);
			else
				g.drawImage(doorClosed, deltax * game.getDoors().get(i).getX(), deltay * game.getDoors().get(i).getY(),
						deltax, deltay, null);
			
	}
	

	void displayGuard(Graphics g) {
		int deltax = getHeight() /  game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;
		
		if (game.getGuard().isAsleep()) {			
			g.drawImage(guardAsleep, deltax * game.getGuard().getX(), deltay * game.getGuard().getY(), deltax, deltay, null);			
		} else {		
			g.drawImage(guardAwake, deltax * game.getGuard().getX(), deltay * game.getGuard().getY(), deltax, deltay, null);

		}

	}

	void displayLever(Graphics g) {
		int deltax = getHeight() / game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;

		if (game.getLever().isActivated()) {
			g.drawImage(leverActive, deltax * game.getLever().getX(), deltay * game.getLever().getY(), deltax, deltay,
					null);
		} else {
			g.drawImage(leverInactive, deltax * game.getLever().getX(), deltay * game.getLever().getY(), deltax, deltay,
					null);

		}
		for (int i = 0; i < game.getLever().getDoors().size(); i++)
			if (game.getLever().getDoors().get(i).isOpen())
				g.drawImage(stairs, deltax * game.getLever().getDoors().get(i).getX(),
						deltay * game.getLever().getDoors().get(i).getY(), deltax, deltay, null);
			else
				g.drawImage(doorClosed, deltax * game.getLever().getDoors().get(i).getX(),
						deltay * game.getLever().getDoors().get(i).getY(), deltax, deltay, null);

	}

	void displayOgres(Graphics g) {
		int deltax = getHeight() / game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;

		for (int i = 0; i < game.getEnemies().size(); i++) {
			g.drawImage(ogreCube, deltax * game.getEnemies().get(i).getCube().getX(),
					deltay * game.getEnemies().get(i).getCube().getY(), deltax, deltay, null);
			
			if (game.getEnemies().get(i).isStunned())
				g.drawImage(ogreStunned, deltax * game.getEnemies().get(i).getX(),
						deltay * game.getEnemies().get(i).getY(), deltax, deltay, null);
			else
				g.drawImage(ogre, deltax * game.getEnemies().get(i).getX(), deltay * game.getEnemies().get(i).getY(),
						deltax, deltay, null);

			

		}

	}

	void displayKey(Graphics g) {
		int deltax = getHeight() / game.getMapArray().length;
		int deltay = getWidth() / game.getMapArray()[0].length;

		if (!game.getHero().hasKey())
			if (game.getKey().isHit()) {

				g.drawImage(keyHit, deltax * game.getKey().getX(), deltay * game.getKey().getY(), deltax, deltay, null);
			} else {
				g.drawImage(keyNotHit, deltax * game.getKey().getX(), deltay * game.getKey().getY(), deltax, deltay,
						null);

			}
		else
			return;
		
		for (int i = 0; i < game.getLever().getDoors().size(); i++)
			if (game.getLever().getDoors().get(i).isOpen())
				g.drawImage(stairs, deltax * game.getLever().getDoors().get(i).getX(),
						deltay * game.getLever().getDoors().get(i).getY(), deltax, deltay, null);
			else
				g.drawImage(doorClosed, deltax * game.getLever().getDoors().get(i).getX(),
						deltay * game.getLever().getDoors().get(i).getY(), deltax, deltay, null);

	}
	

	public void displayMap(Graphics g) {

		char map[][] = game.getMapArray();

		int deltax = getHeight() / map.length;
		int deltay = getWidth() / map[0].length;

		for (int y = 0; y < map.length; y++)
			for (int x = 0; x < map[y].length; x++) {
				if (map[y][x] == 'X')
					g.drawImage(wall, deltax * x, deltay * y, deltax, deltay, null);

			}

	}

	public boolean heroMove(char dir) {
		boolean continueGame = game.updateGame(dir);
		repaint();
		
		return continueGame;
	 
	}
	
	void startGame(String guardPersonality, int numOgres) {
		loadImages();
		

		game = new Game(guardPersonality, numOgres);
		drawMap=true;
		repaint();
	}
	
	

	private void loadImages() {
		ImageIcon ii;

		ii = new ImageIcon("dkeep/Images/Wall.png");
		wall = ii.getImage();

		ii = new ImageIcon("dkeep/Images/BackGround.png");
		backGround = ii.getImage();

		ii = new ImageIcon("dkeep/Images/Stairs.png");
		stairs = ii.getImage();

		ii = new ImageIcon("dkeep/Images/DoorClosed.png");
		doorClosed = ii.getImage();

		ii = new ImageIcon("dkeep/Images/KeyHit.png");
		keyHit = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/KeyNotHit.png");
		keyNotHit = ii.getImage();

		ii = new ImageIcon("dkeep/Images/LeverActive.png");
		leverActive = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/LeverInactive.png");
		leverInactive = ii.getImage();

		ii = new ImageIcon("dkeep/Images/HeroUnarmed.png");
		heroUnarmed = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/HeroArmed.png");
		heroArmed = ii.getImage();

		ii = new ImageIcon("dkeep/Images/GuardAsleep.png");
		guardAsleep = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/GuardAwake.png");
		guardAwake = ii.getImage();

		ii = new ImageIcon("dkeep/Images/Ogre.png");
		ogre = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/OgreCube.png");
		ogreCube = ii.getImage();
		
		ii = new ImageIcon("dkeep/Images/OgreStunned.png");
		ogreStunned = ii.getImage();
		
		

	}

	
}
