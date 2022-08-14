package Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
    String username;

    public void initialize() {

    }

    public void loginOnClick(ActionEvent actionEvent) throws IOException {
        /*Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../ClientWithUi/client.fxml"))));*/
        System.out.println(loginTextField.getText());
    }
}
