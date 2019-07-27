package Logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Class Ogre.
 */
public class Ogre extends Character {
	
	/** Is on key. */
	private boolean isOnKey = false;
	
	/** Is stunned. */
	private int isStunned = 0;
	
	/** The club. */
	private Club cube;
	
	/** Can move. */
	private boolean canMove = true;

	/**
	 * Instantiates a new ogre.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public Ogre(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		cube = new Club(pos_x, pos_y);
	}

	/**
	 * Gets the club.
	 *
	 * @return the club
	 */
	public Club getCube() {
		return cube;
	}

	/**
	 * Got stunned.
	 */
	public void gotStunned() {
		isStunned = 2;
		cube.setPosXY(pos_x, pos_y);
	}

	/**
	 * check if Ogre just got stunned.
	 *
	 * @return true, if true
	 */
	public boolean justgotStunned() {
		if (isStunned == 2)
			return true;
		else
			return false;
	}

	/**
	 * Move Ogre.
	 *
	 * @param ogreLevel the ogre level
	 */
	public void move(OgreLevel ogreLevel) {
		if (!canMove)
			return;

		if (isStunned != 0) {
			isStunned--;
			atack(ogreLevel);
			return;
		}
		// gera num de 0 a 4
		int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);

		boolean canMove = false;

		while (!canMove) {
			
			if (ogreLevel.ogreCanMoveTo(getX(), getY() - 1) && randomNum == 0) {
				moveUp();
				canMove = true;
			} else if (ogreLevel.ogreCanMoveTo(getX(), getY() + 1) && randomNum == 1) {
				moveDown();
				canMove = true;
			} else if (ogreLevel.ogreCanMoveTo(getX() - 1, getY()) && randomNum == 2) {
				moveLeft();
				canMove = true;
			} else if (ogreLevel.ogreCanMoveTo(getX() + 1, getY()) && randomNum == 3) {
				moveRight();
				canMove = true;
			}

			if (!canMove) {
				randomNum++;
				if (randomNum > 3)
					randomNum = 0;

			}

		}
		atack(ogreLevel);
	}

	/**
	 * Sets if Ogre can move or not.
	 *
	 * @param canMove true if can move, false if not
	 */
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	/**
	 * Swing Club.
	 *
	 * @param ogreLevel the ogre level
	 */
	public void atack(OgreLevel ogreLevel) {
		// gera num de 0 a 4
		int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);

		boolean canAtack = false;

		while (!canAtack) {

			if (randomNum == 0 && ogreLevel.ogreCanAtack(getX(), getY() - 1)) {
				cube.setPosXY(getX(), getY() - 1);
				canAtack = true;
			} else if (randomNum == 1 && ogreLevel.ogreCanAtack(getX(), getY() + 1)) {
				cube.setPosXY(getX(), getY() + 1);
				canAtack = true;
			} else if (randomNum == 2 && ogreLevel.ogreCanAtack(getX() - 1, getY())) {
				cube.setPosXY(getX() - 1, getY());
				canAtack = true;
			} else if (randomNum == 3 && ogreLevel.ogreCanAtack(getX() + 1, getY())) {
				cube.setPosXY(getX() + 1, getY());
				canAtack = true;
			}
			if (!canAtack) {
				randomNum++;
				if (randomNum > 3)
					randomNum = 0;

			}

		}

	}

	/**
	 * Stepped on key.
	 */
	public void stepedOnKey() {
		isOnKey = true;

	}

	/**
	 * Checks if is stunned.
	 *
	 * @return true, if is stunned
	 */
	public boolean isStunned() {
		if (isStunned == 0)
			return false;
		else
			return true;

	}

	/**
	 * Checks if is on key.
	 *
	 * @return true, if is on key
	 */
	public boolean isOnKey() {
		return isOnKey;

	}

	/**
	 * Stop stepping on key.
	 */
	public void stopstepedOnKey() {
		isOnKey = false;

	}

	/**
	 * Draws Ogre to string.
	 * 
	 * @return Ogre to string
	 */
	@Override
	public String toString() {
		if (isOnKey)
			return "$";
		else if (isStunned())
			return "8";
		return "0";
	}

}
