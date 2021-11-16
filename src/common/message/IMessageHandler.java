package common.message;

/**
 * The base structure to handle the Messages sent.
 * @param <T> Any class that extends message.
 */
public interface IMessageHandler<T extends Message> {
    void handle(T message);
}
