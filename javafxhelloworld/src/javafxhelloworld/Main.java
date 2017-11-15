package javafxhelloworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stagePrimary) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene sceneMain = new Scene(root, 300, 275);

        sceneMain.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        stagePrimary.setTitle("Hello World");
        stagePrimary.setScene(sceneMain);
        stagePrimary.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
