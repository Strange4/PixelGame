package common.message.server;

import common.message.Message;

import java.io.Serializable;

/**
 * The messages that the server is able to send with some additional data that
 * all server messages should have to help the client interpret them.
 *
 * //TODO not all messages should contain a player id.
 * @param <T> The type of the message
 */
public abstract class ServerMessage<T> extends Message<T> implements Serializable {
    public final int playerID;
    protected ServerMessage(T content, int playerID) {
        super(content);
        this.playerID = playerID;
    }
}
