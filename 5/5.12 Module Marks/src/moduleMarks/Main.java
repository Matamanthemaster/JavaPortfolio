package moduleMarks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //variables for having a single point to change width and height.
        int width = 305;
        int height = 350;

        //set the icon for the stage. The icon is from http://www.famfamfam.com/lab/icons/silk/
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/user.png")));

        //Create an FXMLLoader to load Main.fxml, and load it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("5.12 Module Marks");
        Scene sceneMain = new Scene(root, width, height);

        //Add the scene to the stage, and show it. Sets min width and height to defaults.
        primaryStage.setScene(sceneMain);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.show();

        /*Store the reference of the FXMLLoader's controller (the controller which the FXMLLoader is using defined by
        the FXML) statically in the Controller. Only exists so Student class instances can call an instance of Controller
        to call the method removeStudentTab*/
        Controller.currentController = loader.getController();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
