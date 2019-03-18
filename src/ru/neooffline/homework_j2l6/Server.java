package ru.neooffline.homework_j2l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    Server() throws IOException {
        initServer();
        sendMessageToClient();
    }

    private void initServer() {
        try (ServerSocket ss = new ServerSocket(8080)) {
            System.out.println("Server started");
            Socket socket = ss.accept();
            System.out.println("Client connected: " + socket.getInetAddress());
            if (socket != null) {
                this.inputStream = new DataInputStream(socket.getInputStream());
                this.outputStream = new DataOutputStream(socket.getOutputStream());
            }
            while (true) {
                String message = inputStream.readUTF();
                System.out.println("reciecve " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToClient() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true)
            outputStream.writeUTF(scanner.next());
    }
}
