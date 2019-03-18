package ru.neooffline.homework_j2l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    Server() {
        initServer();
    }

    private void initServer() {
        try (ServerSocket ss = new ServerSocket(8080)) {
            System.out.println("Server started");
            Socket socket = ss.accept();
            System.out.println("Client connected" + socket);
            try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                 DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());) {
                while (true) {
                    String message = inputStream.readUTF();
                    System.out.println("reciecve " + message);
                    outputStream.writeUTF(message);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
