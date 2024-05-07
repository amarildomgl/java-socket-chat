package edu.ucan;

import edu.ucan.constantes.ConstantesSocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ConstantesSocket.HOST, ConstantesSocket.PORT);
        System.out.println("Conectado ao servidor...");

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
            System.out.println("Servidor: " + message);
        }
    }
}
