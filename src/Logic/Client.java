package Logic;

import javafx.scene.layout.VBox;
import tests.ClientWithUi.Controller;

import java.io.*;
import java.net.Socket;

/**
 * @author : Kaveesha Himasanka
 * @package : Client
 * @project : chatApp
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/
public class Client {
    public Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String username;

    public Client(Socket socket, String username) {
        try {

            this.socket = socket;
            this.username = username;
            setReaders();
            System.out.println(this.username);
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    public void setReaders() throws IOException {
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("ok");
        }catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }


    public void sendMessage(String messageToSend) {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(username + " : " + messageToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
  /*  public void listenerForMessage(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String msgFromGroupChat = bufferedReader.readLine();
                        Controller.addLabel(msgFromGroupChat, vBox);
                    }catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error receiving message from server ");
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }*/


    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
