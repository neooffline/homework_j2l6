package ru.neooffline.homework_j2l6;

public class Main {

    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> new Server());
        serverThread.start();
    }
}
