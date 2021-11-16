package server.message;

import common.message.IMessageHandler;
import common.message.client.ClientMessage;

/**
 * A message wrapper that constrains the types only to ClientMessages.
 * @param <T>
 */
public interface IServerMessageHandler<T extends ClientMessage> extends IMessageHandler<T> {
    void handle(T message);

}
