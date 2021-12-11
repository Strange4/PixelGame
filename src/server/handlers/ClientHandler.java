package server.handlers;

import common.Credentials;
import common.message.HandlerProvider;
import common.message.client.Auth;
import common.message.client.Chat;
import common.message.client.ClientMessage;
import common.message.server.PlayerChat;
import common.message.server.ServerMessage;
import common.network.Network;
import game.entity.types.Player;
import server.Server;
import server.message.IServerMessageHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Handles the all the messages coming from a socket.
 */
public class ClientHandler extends Network<ClientMessage, ServerMessage> {

    private HandlerProvider<IServerMessageHandler<? extends ClientMessage>, ClientMessage> handlerProvider = new HandlerProvider();
    private Player player;
    private Server server;

    public ClientHandler(Socket socket, Server server) {
        super(socket);
        this.server = server;

        handlerProvider.addHandler(Auth.class, onAuth);
        handlerProvider.addHandler(Chat.class, onChat);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private IServerMessageHandler<Auth> onAuth = message -> {
        Credentials credentials = message.getContent();
        System.out.println("New authentication request received.");
    };

    private IServerMessageHandler<Chat> onChat = message -> {
        //server.sendToAllExceptCaller(this, new Chat(message.getContent(), this.player.id));
        //System.out.println("[" + this.player.name + "]: " + message.getContent());
        System.out.println(message.getContent());
        server.sendToAllExceptCaller(this, new PlayerChat(message.getContent(), 1));
        //System.out.println("New chat message received.");
    };

    @Override
    public void run() {
        while (true) {
            try {
                ClientMessage message = receiveMessage();
                IServerMessageHandler handler = this.handlerProvider.getHandler(message.getClass());
                handler.handle(message);
            } catch (IOException | ClassNotFoundException e) {
                //serverBus.addMessage();
                System.out.println("Connection lost.");
                break;
            }
        }
    }
}
