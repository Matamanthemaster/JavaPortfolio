package helloAgain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("hello.fxml"));
        primaryStage.setTitle("6.1 Hello Again");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/comments.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
