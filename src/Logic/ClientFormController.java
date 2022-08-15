package Logic;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kaveesha Himasanka
 * @package : Client
 * @project : chatApp
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/
public class ClientFormController {
    public static ArrayList<ClientFormController> ClientFormControllers = new ArrayList<>();
    public ScrollPane spContext;
    public VBox vboxContext;
    public TextField txtMessage;
    public Button btnSend;
    private Client client;
    private String username;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUserName;

    /*public ClientFormController(Socket socket) throws IOException {
        try{
            this.socket = socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName = bufferedReader.readLine();
            ClientFormControllers.add(this);
        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
*/

    public void initialize(URL location, ResourceBundle resources) {
        try {
            client = new Client(new Socket("localhost", 3001), "wasantha");
            System.out.println("Connected to server");
        } catch (IOException e) {
            e.printStackTrace();
        }

        vboxContext.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                spContext.setVvalue((Double) newValue);
            }
        });

       // client.listenerForMessage(vboxContext);

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageToSend = txtMessage.getText();
                if (!messageToSend.isEmpty()) {
                   /* HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);

                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-color: rgb(239,242,255); " +
                            "-fx-background-color: rgb(15,125,242); " +
                            "-fx-background-radius: 20px; ");

                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.934,0.945,0.994));

                    hBox.getChildren().add(textFlow);
                    vboxContext.getChildren().add(hBox);
*/
                    client.sendMessage(messageToSend);
                    txtMessage.clear();
                }
            }
        });
   /* }

    public static void addLabel(String msgFromServer, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,255); " +
                "-fx-background-radius: 20px; ");

        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }

    public void broadcastMessage(String messageToSend) throws IOException {
        for (ClientFormController clientHandler : ClientFormControllers) {
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
        ClientFormControllers.remove(this);
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
       }*/
    }
}