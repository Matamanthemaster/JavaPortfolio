package volume;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //create FXMLLoader, loads Main.fxml and creates new instance of controllerMain.
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //Set title and icon image. Image from http://www.famfamfam.com/lab/icons/silk/.
        primaryStage.setTitle("6.3 Volume");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/information.png")));

        //setting scene and show stage
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
