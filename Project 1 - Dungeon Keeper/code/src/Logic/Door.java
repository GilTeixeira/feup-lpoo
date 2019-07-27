package Logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 */
public class Door extends Object {

	/** Is it open. */
	private boolean isOpen = false;

	/**
	 * Instantiates a new door.
	 *
	 * @param pos_x Coordinate x
	 * @param pos_y Coordinate y
	 */
	public Door(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}

	/**
	 * Open door.
	 */
	public void openDoor() {
		isOpen = true;
	}

	/**
	 * Checks if is open.
	 *
	 * @return true, if it is open
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Draws Door to string.
	 * 
	 * @return Door to string
	 */
	@Override
	public String toString() {
		if (isOpen)
			return "S";
		else
			return "I";
	}

	/**
	 * Gets the type of Object
	 * 
	 * @return type of Object
	 */
	public String getType() {
		return "Door";
	}

	/**
	 * Check if door can be open by Hero
	 *
	 * @param hero the hero
	 * @param direction the direction the hero moves
	 * @return true, if successful
	 */
	public boolean canBeOpenWith(Hero hero, char direction) {

		if (getX() == hero.getX() && getY() == hero.getY() - 1 && direction == 'w')
			return true;
		if (getX() == hero.getX() && getY() == hero.getY() + 1 && direction == 's')
			return true;

		if (getX() == hero.getX() - 1 && getY() == hero.getY() && direction == 'a')
			return true;
		if (getX() == hero.getX() + 1 && getY() == hero.getY() && direction == 'd')
			return true;

		return false;

	}
}