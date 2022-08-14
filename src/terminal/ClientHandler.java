package terminal;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/
public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUserName;

    public ClientHandler(Socket socket) throws IOException {
        try{
            this.socket = socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER : " + clientUserName + " has entered the chat");
        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageForClient;

        while(socket.isConnected()) {
            try {
                messageForClient = bufferedReader.readLine();
                broadcastMessage(messageForClient);
            }catch (IOException e){
                try {
                    closeEverything(socket,bufferedReader,bufferedWriter);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) throws IOException {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUserName.equals(clientUserName)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e) {
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClientHandler() throws IOException {
        clientHandlers.remove(this);
        broadcastMessage("SERVER : " + clientUserName + "has left the chat");

    }

    public void closeEverything(Socket socket,BufferedReader bufferedReader , BufferedWriter bufferedWriter) throws IOException {
        removeClientHandler();
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
