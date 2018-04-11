package iceSkaters;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //widths and heights to set scene and stage height;
        int width = 317;
        int height = 161;

        //Load the FXML file Main.fxml (and the controller class).
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //Images from http://www.famfamfam.com/lab/icons/silk/. Set image as icon.
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/medal_gold_1.png")));

        //set stage scene as scene we have loaded using an FXMLLoader.
        primaryStage.setScene(new Scene(root, width, height));

        //set a stage title.
        primaryStage.setTitle("7.1 Ice Skaters");

        //Make it so the user cannot resize the stage.
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(width);
        primaryStage.setMaxWidth(width);
        primaryStage.setWidth(width);
        primaryStage.setMinHeight(height + 40);
        primaryStage.setMaxHeight(height + 40);
        primaryStage.setHeight(height + 40);

        //show the stage.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
