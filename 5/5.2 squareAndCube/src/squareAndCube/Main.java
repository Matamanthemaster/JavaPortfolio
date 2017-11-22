package squareAndCube;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stageMain) throws Exception {

        //get the contents of Main.fxml to append to the scene
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //set the scene with the contents of Main.fxml and default size.
        Scene sceneMain = new Scene(root, 600, 300);

        //get the css file and add it to the scene's stylesheets.
        sceneMain.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        //set title, scene and show the stage.
        stageMain.setTitle("5.2 Square and Cube");
        stageMain.setScene(sceneMain);
        stageMain.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
