package com.ezchat.test;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Map<String, Socket> clients = new HashMap<>(); // <ip, socket>
        // to store all connected clients

        try {
            // Start server to receive messages
            server = new ServerSocket(5768);
            System.out.println("Server started, waiting for clients...");
            while (true) {
                // Connect with new client
                Socket socket = server.accept();
                String ip = socket.getInetAddress().getHostAddress();
                System.out.println("Connected with " + ip + " at " + socket.getPort());

                // manage all connected clients
                String clientKey = ip + socket.getPort();
                clients.put(clientKey, socket);

                // New thread to receive messages & send back
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        while (true) {
                            try{
                                // Receive a message from client
                                InputStream inputStream = socket.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                                BufferedReader bufferReader = new BufferedReader(inputStreamReader); //BufferReader
                                String readData = bufferReader.readLine();
                                System.out.println("Received: " + readData);

                                // Send the message back to clients
                                clients.forEach((key, value) -> {
                                    // System.out.println("Clients are: " + key + " " + value);
                                    // // Clients are: 127.0.0.120995 Socket[addr=/127.0.0.1,port=20995,localport=5768]
                                    // // Clients are: 127.0.0.121127 Socket[addr=/127.0.0.1,port=21127,localport=5768]
                                    try{
                                        OutputStream outputStream = value.getOutputStream();
                                        PrintWriter printWriter = new PrintWriter(
                                                new OutputStreamWriter(outputStream, "UTF-8")
                                        );
                                        printWriter.println(socket.getPort() + ": " + readData); // sender: message
                                        printWriter.flush();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}