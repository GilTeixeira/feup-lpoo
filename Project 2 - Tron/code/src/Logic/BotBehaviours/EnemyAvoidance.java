package Logic.BotBehaviours;

import java.util.ArrayList;

import Logic.Map;
import Logic.Player;

/**
 * The Class EnemyAvoidance.
 */
public class EnemyAvoidance implements BotBehaviour {

	/** The directions. */
	int[] dirs = { Player.UP, Player.DOWN, Player.LEFT, Player.RIGHT };

	/**
	 * Move bot.
	 *
	 * @param map
	 *            the map
	 * @param player
	 *            the bot
	 */
	@Override
	public void moveBot(Map map, Player player) {
		ArrayList<Integer> candidates = new ArrayList<Integer>();

		for (int i = 0; i < dirs.length; i++) {
			int newDir = dirs[i];
			int[] newPos = player.nextPosInThisDir(newDir);

			if (!map.validCoord(newPos[0], newPos[1]) || map.getPosMap(newPos[0], newPos[1]) != 0)
				continue;

			candidates.add(newDir);

			int[] adjacent = map.getAdj(newPos[0], newPos[1]);
			boolean enemyAdjacent = false;
			for (int j = 0; j < adjacent.length; j++) {
				if (adjacent[j] != 0 && adjacent[j] != player.getId() && adjacent[j] != -1) {
					enemyAdjacent = true;
					break;

				}

			}
			if (!enemyAdjacent) {
				player.setDirection(newDir);
				return;
			}

		}
		if (!candidates.isEmpty())
			player.setDirection(candidates.get(0));

	}

}
