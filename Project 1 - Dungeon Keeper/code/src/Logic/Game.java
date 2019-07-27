package Logic;

import java.util.Vector;

/**
 * The Class Game.
 */
public class Game {
	
	/** The level 1. */
	private DungeonLevel level1;
	
	/** The level 2. */
	private OgreLevel level2;
	
	/** Is game on. */
	private boolean gameOn = true;

	/**
	 * Instantiates a new game.
	 *
	 * @param guardPersona the guard personality
	 * @param numOgres the number of ogres
	 */
	public Game(int guardPersona, int numOgres) {
		level1 = new DungeonLevel(guardPersona);
		level2 = new OgreLevel(numOgres);

	}

	/**
	 * Instantiates a new game.
	 *
	 * @param guardPersonality the guard personality
	 * @param numOgres the number of ogres
	 */
	public Game(String guardPersonality, int numOgres) {
		if (guardPersonality == "Rookie")
			level1 = new DungeonLevel(1);
		else if (guardPersonality == "Druken")
			level1 = new DungeonLevel(2);
		else if (guardPersonality == "Suspicious")
			level1 = new DungeonLevel(3);

		level2 = new OgreLevel(numOgres);

	}

	/**
	 * Update game.
	 *
	 * @param direction the direction the hero moves
	 * @return true, if successful
	 */
	public boolean updateGame(char direction) {
		if (!level1.isBeaten()) {
			gameOn = level1.update(direction);
			return gameOn;
		}

		else {
			gameOn = level2.update(direction);
			return gameOn;
		}

	}

	/**
	 * Checks if is game on.
	 *
	 * @return true, if is game on
	 */
	public boolean isGameOn() {
		return gameOn;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public String getMap() {
		if (!level1.isBeaten())
			return level1.toString();
		else
			return level2.toString();

	}

	/**
	 * Gets the map array.
	 *
	 * @return the map array
	 */
	public char[][] getMapArray() {
		if (!level1.isBeaten())
			return level1.getMap();
		else
			return level2.getMap();

	}


	/**
	 * Results.
	 *
	 * @return the 0 if beaten, 1 if lost on level1, 2 if lost on level2
	 */
	public int results() {
		if (level2.isBeaten())
			return 0;

		if (level1.isBeaten())
			return 2;

		return 1;

	}

	/**
	 * Level in which the game is.
	 *
	 * @return the number of the level
	 */
	public int levelIsOn() {
		if (!level1.isBeaten())
			return 1;
		else
			return 2;

	}

	/**
	 * Gets the hero.
	 *
	 * @return the hero
	 */
	public Hero getHero() {
		if (!level1.isBeaten())
			return level1.getHero();
		else
			return level2.getHero();
	}

	/**
	 * Gets the guard.
	 *
	 * @return the guard
	 */
	public Guard getGuard() {

		return level1.getGuard();
	}

	/**
	 * Gets the lever.
	 *
	 * @return the lever
	 */
	public Lever getLever() {

		return level1.getLever();

	}

	/**
	 * Gets the doors.
	 *
	 * @return the doors
	 */
	public Vector<Door> getDoors() {
		if (!level1.isBeaten()) {
			return level1.getOtherDoors();
		} else
			return level2.getOtherDoors();
	}

	/**
	 * Gets the enemies.
	 *
	 * @return the enemies
	 */
	public Vector<Ogre> getEnemies() {
		return level2.getEnemies();
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public Key getKey() {
		return level2.getKey();
	}

}