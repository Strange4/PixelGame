package common.network;

import java.io.*;
import java.net.Socket;

public abstract class Network<MessageReceiver, MessageSender> extends Thread {
    public final Socket socket;

    /**
     * An abstract class to provide typed socket data transmission.
     * @param socket the socket responsible for sending and receiving messages.
     */
    public Network(Socket socket) {
        this.socket = socket;
    }

    /**
     * Get the input stream of a socket and return the object with the proper type.
     * @return the object sent through the socket.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected MessageReceiver receiveMessage() throws IOException, ClassNotFoundException {
        // Byte array input.
        InputStream input = this.socket.getInputStream();

        // Prints the input
        return ((MessageReceiver) new ObjectInputStream(input).readObject());
    }

    /**
     * Sends an object through the socket output stream.
     * @param message
     * @throws IOException
     */
    public void sendMessage(MessageSender message) throws IOException {
        OutputStream output = this.socket.getOutputStream();
        new ObjectOutputStream(output).writeObject(message);
    }

}
