package common.message.server;

/**
 * A message that represents a message sent in the game chat from the player.
 */
public class PlayerChat extends ServerMessage<String> {

    /**
     * @param content the message that the player wrote.
     * @param id the id of the player who sent the message.
     */
    public PlayerChat(String content, int id) {
        super(content, id);
    }
}
