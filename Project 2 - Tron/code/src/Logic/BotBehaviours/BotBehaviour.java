package Logic.BotBehaviours;

import Logic.Map;
import Logic.Player;

/**
 * The Interface BotBehaviour.
 */
public interface BotBehaviour {

	/**
	 * Move bot.
	 *
	 * @param map
	 *            the map
	 * @param player
	 *            the bot
	 */
	public void moveBot(Map map, Player player);

}
