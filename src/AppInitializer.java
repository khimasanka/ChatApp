/**
 * @author : Kaveesha Himasanka
 * @package : Views
 * @project : chatApp
 * Kaveesha Himasanka
 * 2022-Aug
 * @since : 0.1.0
 **/

import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/EnterChat.fxml"));
        primaryStage.setTitle("Tech Chat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
