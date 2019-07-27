package Logic;


/**
 * The Class Hero.
 */
public class Hero extends Character {

	/** Is alive. */
	private boolean isAlive = true;
	
	/** Has key. */
	private boolean hasKey = false;
	
	/** Is armed. */
	private boolean isArmed = false;

	/**
	 * Instantiates a new hero.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public Hero(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}

	/**
	 * Kills Hero.
	 */
	public void wasKilled() {
		this.isAlive = false;
	}

	/** 
	 * Draws Hero to string.
	 * 
	 * @return Hero to string
	 */
	@Override
	public String toString() {
		if (hasKey)
			return "K";
		else if (isArmed)
			return "A";
		return "H";
	}

	/**
	 * Checks if Hero is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Checks if Hero has key.
	 *
	 * @return true, if successful
	 */
	public boolean hasKey() {
		return hasKey;
	}

	/**
	 * Pick key.
	 */
	public void pickKey() {
		hasKey = true;

	}

	/**
	 * Pick club.
	 */
	public void pickClub() {
		isArmed = true;
	}

	/**
	 * Move.
	 *
	 * @param dir the direction
	 * @param level the level
	 * @return true, if successful
	 */
	public boolean move(char dir, IGameLogicLevel level) {
		boolean validMove = false;

		switch (dir) {
		case 'w':
			if (level.heroCanMoveTo(getX(), getY() - 1)) {
				moveUp();
				validMove = true;
			}
			break;

		case 's':
			if (level.heroCanMoveTo(getX(), getY() + 1)) {
				moveDown();
				validMove = true;
			}
			break;

		case 'a':
			if (level.heroCanMoveTo(getX() - 1, getY())) {
				moveLeft();
				validMove = true;
			}
			break;

		case 'd':
			if (level.heroCanMoveTo(getX() + 1, getY())) {
				moveRight();
				validMove = true;
			}
			break;

		}
		return validMove;

	}

	/**
	 * Checks if is next to Character.
	 *
	 * @param character the Character
	 * @return true, if is next to Character
	 */
	public boolean isNextTo(Character character) {
		if (((Math.abs(getX() - character.getX()) <= 1) && (Math.abs(getY() - character.getY()) <= 0))
				|| ((Math.abs(getX() - character.getX()) <= 0) && (Math.abs(getY() - character.getY()) <= 1)))
			return true;
		else
			return false;
	}
}
