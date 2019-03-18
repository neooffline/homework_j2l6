package ru.neooffline.homework_j2l6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        initConnection();
        initReceiver();
    }

    private void initConnection() {
        try {
            socket = new Socket("localhost", 8080);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Conection established " + socket.getInetAddress());
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

    public static void main(String[] args) {
        new Client();
    }
}
