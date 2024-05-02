package com.ezchat.test;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(5768);
            System.out.println("Server started");
            Socket socket = server.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println("Connected with " + ip + " at " + socket.getPort());

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader); //BufferReader
            String readData = bufferReader.readLine();
            System.out.println("Received: " + readData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}