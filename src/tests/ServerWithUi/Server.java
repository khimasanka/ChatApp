package tests.ServerWithUi;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Kaveesha Himasanka
 * @package : PACKAGE_NAME
 * @project : demo2
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e) {
            System.out.println("Error creating server");
            e.printStackTrace();
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessageToClient(String messageToClient) {
        try {
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending Message To Client ");
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void receiveMessageFromClient(VBox vbox) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String messageFromClient = bufferedReader.readLine();
                        Controller.addLabel(messageFromClient, vbox);
                    }catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error receiving message from client");
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket,BufferedReader bufferedReader , BufferedWriter bufferedWriter ) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
