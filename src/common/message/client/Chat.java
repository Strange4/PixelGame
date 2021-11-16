package common.message.client;

/**
 * Represents a text message sent by an user. It just encapsulates a simple string
 * but it deserved it's own class since Chat messages can be potentially be handled
 * very differently from other strings.
 */
public class Chat extends ClientMessage<String> {
    /**
     * Creates a simple Chat Message
     * @param content The content of the message.
     */
    public Chat(String content) {
        super(content);
    }
}
