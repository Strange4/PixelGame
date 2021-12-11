package game.network;


import common.message.HandlerProvider;
import common.message.client.ClientMessage;
import common.message.server.LoginAnswer;
import common.message.server.PlayerChat;
import common.message.server.ServerMessage;
import common.network.Network;
import game.entity.types.Player;
import game.network.handlers.IClientMessageHandler;

import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Network<ServerMessage, ClientMessage> {

    private HandlerProvider<IClientMessageHandler<? extends ServerMessage>, ServerMessage> handlerProvider = new HandlerProvider();

    /**
     * Handles all Engine.Client-Server networking.
     *
     * @param socket
     */
    public ServerHandler(Socket socket) {
        super(socket);

        // TODO allow multiple handlers per class.
        this.handlerProvider.addHandler(LoginAnswer.class, onLogin);
        this.handlerProvider.addHandler(PlayerChat.class, onPlayerChat);
    }

    private IClientMessageHandler<LoginAnswer> onLogin = message -> {
        Player[] player = message.getContent();
    };

    private IClientMessageHandler<PlayerChat> onPlayerChat = message -> {
        System.out.println(message.getContent());
    };

    @Override
    public void run() {
        while (true) {
            try {
                ServerMessage message = this.receiveMessage();
                IClientMessageHandler handler = this.handlerProvider.getHandler(message.getClass());
                handler.handle(message);

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
                System.out.println("Connection lost.");
                break;
            }
        }

    }
}
