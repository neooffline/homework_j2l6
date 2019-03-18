package ru.neooffline.homework_j2l6;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            try {
                new Server();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.isDaemon();
        serverThread.start();
    }
}
