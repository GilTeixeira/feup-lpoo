package Logic;

import Logic.BotBehaviours.BotBehaviour;

/**
 * The Class Bot.
 */
public class Bot extends Player {

	/** The Bot behaviour. */
	private BotBehaviour behaviour;

	/**
	 * Checks if is a bot.
	 *
	 * @return true, if is bot
	 */
	public boolean isBot() {
		return true;
	}

	/**
	 * Sets the behaviour.
	 *
	 * @param behaviour
	 *            the new behaviour
	 */
	public void setBehaviour(BotBehaviour behaviour) {
		this.behaviour = behaviour;

	}

	/**
	 * Move bot.
	 *
	 * @param map
	 *            the map
	 * @param player
	 *            the bot
	 */
	public void moveBot(Map map, Player player) {
		this.behaviour.moveBot(map, player);

	}

}
