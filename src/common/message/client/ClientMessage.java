package common.message.client;

import common.message.Message;
import java.io.Serializable;

/**
 * All messages that the client can SEND to the server ( c -> s) will inherit from this
 * class.
 *
 * No additional data is added to the ClientMessage so it's only a copy of the MessageType.
 * This class is justifiable to ensure typechecking, but in the future I (Gui) will probably
 * add more data that a ClientMessage should handle.
 * @param <T> The content type
 */
public abstract class ClientMessage<T> extends Message<T> implements Serializable {
    /**
     * Creates a new message that will encapsulate a given object.
     * @param content The object that the message will encapsulate.
     */
    public ClientMessage(T content) {
        super(content);
    }
}
