package common.message.server;

/**
 * A message that is triggered when a new Player joins the game.
 * This message should be sent only to the players who are already connected.
 */
public class NewPlayerConnected extends ServerMessage<String> {
    /**
     * Creates the New Player message
     * @param content The player's name
     * @param playerID The ID associated with the player
     */
    protected NewPlayerConnected(String content, int playerID) {
        super(content, playerID);
    }
}
