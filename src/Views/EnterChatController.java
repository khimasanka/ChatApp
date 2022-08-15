package Views;

import Logic.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

/**
 * @author : Kaveesha Himasanka
 * @package : Views
 * @project : chatApp
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/
public class EnterChatController {
    public TextField loginTextField;
    public Button btnLogin;
    public AnchorPane loginContext;

    public void loginOnClick(ActionEvent actionEvent) throws IOException {
        try {
            if (!loginTextField.getText().isEmpty()) {
                createSocket();
                Stage window = (Stage) loginContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Logic/ClientForm.fxml")))));
            }else {
                new Alert(Alert.AlertType.ERROR, "please enter username").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void createSocket() {
        try {
            Socket socket = new Socket("localhost",3001);
            Client client = new Client(socket,loginTextField.getText());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating socket");
        }
    }
}
