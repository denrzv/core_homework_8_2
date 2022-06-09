package io.github.denrzv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server(8080);
    }
    public Server(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            if (clientSocket.isConnected()) {
                System.out.println("New connection accepted");
                out.println("Write your name");
                String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                out.println("Are you child? (yes/no)");
                String answer = in.readLine();
                if (answer.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else {
                    out.println("Welcome to the adult zone, " + name +
                            "! Have a good rest, or a good working day!");
                }
                out.println("Where are you from?");
                answer = in.readLine();
                out.println("Cool! I'm from JVM");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
