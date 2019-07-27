package Logic;

/**
 * The Class Club.
 */
public class Club extends Object {

	/**
	 * Instantiates a new Club.
	 *
	 * @param pos_x Coordinate x
	 * @param pos_y Coordinate y
	 */
	public Club(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}

	/**
	 * Draws Club to string.
	 * 
	 * @return Club to string
	 */
	public String toString() {
		return "*";

	}

	/** 
	 * Sets the Club coordinates.
	 * 
	 * @param pos_x Coordinate x
	 * @param pos_y Coordinate y
	 */
	public void setPosXY(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

}
