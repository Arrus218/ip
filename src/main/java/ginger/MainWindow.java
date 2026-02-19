package ginger;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Ginger ginger;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image gingerImage = new Image(this.getClass().getResourceAsStream("/images/Ginger.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.setStyle("-fx-background-color: " + "#FFDAB9" + ";");
        scrollPane.setStyle("-fx-background: " + "#FFDAB9" + "; -fx-background-color: transparent;");
    }

    /** Injects the Duke instance */
    public void setGinger(Ginger g) {
        this.ginger = g;

        dialogContainer.getChildren().addAll(
                DialogBox.getGingerDialog(this.ginger.getUi().showWelcome(), gingerImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ginger.getResponse(input);
        if (ginger.isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGingerDialog(response, gingerImage)
        );
        userInput.clear();
    }
}