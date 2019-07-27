package Logic.BotBehaviours;

import Logic.Map;
import Logic.Player;
import Logic.Util;

/**
 * The Class Random.
 */
public class Random implements BotBehaviour {

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

		Util.shuffleArray(dirs);

		for (int i = 0; i < dirs.length; i++) {
			int newDir = dirs[i];
			int[] newPos = player.nextPosInThisDir(newDir);
			if (!map.validCoord(newPos[0], newPos[1]) || map.getPosMap(newPos[0], newPos[1]) != 0)
				continue;
			else {
				player.setDirection(dirs[i]);
				return;
			}
		}
		player.setDirection(dirs[0]);

	}

}
