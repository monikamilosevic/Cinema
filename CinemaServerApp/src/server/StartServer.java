/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monika
 */
public class StartServer extends Thread {

    ServerSocket serverSocket;
    List<ClientThread> clients;

    public StartServer() {
        clients = new ArrayList<>();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server is running.");
            new StopServer(this).start();

            while (isInterrupted() == false) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");
                ClientThread ct = new ClientThread(socket);
                clients.add(ct);
                ct.start();
            }
        } catch (IOException ex) {
            System.out.println("Server is stopped.");
            stoppedServer();
        }

    }

    public void stoppedServer() {
        try {
            serverSocket.close();
            for (ClientThread ct : clients) {
                ct.closeConnection();
            }
        } catch (IOException ex) {
            System.out.println("Server is stopped.");
        }
    }
}
