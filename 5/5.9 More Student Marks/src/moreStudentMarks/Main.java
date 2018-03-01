package moreStudentMarks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Create an FXMLLoader to load Main.fxml, and load it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("5.5 Student Marks");
        Scene sceneMain = new Scene(root, 300, 350);

        /*While I am not adding my own CSS, the option to modify the CSS exists for the end user, though is going to
        require a knowledge of javafx CSS.*/
        sceneMain.getStylesheets().add(getClass().getResource("css/Main.css").toExternalForm());

        //Add the scene to the stage, and show it.
        primaryStage.setScene(sceneMain);
        primaryStage.show();

        /*Store the reference of the FXMLLoader's controller (the controller which the FXMLLoader is using defined by
        the FXML) statically in the Controller. Only exists so Student class instances can call an instance of Controller
        to call the method removeStudentTab*/
        Controller.CurrentController = loader.getController();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
