package test.ClientWithUi.ServerWithUi; /**
 * @author : Kaveesha Himasanka
 * @since : 0.1.0
 * Kaveesha Himasanka
 * 2022
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root,478,396));
        primaryStage.show();
    }
}
