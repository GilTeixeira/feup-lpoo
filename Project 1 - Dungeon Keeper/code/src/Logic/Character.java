package Logic;

/**
 * The Class Character.
 */

public abstract class Character {

	/** The Coordinate x. */
	protected int pos_x;
	
	/** The Coordinate y. */
	protected int pos_y;
	
	
	/**
	 * Constructor that sets its initial position.
	 *
	 * @param x the x
	 * @param y the y
	 */

	public Character(int x, int y) {
		this.pos_x = x;
		this.pos_y = y;
	}

	/**
	 * Sets Coordinate x.
	 *
	 * @param pos_x the new Coordinate x
	 */
	public void setPosX(int pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * Sets Coordinate y.
	 *
	 * @param pos_y the new Coordinate y
	 */
	public void setPosY(int pos_y) {
		this.pos_y = pos_y;
	}

	/**
	 * Move up.
	 */
	public void moveUp() {
		pos_y--;
	}

	/**
	 * Move down.
	 */
	public void moveDown() {
		pos_y++;
	}

	/**
	 * Move left.
	 */
	public void moveLeft() {
		pos_x--;
	}

	/**
	 * Move right.
	 */
	public void moveRight() {
		pos_x++;
	}

	/**
	 * Check if Position is this.
	 *
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return true, if successful
	 */
	public boolean positionIs(int x, int y) {
		if (pos_x == x && pos_y == y)
			return true;
		else
			return false;
	}

	/**
	 * Check if character is in the same position as object.
	 *
	 * @param obj the object
	 * @return true, if successful
	 */
	public boolean samePosition(Object obj) {
		if ((getX() == obj.getX()) && (getY() == obj.getY()))
			return true;
		else
			return false;
	}

	/**
	 * Gets the Coordinate x.
	 *
	 * @return the Coordinate x
	 */
	public int getX() {
		return pos_x;
	}

	/**
	 * Gets the Coordinate y.
	 *
	 * @return the Coordinate y
	 */
	public int getY() {
		return pos_y;
	}

}
