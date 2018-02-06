package payslips;

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
        Parent root = FXMLLoader.load(getClass().getResource("payslips.fxml"));

        //set the scene with the contents of Main.fxml and default size.
        Scene sceneMain = new Scene(root, 500, 250);


        //get the css files and add it to the scene's stylesheets.
        sceneMain.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        sceneMain.getStylesheets().add(getClass().getResource("css/colour.css").toExternalForm());

        //set stage minimum sizes
        stageMain.setMinWidth(300);
        stageMain.setMinHeight(150);

        //set title, scene and show the stage.
        stageMain.setTitle("5.3 Payslips");
        stageMain.setScene(sceneMain);
        stageMain.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
