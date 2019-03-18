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
    private Scanner scanner;

    Server() {
        initServer();
    }

    private void initServer() {
        try (ServerSocket ss = new ServerSocket(8080)) {
            System.out.println("Server started");
            Socket socket = ss.accept();
            System.out.println("Client connected: " + socket.getInetAddress());
            try {
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                scanner = new Scanner(System.in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                String inputMessage = inputStream.readUTF();
                System.out.println("Message received from client:: " + inputMessage);
                try {
                    String outputMessage = scanner.next();
                    outputStream.writeUTF(outputMessage);
                    System.out.println("Message sent to client::" + outputMessage);
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Server started on::" + Thread.currentThread());
        new Server();
    }
}
