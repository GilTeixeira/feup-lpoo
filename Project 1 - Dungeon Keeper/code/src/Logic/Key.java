package Logic;

import java.util.Vector;

/**
 * The Class Key.
 */
public class Key extends Object {

	/** Is hit. */
	private boolean isHit = false;
	
	/** The doors it opens. */
	private Vector<Door> doors;

	/**
	 * Key was hit.
	 */
	public void keyWasHit() {
		isHit = true;
	}

	/**
	 * Key was not hit.
	 */
	public void keyWasNotHit() {
		isHit = false;
	}

	/**
	 * Checks if is hit.
	 *
	 * @return true, if is hit
	 */
	public boolean isHit() {
		return isHit;
	}

	/**
	 * Instantiates a new key.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 */
	public Key(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		doors = new Vector<Door>();
	}

	/**
	 * Adds a door.
	 *
	 * @param door the door to be added
	 */
	public void addDoor(Door door) {
		doors.add(door);
	}

	/**
	 * Gets the doors it opens.
	 *
	 * @return the doors
	 */
	public Vector<Door> getDoors() {
		return doors;
	}

	/**
	 * Draws Key to string.
	 * 
	 * @return Key to string
	 */
	public String toString() {
		if (isHit)
			return "$";
		return "k";

	}

	/** 
	 * Gets the type of Object
	 * 
	 * @return type of Object
	 */
	public String getType() {
		return "Key";
	}

}
