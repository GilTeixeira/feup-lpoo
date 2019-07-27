package Logic;

import java.util.Vector;

import Logic.BotBehaviours.BotBehaviour;

/**
 * Controls the physics aspect of the game.
 */

public class Game {
	/**
	 * The arena width in meters.
	 */
	public static final int ARENA_WIDTH = 100;

	/**
	 * The arena height in meters.
	 */
	public static final int ARENA_HEIGHT = 75;

	/**
	 * The arena.
	 */
	private Map map;

	/**
	 * The players.
	 */
	private Vector<Player> players;

	/**
	 * The Bots.
	 */
	private Vector<Bot> bots;

	/** Is the game over. */
	boolean gameOver = false;

	/** Has Player won the game. */
	boolean gameWon = false;

	/** Player who Won. */
	int playerwhoWon;

	/**
	 * Instantiates a new game.
	 *
	 * @param NumHuman
	 *            the number of human player
	 * @param NumBots
	 *            the number of bots
	 * @param behaviour
	 *            the Bot behaviour
	 */
	public Game(int NumHuman, int NumBots, BotBehaviour behaviour) {
		map = new Map(ARENA_WIDTH, ARENA_HEIGHT);
		initializeMap();
		players = new Vector<Player>();
		bots = new Vector<Bot>();

		for (int i = 0; i < NumHuman; i++)
			players.add(new Player());

		for (int i = 0; i < NumBots; i++) {
			bots.add(new Bot());
			bots.get(i).setBehaviour(behaviour);
		}

		initialize();

	}

	/**
	 * Initialize the Game.
	 */
	public void initialize() {
		int InitialX[] = { 1, ARENA_WIDTH - 2, 1, ARENA_WIDTH - 2 };
		int InitialY[] = { 1, ARENA_HEIGHT - 2, ARENA_HEIGHT - 2, 1 };
		int InitialDir[] = { Player.RIGHT, Player.LEFT, Player.UP, Player.DOWN };
		byte num = 0;

		for (int i = 0; i < players.size(); i++) {
			players.get(i).setDirection(InitialDir[num]);
			players.get(i).setX(InitialX[num]);
			players.get(i).setY(InitialY[num]);
			players.get(i).setId(num + 1);
			map.setPosMap(InitialX[num], InitialY[num], (byte) (num + 1));
			num++;

		}

		for (int i = 0; i < bots.size(); i++) {
			bots.get(i).setDirection(InitialDir[num]);
			bots.get(i).setX(InitialX[num]);
			bots.get(i).setY(InitialY[num]);
			bots.get(i).setId(num + 1);
			map.setPosMap(InitialX[num], InitialY[num], (byte) (num + 1));
			num++;

		}

	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public Vector<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the bots.
	 *
	 * @return the bots
	 */
	public Vector<Bot> getBots() {
		return bots;
	}

	/**
	 * Gets the player Who won.
	 *
	 * @return the player Who won
	 */
	public int getPlayerwhoWon() {
		return playerwhoWon;
	}

	/**
	 * Calculates the next step.
	 *
	 * @return true, if game Over
	 */
	public boolean update() {

		updateDirBots();

		movePlayerAndBots();

		checkIfPassLimitsMap();

		checkColisons();

		updateMap();

		if (checkGameOver())
			return true;

		return false;
	}

	/**
	 * Update Direction of bots.
	 */
	public void updateDirBots() {
		for (int i = 0; i < bots.size(); i++)
			bots.get(i).moveBot(map, bots.get(i));
	}

	/**
	 * Move player and bots.
	 */
	public void movePlayerAndBots() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive())
				players.get(i).updatePos();

		}

		for (int i = 0; i < bots.size(); i++) {
			if (bots.get(i).isAlive())
				bots.get(i).updatePos();
		}
	}

	/**
	 * Check if players the limits of the map.
	 */
	public void checkIfPassLimitsMap() {
		for (int i = 0; i < bots.size(); i++) {
			if (bots.get(i).getX() < 0 || bots.get(i).getX() >= ARENA_WIDTH)
				bots.get(i).setAlive(false);

			if (bots.get(i).getY() < 0 || bots.get(i).getY() >= ARENA_HEIGHT)
				bots.get(i).setAlive(false);

		}

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getX() < 0 || players.get(i).getX() >= ARENA_WIDTH)
				players.get(i).setAlive(false);

			if (players.get(i).getY() < 0 || players.get(i).getY() >= ARENA_HEIGHT)
				players.get(i).setAlive(false);

		}
	}

	/**
	 * Check colisons between players.
	 */
	public void checkColisons() {
		for (int i = 0; i < bots.size(); i++) {
			int pos_x = bots.get(i).getX();
			int pos_y = bots.get(i).getY();
			if (bots.get(i).isAlive())
				if (map.getPosMap(pos_x, pos_y) != 0)
					bots.get(i).setAlive(false);
		}

		for (int i = 0; i < players.size(); i++) {
			int pos_x = players.get(i).getX();
			int pos_y = players.get(i).getY();

			if (players.get(i).isAlive())
				if (map.getPosMap(pos_x, pos_y) != 0)
					players.get(i).setAlive(false);
		}

	}

	/**
	 * Update map.
	 */
	public void updateMap() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive())
				map.setPosMap(players.get(i).getX(), players.get(i).getY(), (byte) players.get(i).getId());

		}

		for (int i = 0; i < bots.size(); i++) {
			if (bots.get(i).isAlive())
				map.setPosMap(bots.get(i).getX(), bots.get(i).getY(), (byte) bots.get(i).getId());
		}

	}

	/**
	 * Check if game over.
	 *
	 * @return true, if gameOver
	 */
	public boolean checkGameOver() {
		int numAlivePlayers = 0;
		int numAliveBots = 0;

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isAlive())
				numAlivePlayers++;

		}

		for (int i = 0; i < bots.size(); i++) {
			if (bots.get(i).isAlive())
				numAliveBots++;

		}

		if (numAlivePlayers == 0) {
			gameOver = true;
			gameWon = false;
			playerwhoWon = -1;
			return true;
		}

		if (numAlivePlayers == 1 && numAliveBots == 0) {
			gameOver = true;
			gameWon = true;
			for (int i = 0; i < players.size(); i++)
				if (players.get(i).isAlive()) {
					playerwhoWon = players.get(i).getId();
					break;
				}
			return true;
		}
		return false;

	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Initialize map.
	 */
	public void initializeMap() {

		for (int x = 0; x < map.getMaxXsize(); x++) {
			map.setPosMap(x, 0, (byte) -1);
			map.setPosMap(x, ARENA_HEIGHT - 1, (byte) -1);
		}

		for (int y = 0; y < map.getMaxY(); y++) {
			map.setPosMap(0, y, (byte) -1);
			map.setPosMap(ARENA_WIDTH - 1, y, (byte) -1);
		}

	}

	/**
	 * Sets the direction of player.
	 *
	 * @param index
	 *            the index
	 * @param dir
	 *            the direction
	 */
	public void setDirPlayer(int index, int dir) {
		players.get(index).setDirection(dir);

	}

	/**
	 * Checks if is player direction is this.
	 *
	 * @param index
	 *            the index
	 * @param dir
	 *            the direction
	 * @return true, if this is the player direction
	 */
	public boolean isDirPlayer(int index, int dir) {
		if (players.get(index).getDirection() == dir)
			return true;
		else
			return false;

	}

}
