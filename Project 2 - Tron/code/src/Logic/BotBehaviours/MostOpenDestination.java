package Logic.BotBehaviours;

import Logic.Map;
import Logic.Player;

/**
 * The Class MostOpenDestination.
 */
public class MostOpenDestination implements BotBehaviour {

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
		int bestcount = -1;
		int bestmove = Player.UP;
		for (int dir = Player.UP; dir <= Player.LEFT; dir++) {
			int[] dest = player.nextPosInThisDir(dir);
			if (!map.validCoord(dest[0], dest[1]) || map.getPosMap(dest[0], dest[1]) != 0)
				continue;
			int count = 0;
			int[] adjacent = map.getAdj(dest[0], dest[1]);
			for (int pos = 0; pos < adjacent.length; pos++) {
				if (adjacent[pos] == 0)
					count++;
			}
			if (count > bestcount) {
				bestcount = count;
				bestmove = dir;
			}

		}
		player.setDirection(bestmove);

	}

}
