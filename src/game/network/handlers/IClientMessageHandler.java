package game.network.handlers;

import common.message.IMessageHandler;
import common.message.Message;

@FunctionalInterface
public interface IClientMessageHandler <T extends Message> extends IMessageHandler<T> {
    // The game engine related stuff will be put here so we can communicate with the game through event queues.
    void handle(T message);
}
