package Logic;

/**
 * The Class Object.
 */
public abstract class Object {

	/** The Coordinate x. */
	protected int pos_x;
	
	/** The Coordinate y. */
	protected int pos_y;

	/**
	 * Instantiates a new object.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public Object(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
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

	/**
	 * Sets the pos Coordinate X and Coordinate Y.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public void setPosXY(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	/**
	 * Check if Position is.
	 *
	 * @param x the Coordinate x
	 * @param y the Coordinate y
	 * @return true, if successful
	 */
	public boolean positionIs(int x, int y) {
		if (pos_x == x && pos_y == y)
			return true;
		else
			return false;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return null;
	}

	/**
	 * Checks if object is next to character.
	 *
	 * @param character the character
	 * @return true, if is next to character
	 */
	public boolean isNextTo(Character character) {
		if (((Math.abs(character.getX() - getX()) <= 1) && (Math.abs(getY() - character.getY()) <= 0))
				|| ((Math.abs(getX() - character.getX()) <= 0) && (Math.abs(character.getY() - getY()) <= 1)))
			return true;
		else
			return false;
	}

	/**
	 * Checks if object is in same position as other object.
	 *
	 * @param obj the other object
	 * @return true, if successful
	 */
	public boolean samePosition(Object obj) {
		if ((getX() == obj.getX()) && (getY() == obj.getY()))
			return true;
		else
			return false;
	}

}
