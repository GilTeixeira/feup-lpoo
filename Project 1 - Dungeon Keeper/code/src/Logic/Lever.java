package Logic;

import java.util.Vector;

/**
 * The Class Lever.
 */
public class Lever extends Object {

	/** Is Lever activated. */
	private boolean isActivated = false;
	
	/** The doors the Lever opens. */
	private Vector<Door> doors;

	/**
	 * Instantiates a new lever.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public Lever(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		doors = new Vector<Door>();
	}

	/**
	 * Activate lever.
	 */
	public void activateLever() {
		isActivated = true;
		for (Door i : doors)
			i.openDoor();
	}

	/**
	 * Gets the doors the lever opens.
	 *
	 * @return the doors
	 */
	public Vector<Door> getDoors() {
		return doors;
	}

	/**
	 * Checks if is activated.
	 *
	 * @return true, if is activated
	 */
	public boolean isActivated() {
		return isActivated;
	}

	/**
	 * Adds a door.
	 *
	 * @param door the door
	 */
	public void addDoor(Door door) {
		doors.add(door);
	}
	
	/**
	 * Draws Lever to string.
	 * 
	 * @return Lever to string
	 */
	@Override
	public String toString() {
		return "k";
	}

	/**
	 * Gets the type of Object
	 * 
	 * @return type of Object
	 */
	public String getType() {
		return "Lever";
	}

}
