package common.message;

import java.io.Serializable;

/**
 * Message is a wrapper to that wraps any type and makes it serializable
 * so they can be sent through the network.
 * @param <T> The type you want to wrap.
 */
public abstract class Message<T> implements Serializable {
    private T content;

    protected Message(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

}
