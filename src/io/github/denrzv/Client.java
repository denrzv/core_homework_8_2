package io.github.denrzv;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String HOST = "netology.homework";
        final int PORT = 8080;

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                getMessage(in);
                System.out.println("Enter message for server... 'end' to exit");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) break;
                getMessage(in);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void getMessage(BufferedReader in) throws IOException {
        String response = in.readLine();
        if (response != null) {
            System.out.println("SERVER: " + response);
        }
    }
}
