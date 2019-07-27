package Logic;

/**
 * The Class Player. Stores Information about a Player.
 */
public class Player {

	/** The Constant UP. */
	public static final int UP = 0;

	/** The Constant RIGHT. */
	public static final int RIGHT = 1;

	/** The Constant DOWN. */
	public static final int DOWN = 2;

	/** The Constant LEFT. */
	public static final int LEFT = 3;

	/** Is the Player alive. */
	boolean alive;

	/** The direction player is going. */
	int direction;

	/** The x coordinate. */
	int x;

	/** The y coordinate. */
	int y;

	/** The Player's id. */
	int id;

	/**
	 * Instantiates a new player.
	 */
	public Player() {
		this.alive = true;

	}

	/**
	 * Checks if it is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets the PLayer's life.
	 *
	 * @param alive
	 *            the life to be set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Returns the Player's Direction.
	 * 
	 * @return the player's Direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction
	 *            the new direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Returns the Player's x coordinate.
	 * 
	 * @return the player's x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate.
	 *
	 * @param x
	 *            the new x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the Player's Y coordinate.
	 * 
	 * @return the player's Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate.
	 *
	 * @param y
	 *            the new y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns the Player's ID.
	 * 
	 * @return the player's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Checks if is a bot.
	 *
	 * @return true, if is bot
	 */
	public boolean isBot() {
		return false;
	}

	/**
	 * Update pos. Move in the direction player is faced.
	 */
	public void updatePos() {
		if (this.direction == Player.UP)
			this.y--;

		if (this.direction == Player.RIGHT)
			this.x++;

		if (this.direction == Player.DOWN)
			this.y++;

		if (this.direction == Player.LEFT)
			this.x--;
	}

	/**
	 * Next coordinates if player moves in this direction.
	 *
	 * @param dir
	 *            the direction
	 * @return the coordinates
	 */
	public int[] nextPosInThisDir(int dir) {
		int newX = this.x, newY = this.y;

		if (dir == Player.UP)
			newY = this.y - 1;

		if (dir == Player.RIGHT)
			newX = this.x + 1;

		if (dir == Player.DOWN)
			newY = this.y + 1;

		if (dir == Player.LEFT)
			newX = this.x - 1;

		return new int[] { newX, newY };

	}

}
