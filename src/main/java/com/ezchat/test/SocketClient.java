package com.ezchat.test;

import java.io.PrintWriter;
import java.util.Scanner;
public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 5768);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            System.out.println("Please Input: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            printWriter.println(input);
            printWriter.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
