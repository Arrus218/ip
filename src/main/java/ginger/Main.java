package ginger;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A GUI for Ginger using FXML.
 */
public class Main extends Application {

    private Ginger ginger = new Ginger();

    @Override
    public void start(Stage stage) {
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/comic.ttf"), 13);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image (this.getClass().getResourceAsStream("/images/Icon.png")));
            stage.setTitle("Ginger");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGinger(ginger);  // inject the Ginger instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
