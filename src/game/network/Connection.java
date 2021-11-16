package game.network;

import common.Credentials;
import common.message.client.Auth;
import common.message.client.Chat;
import common.message.client.ClientMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

    private ServerHandler serverHandler ;

    public Connection(int port) {
        try {
            final Socket requestSocket = new Socket("127.0.0.1", port);
            this.serverHandler = new ServerHandler(requestSocket);

            // Start listening to messages
            serverHandler.start();

            System.out.println("Connected successfully");

            this.promptLogin();
            this.chatMessageLoop();

        } catch (IOException error) {
            System.out.println("Couldn't connect to localhost.");
            error.printStackTrace();
        }
    }

    /** DEBUG */
    private void promptLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please login\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            Auth cr = new Auth(new Credentials(username, password));
            this.serverHandler.sendMessage(cr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chatMessageLoop() {
        Scanner scanner = new Scanner(System.in);
        ClientMessage chat;
        String input = "";

        while (input != ":q") {
            System.out.print("> ");
            input = scanner.nextLine();
            chat = new Chat(input);
            try {
                this.serverHandler.sendMessage(chat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new Connection(1224);
    }
}
