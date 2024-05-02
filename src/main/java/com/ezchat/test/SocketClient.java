package com.ezchat.test;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Connect to server
            socket = new Socket("localhost", 5768);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            System.out.println("Please Input: ");
            // New thread to send a message to server
            new Thread(() -> {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    try{
                        printWriter.println(input);
                        printWriter.flush();
                    }catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
            
            // Receive messages from server
            // Initialize InputStream to BufferedReader
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader = new BufferedReader(inputStreamReader); 
            // New thread to receive a message from server
            new Thread(() -> {
                while (true) {
                    try {
                        // Receive a message from server
                        String readData = bufferReader.readLine();
                        System.out.println("Received from Server: " + readData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
