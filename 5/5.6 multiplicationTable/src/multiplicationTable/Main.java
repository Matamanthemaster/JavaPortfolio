package multiplicationTable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //ints store height and widths of application stage and scene, so they are all changed by changing 1 variable.
        int width = 215;
        int height = 300;

        //creates a new FXMLLoader, loads it in, stores the computed node as root.
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //Create a new scene with the root element gotten from the FXML loader, at specified dimensions.
        Scene sceneMain = new Scene(root, width, height);

        /*Get the Main.css file and add it to the list of stylesheets for this scene. I do not plan on writing any but
        the option is there for an advanced used to add their own styles.*/
        sceneMain.getStylesheets().add(getClass().getResource("css/Main.css").toExternalForm());

        //sets the stage title, adds the main scene to stage, show the stage.
        primaryStage.setTitle("5.6 Multiplication Tables");
        primaryStage.setScene(sceneMain);
        primaryStage.show();





        //Same code as in 5.2, but simplified. Should cover all bases in event both are needed by a specific OS.
        //Sets resizable to false, which works in specific OS' like windows.
        primaryStage.setResizable(false);

        //Sets widths and heignts, and max/min widths and heights. For operating systems that do not support setResizable property.
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
