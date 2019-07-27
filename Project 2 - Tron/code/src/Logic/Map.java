package Logic;

/**
 * The Class Map.
 */
public class Map {

	/** The map. */
	private byte[][] map;

	/**
	 * Instantiates a new map.
	 *
	 * @param ARENA_WIDTH
	 *            the arena width
	 * @param ARENA_HEIGHT
	 *            the arena height
	 */
	public Map(int ARENA_WIDTH, int ARENA_HEIGHT) {
		map = new byte[ARENA_HEIGHT][ARENA_WIDTH];

	}

	/**
	 * Gets the value in this position in the map.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return value of element
	 */
	public byte getPosMap(int x, int y) {
		return map[y][x];
	}

	/**
	 * Sets the value of element in this position in the map.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param value
	 *            the value
	 */
	public void setPosMap(int x, int y, byte value) {
		map[y][x] = value;
	}

	/**
	 * Checks if is empty.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is empty
	 */
	public boolean isEmpty(int x, int y) {
		if (map[y][x] != 0)
			return false;
		else
			return true;
	}

	/**
	 * Gets the max x.
	 *
	 * @return the max x
	 */
	public int getMaxXsize() {
		return map[0].length;

	}

	/**
	 * Gets the max Y.
	 *
	 * @return the max Y
	 */
	public int getMaxY() {
		return map.length;

	}

	/**
	 * Checks if is next to wall.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is next to wall
	 */
	public boolean isNextToWall(int x, int y) {

		if (y + 1 < getMaxY() && getPosMap(x, y + 1) != 0)
			return true;

		if (y - 1 >= 0 && getPosMap(x, y - 1) != 0)
			return true;

		if (x + 1 < getMaxXsize() && getPosMap(x + 1, y) != 0)
			return true;

		if (x - 1 >= 0 && getPosMap(x - 1, y) != 0)
			return true;

		return false;

	}

	/**
	 * Check if coordinate is Valid.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is valid
	 */
	public boolean validCoord(int x, int y) {

		if (y < 0 || y >= getMaxY())
			return false;

		if (x < 0 || x >= getMaxXsize())
			return false;

		return true;

	}

	/**
	 * Gets the value of adjacent positions.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return value of adjacent positions
	 */
	public int[] getAdj(int x, int y) {
		int up = -1;
		int right = -1;
		int down = -1;
		int left = -1;

		if (validCoord(x, y - 1))
			up = getPosMap(x, y - 1);

		if (validCoord(x + 1, y))
			right = getPosMap(x + 1, y);

		if (validCoord(x, y + 1))
			down = getPosMap(x, y + 1);

		if (validCoord(x - 1, y))
			left = getPosMap(x - 1, y);

		return new int[] { up, right, down, left };

	}

}
