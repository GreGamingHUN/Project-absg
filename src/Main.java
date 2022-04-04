import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.shape.Rectangle;

public class Main extends Application {


    //Game Stages:
    //-1 = Intro
    //0 = Tactical Phase
    //1 = Fight Phase
    public int gameStage = -1;

    Stage window, difficultyWindow;
    Scene mainScene, difficultyScene;

    /*GridPane root = new GridPane();
    GridPane map = new GridPane();
    GridPane sideBarStart = new GridPane();
    GridPane items = new GridPane();
    GridPane sideBar = new GridPane();*/

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*window = primaryStage;
        window.setResizable(false);
        difficultyWindow = new Stage();
        difficultyWindow.initStyle(StageStyle.UTILITY);
        difficultyWindow.setResizable(false);*/

        /*        //region Grids


        GridPane difficulty = new GridPane();

        //endregion

        //region root Grid
        root.setMinSize(600,600);
        root.add(map, 0, 0);
        root.add(sideBarStart, 1, 0);
        root.add(items, 0, 1);

        //endregion

        //region map Grid
        map.setPadding(new Insets(10, 10, 10, 10));
        map.setVgap(1);
        map.setHgap(1);
        map.setAlignment(Pos.CENTER);

        //endregion

        //region sideBarStart Grid

            //region Grid Elements
        Label welcomeLabel = new Label();
        Button startButton = new Button("Start");
        startButton.setOnAction(e-> {
            System.out.println("fasz");
            difficultyWindow.show();
            //root.getChildren().remove(sideBarStart);
            //root.add(sideBar, 1, 0);
        });

        welcomeLabel.setText("Üdvözöllek a Project-ABSG-ben!");
            //endregion

            //region Grid sizes
        sideBarStart.setMinWidth(100);
        sideBarStart.setMinHeight(600);
            //endregion

            //region Element Alignments
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setValignment(startButton, VPos.TOP);

        GridPane.setHalignment(welcomeLabel, HPos.CENTER);
        GridPane.setValignment(welcomeLabel, VPos.BOTTOM);

        RowConstraints row1_rc = new RowConstraints();
        row1_rc.setPercentHeight(50);
        RowConstraints row2_rc = new RowConstraints();
        row2_rc.setPercentHeight(50);
        sideBarStart.getRowConstraints().addAll(row1_rc, row2_rc);
            //endregion

            //region add Elements
        sideBarStart.add(welcomeLabel, 0,0);
        sideBarStart.add(startButton, 0, 1);
            //endregion

        //endregion

        //region Difficulty Window/Grid

            //region grid elements
        difficulty.add(new Label("Válassz nehézségi szintet!"), 0, 0);
        Button easyButton = new Button("Könnyű");
        Button mediumButton = new Button("Közepes");
        Button hardButton = new Button("Nehéz");

        easyButton.setOnAction(e -> {
            startGame();
        });

        mediumButton.setOnAction(e -> {
            startGame();
        });

        hardButton.setOnAction(e -> {
            startGame();
        });

        difficulty.add(easyButton, 0, 1);
        difficulty.add(mediumButton, 0, 2);
        difficulty.add(hardButton, 0, 3);
        difficulty.setVgap(5);
        difficulty.setHgap(5);
            //endregion

            //region elements alignment
        difficulty.setPadding(new Insets(5));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100);
        GridPane.setValignment(easyButton, VPos.CENTER);
        GridPane.setValignment(mediumButton, VPos.CENTER);
        GridPane.setValignment(hardButton, VPos.CENTER);

        GridPane.setHalignment(easyButton, HPos.CENTER);
        GridPane.setHalignment(mediumButton, HPos.CENTER);
        GridPane.setHalignment(hardButton, HPos.CENTER);
            //endregion

            //region window setup

        difficultyScene = new Scene(difficulty);
        difficultyWindow.setScene(difficultyScene);
        difficultyWindow.setTitle("Nehézség");
            //endregion
        //endregion

        //region items Grid
        FileInputStream villagerIMGSource = new FileInputStream("img/villager.jpg");
        Image villagerIMG = new Image(villagerIMGSource);
        ImageView villagerView = new ImageView(villagerIMG);
        villagerView.setFitHeight(50);
        villagerView.setFitWidth(50);
        items.add(villagerView, 0, 0);

        items.setPadding(new Insets(10));

        items.setVgap(10);
        items.setHgap(10);

        //endregion

        //region Set Default Scene
        mainScene = new Scene(root);
        window.setScene(mainScene);
        window.setTitle("ABSG");
        window.show();
        //endregion*/
        VBox root = FXMLLoader.load(getClass().getResource("FX_Events.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fasz");
        primaryStage.show();

    }



}
