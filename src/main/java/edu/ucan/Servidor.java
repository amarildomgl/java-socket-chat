package edu.ucan;

import edu.ucan.constantes.ConstantesSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(ConstantesSocket.PORT);
        System.out.println("Servidor iniciado");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        Thread sendMessage = new Thread(() -> {
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }
        });

        sendMessage.start();

        Scanner in = new Scanner(socket.getInputStream());
        while (true) {
            String message = in.nextLine();
            System.out.println("Cliente: " + message);
        }

    }
}
