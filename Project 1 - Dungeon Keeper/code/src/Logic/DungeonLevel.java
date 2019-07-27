package Logic;

import java.util.Vector;

/**
 * The Class DungeonLevel.
 */
public class DungeonLevel implements IGameLogicLevel {

	/** The wall char. */
	private char wallChar = 'X';

	/** The map. */
	private char map[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };


	/** The hero. */
	private Hero hero;
	
	/** The guard. */
	private Guard guard;
	
	/** The lever. */
	private Lever lever;
	
	/** The other doors. */
	private Vector<Door> otherDoors;

	/** The is beaten. */
	private boolean isBeaten;

	/**
	 * Instantiates a new dungeon level.
	 *
	 * @param guardPersona the guard personality
	 * @param map the map
	 */
	public DungeonLevel(int guardPersona, char map[][]) {
		this.map = map;

		lever = new Lever(-1, -1);
		otherDoors = new Vector<Door>();
		for (int y = 0; y < map.length; y++)
			for (int x = 0; x < map[y].length; x++) {
				if (map[y][x] == 'X' || map[y][x] == ' ')
					continue;
				else if (map[y][x] == 'k')
					lever.setPosXY(x, y);
				else if (map[y][x] == 'H')
					hero = new Hero(x, y);
				else if (map[y][x] == 'I')
					lever.addDoor(new Door(x, y));
				else if (map[y][x] == 'G')
					guard = new Guard(x, y, guardPersona);
			}

	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public char[][] getMap() {
		return map;
	}

	/**
	 * Gets the hero.
	 *
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Gets the other doors.
	 *
	 * @return the other doors
	 */
	public Vector<Door> getOtherDoors() {
		return otherDoors;
	}

	/**
	 * Gets the guard.
	 *
	 * @return the guard
	 */
	public Guard getGuard() {
		return guard;
	}

	/**
	 * Gets the lever.
	 *
	 * @return the lever
	 */
	public Lever getLever() {
		return lever;
	}

	/**
	 * Instantiates a new dungeon level.
	 *
	 * @param guardPersona the guard personality
	 */
	public DungeonLevel(int guardPersona) {
		hero = new Hero(1, 1);
		guard = new Guard(8, 1, guardPersona);
		lever = new Lever(7, 8);
		lever.addDoor(new Door(0, 5));
		lever.addDoor(new Door(0, 6));

		otherDoors = new Vector<Door>();
		otherDoors.add(new Door(4, 1));
		otherDoors.add(new Door(2, 3));
		otherDoors.add(new Door(4, 3));
		otherDoors.add(new Door(2, 8));
		otherDoors.add(new Door(4, 8));

	}

	/**
	 * Checks if is beaten.
	 *
	 * @return true, if is beaten
	 */
	public boolean isBeaten() {
		return isBeaten;
	}

	/**
	 * Beaten.
	 */
	public void beaten() {
		isBeaten = true;
	}

	/**
	 * Checks if lever is activated.
	 *
	 * @return true, if is lever activated
	 */
	public boolean isLeverActivated() {
		return lever.isActivated();
	}

	/**
	 * Activate lever.
	 */
	public void activateLever() {
		lever.activateLever();
	}

	/**
	 * Draws Map to string.
	 * 
	 * @return Map to string
	 */
	@Override
	public String toString() {
		String ret = "\n\n\n";

		for (int y_pos = 0; y_pos < map.length; y_pos++) {
			for (int x_pos = 0; x_pos < map[y_pos].length; x_pos++) {
				boolean wasAdded = false;
				if (hero.positionIs(x_pos, y_pos)) {
					ret += hero.toString();
					wasAdded = true;
				} else if (guard.positionIs(x_pos, y_pos)) {
					ret += guard.toString();
					wasAdded = true;
				} else if (lever.positionIs(x_pos, y_pos)) {
					ret += lever.toString();
					wasAdded = true;
				}
				for (Door i : lever.getDoors())
					if (i.positionIs(x_pos, y_pos)) {
						ret += i.toString();
						wasAdded = true;
					}

				for (Door i : otherDoors)
					if (i.positionIs(x_pos, y_pos)) {
						ret += i.toString();
						wasAdded = true;
					}
				if (!wasAdded)
					ret += map[y_pos][x_pos];
				ret += " ";
			}
			ret += " \n";

		}
		return ret;
	}

	/**
	 * Update Level.
	 *
	 * @param direction the direction the hero moves
	 * @return true, if successful
	 */
	public boolean update(char direction) {

		if (!hero.move(direction, this))
			return true;
		guard.move();

		// check if player has pushed the lever
		if (!isLeverActivated())
			if (hero.samePosition(lever)) {
				activateLever();
			}

		// check is player is next to Guard
		if (hero.isNextTo(guard) && !guard.isAsleep()) {
			hero.wasKilled();
			return false;
		}

		// check if player is on Exit Door
		if (isLeverActivated()) {
			for (Door i : lever.getDoors())
				if (hero.samePosition(i)) {
					beaten();

				}
		}
		return true;
	}

	/**
	 * Check if hero can move to Point
	 *
	 * @param  x_pos Coordinate x
	 * @param  y_pos Coordinate y
	 * @return true, if successful
	 */
	public boolean heroCanMoveTo(int x_pos, int y_pos) {
		if (map[y_pos][x_pos] == wallChar)
			return false;

		for (Door i : otherDoors)
			if (i.positionIs(x_pos, y_pos) && !i.isOpen())
				return false;

		for (Door i : lever.getDoors())
			if (i.positionIs(x_pos, y_pos) && !i.isOpen())
				return false;

		if (guard.positionIs(x_pos, y_pos))
			return false;

		return true;
	}
}
