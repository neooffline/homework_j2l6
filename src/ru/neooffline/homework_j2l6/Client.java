package ru.neooffline.homework_j2l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client() throws IOException {
        initConnection();
        initReceiver();
        sendMessage();
    }

    private void initConnection() {
        try {
            socket = new Socket("localhost", 8080);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connection established " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initReceiver() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String message = inputStream.readUTF();
                    System.out.println("Message received from server::" + message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("Receiver started on::" + Thread.currentThread());
    }

    private void sendMessage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String outMessage = scanner.next();
            outputStream.writeUTF(outMessage);
            System.out.println("Message sent to server::" + outMessage);
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
