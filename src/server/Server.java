package server;

import common.message.server.ServerMessage;
import server.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Holds the socket server and the game tick.
 */
public class Server extends Thread {
    private ArrayList<ClientHandler> activeClients = new ArrayList<>();
    private final int port;
    private int tickRate, playerLimit;

    public Server(int playerLimit, int tickRate, int port) {
        this.port = port;
        this.tickRate = tickRate;
        this.playerLimit = playerLimit;

        this.start();
        this.gameTick();
    }

    private void gameTick() {
        long initialTime = System.nanoTime();
        final double time = 1000000000 / this.tickRate;
        double delta = 0;
        int ticks = 0;
        long timer = System.currentTimeMillis();
        boolean running = true;

        while (running) {

            long currentTime = System.nanoTime();
            delta += (currentTime - initialTime) / time;
            initialTime = currentTime;

            if (delta >= 1) {
                ticks++;
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                ticks = 0;
                timer += 1000;
            }
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + port);

            while (true) {
                // Blocks the current thread until a new connection is made.
                Socket socket = serverSocket.accept();

                ClientHandler cl = new ClientHandler(socket,this);
                cl.start();
                activeClients.add(cl);
            }
        } catch (IOException error) {
            System.out.println("Couldn't start the server.");
            error.printStackTrace();
        }
    }

    public void sendToAll(ServerMessage message) {
        this.activeClients.forEach(connection -> {
            try {
                connection.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendToAllExceptCaller(ClientHandler caller, ServerMessage message) {
        this.activeClients.forEach(connection -> {
            if (connection != caller) {
                try {
                    connection.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new Server(3, 33, 1224);
    }
}
