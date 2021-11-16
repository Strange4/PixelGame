package common.message;

import java.util.HashMap;
import java.util.Map;

/**
 * The handler provider is a class that holds a map with all event listeners to a message type. The generics are a bit
 * messy but they allow the HandlerProvider to check the types of the event listeners and return the correct message
 * type at compile time.
 *
 * @param <HandlerType> The handler for the Messages of a certain type.
 * @param <MessageType> The type of message that this HandlerProvider will provide to.
 */
public class HandlerProvider<HandlerType extends IMessageHandler<? extends MessageType>, MessageType extends Message> {

    private Map<Class<? extends MessageType>, HandlerType> handlers = new HashMap<>();

    /**
     * Adds a new handler to a given message type.
     * @param messageType The class of the message type
     * @param handler The method/class that will be set as the new "callback" (not exactly a callback since it wont
     *                be automatically called)
     * @param <T> A class that extends the given MessageType given on the HandlerProvider constructor.
     */
    public <T extends MessageType> void addHandler(Class<T> messageType, HandlerType handler) {
        handlers.put(messageType, handler);
    }

    /**
     * Returns the handler that was added using addHandler.
     * @param messageType The class of the message that you want to retrieve the handler from.
     * @return The handler from the given class.
     */
    public HandlerType getHandler(Class<? extends Message> messageType) {
        return handlers.get(messageType);
    }
}
