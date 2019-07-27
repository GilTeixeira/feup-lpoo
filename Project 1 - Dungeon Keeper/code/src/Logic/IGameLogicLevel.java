package Logic;

/**
 * The Interface IGameLogicLevel.
 */
public interface IGameLogicLevel {

	/**
	 * Update Level.
	 *
	 * @param direction the direction the hero moves
	 * @return true, if successful
	 */
	public boolean update(char direction);

	/**
	 * Check if Hero can move to.
	 *
	 * @param x the Coordinate x
	 * @param y the Coordinate y
	 * @return true, if successful
	 */
	public boolean heroCanMoveTo(int x, int y);
}
