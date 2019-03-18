package ru.neooffline.homework_j2l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException {
        initConnection();
        initReceiver();
        sendMessage();
    }

    private void initConnection() {
        try {
            socket = new Socket("localhost", 8080);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connection established " + socket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initReceiver() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String echoMessage = in.readUTF();
                    System.out.println(echoMessage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("receiver started");
    }

    private void sendMessage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true)
            out.writeUTF(scanner.next());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
//        client.sendMessage();
    }
}
