package commissions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stageMain) throws Exception
    {

        //get the contents of Main.fxml to append to the scene
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //set the scene with the contents of Main.fxml and default size.
        Scene sceneMain = new Scene(root, 600, 400);



        //get the css files and add them to the scene's stylesheets.
        sceneMain.getStylesheets().add(getClass().getResource("css/colour.css").toExternalForm());

        //set title, scene, min height and widths, and show the stage.
        stageMain.setTitle("5.4 Commissions");
        stageMain.setScene(sceneMain);
        stageMain.setMinHeight(200);
        stageMain.setMinWidth(500);
        stageMain.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
