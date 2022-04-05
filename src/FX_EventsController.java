import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class FX_EventsController {

    @FXML
    private MenuItem menuItemExit;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private RadioButton difficultyEasy;

    @FXML
    private RadioButton difficultyHard;

    @FXML
    private RadioButton difficultyMedium;

    @FXML
    private VBox rootScene;

    @FXML
    private Pane sideBarPreGame;

    @FXML
    private Button startButton;

    @FXML
    private GridPane tilesGrid;
    @FXML
    private ImageView villagerImage;
    @FXML
    private GridPane unitsGrid;

    @FXML
    void startGame(ActionEvent event) {
        System.out.println("fasz");
        unitsGrid.setOpacity(1);
        if (difficultyEasy.isSelected()) {
            Main.startGameMain(tilesGrid, 1);
        } else if (difficultyMedium.isSelected()) {
            Main.startGameMain(tilesGrid, 2);
        } else if (difficultyHard.isSelected()) {
            Main.startGameMain(tilesGrid, 3);
        }
    }


    @FXML
    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }
}
