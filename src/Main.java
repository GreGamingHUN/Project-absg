import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Stack;

public class Main extends Application {

    Stage window, window2;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window2 = new Stage();
        window2.setScene(scene2);
        //Button 1
        Label label1 = new Label("Szoszi devla");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> {
            System.out.println("toScene 2");
            window.setScene(scene2);
            window2.show();
        });

        //Layout 1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);


        //Button 2
        Button button2 = new Button("Go to scene 1");
        button1.setOnAction(e -> {
            window.setScene(scene1);
            System.out.println("toScene 1");
        });

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        //Set Default Scene
        window.setScene(scene1);
        window.setTitle("ABSG");
        window.show();
    }

}
