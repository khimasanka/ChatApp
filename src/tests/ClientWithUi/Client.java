package tests.ClientWithUi;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

/**
 * @author : Kaveesha Himasanka
 * @package : PACKAGE_NAME
 * @project : Client
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/
public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.out.println("Error creating client");
            e.printStackTrace();
            closeEverything(socket,bufferedReader,bufferedWriter);
        }

    }

    public void sendMessageToServer(String messageToServer) {
        try {
            bufferedWriter.write(messageToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending Message To Server ");
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void receiveMessageFromServer(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String messageFromServer = bufferedReader.readLine();
                        Controller.addLabel(messageFromServer, vBox);
                    }catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error receiving message from server ");
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
