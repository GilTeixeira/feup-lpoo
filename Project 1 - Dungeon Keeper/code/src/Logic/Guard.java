package Logic;

import java.util.Random;

/**
 * The Class Guard.
 */
public class Guard extends Character {

	/** The moves the guard makes. */
	private char moves[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd',
			'd', 'w', 'w', 'w', 'w', 'w' };

	/** The next move. */
	private int nextMove = 0;
	
	/** Is moving front. */
	private boolean isMovingFront = true;
	
	/** Is asleep. */
	private int isAsleep = 0;

	// 0-not moving
	// 1-rookie
	// 2-Druken
	// 3-Suspicious
	/** The guard personality. */
	private int guardPersona;

	/**
	 * Instantiates a new guard.
	 *
	 * @param pos_x the Coordinate x
	 * @param pos_y the Coordinate y
	 * @param guardPersona the guard personality
	 */
	public Guard(int pos_x, int pos_y, int guardPersona) {
		super(pos_x, pos_y);
		this.guardPersona = guardPersona;
	}

	/**
	 * Checks if is asleep.
	 *
	 * @return true, if is asleep
	 */
	public boolean isAsleep() {
		return (isAsleep != 0);

	}

	/** 
	 * Draws Guard to string.
	 * 
	 * @return Guard to string
	 */
	@Override
	public String toString() {
		if (isAsleep())
			return "g";
		return "G";
	}

	/**
	 * Move Guard.
	 */
	public void move() {

		switch (guardPersona) {
		case 0: // Not Moving-for tests
			return;

		case 1: // Rookie
			movefront();
			return;
		case 2:
			moveDruken();
			return;

		case 3:
			moveSuspicious();
			return;
		}

	}

	/**
	 * Move drunken.
	 */
	public void moveDruken() {
		if (isAsleep()) {
			isAsleep--;
			return;

		}
		Random ran = new Random();
		int num = ran.nextInt(8);
		if (num == 7) {
			isAsleep = 3;
			if (isMovingFront)
				if (nextMove == 0)
					nextMove = 23;
				else
					nextMove--;
			else if (nextMove == 23)
				nextMove = 0;
			else
				nextMove++;
			isMovingFront = !isMovingFront;

		}

		if (isMovingFront)
			movefront();
		else
			moveback();

	}

	/**
	 * Move suspicious.
	 */
	public void moveSuspicious() {
		Random rand = new Random();
		int nume;
		if (isMovingFront)
			nume = rand.nextInt(7);
		else
			nume = rand.nextInt(3);
		if (nume == 2) {
			if (isMovingFront)
				if (nextMove == 0)
					nextMove = 23;
				else
					nextMove--;
			else if (nextMove == 23)
				nextMove = 0;
			else
				nextMove++;

			isMovingFront = !isMovingFront;
		}
		if (!isMovingFront)
			moveback();
		else
			movefront();

	}

	/**
	 * Move in the original path.
	 */
	public void movefront() {

		char dir = moves[nextMove];
		if (nextMove == 23)
			nextMove = 0;
		else
			nextMove++;

		switch (dir) {
		case 'w':
			moveUp();
			break;

		case 's':
			moveDown();
			break;

		case 'a':
			moveLeft();
			break;

		case 'd':
			moveRight();
			break;
		}
	}

	/**
	 * Move Backwards.
	 */
	public void moveback() {

		char dir = moves[nextMove];
		if (nextMove == 0)
			nextMove = 23;
		else
			nextMove--;

		switch (dir) {
		case 'w':
			moveDown();
			break;

		case 's':
			moveUp();
			break;

		case 'a':
			moveRight();
			break;

		case 'd':
			moveLeft();
			break;
		}
	}

}
