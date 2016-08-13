package com.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by studentgoit on 13.08.16.
 */
public class Client {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws IOException {
        new Client().execution();
    }

    public void execution() throws IOException {
        boolean connection = true;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Connected to server. Enter your name : ");
        setName(new Scanner(System.in).nextLine());

        while (connection) {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 3001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Enter your message :");
            String message = new Scanner(System.in).nextLine();
            out.writeUTF(sdf.format(cal.getTime()) + "." + name + " : " + message);
            System.out.println("Message sent");
            out.flush();
            out.close();
            System.out.println();
        }
    }
}
