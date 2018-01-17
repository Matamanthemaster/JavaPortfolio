package squareAndCube;

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
        Scene sceneMain = new Scene(root, 250, 100);


        //get the css file and add it to the scene's stylesheets.
        sceneMain.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        //set title, scene and show the stage.
        stageMain.setTitle("5.2 Square and Cube");
        stageMain.setScene(sceneMain);
        stageMain.show();


        /*IF to check OS names, as setResizable does not work with some operating systems, such as linux.
        By default we want to do it the longer method with more set actions, and looks worse on windows. If we can just use set
        resizable then we can do that. Linux based OS' do not work with setResizable, which is an issue because
        that is the kernel I am using to program javafx*/
        String osName = System.getProperty("os.name");

        if (osName.indexOf("Windows") != -1)
        {
            //any os that contains "Windows" within its name (e.g. "Windows 10"
            stageMain.setResizable(false);
        }
        else
        {
            /*sets resize properties. Sets current height and width at the top, then sets the max and mins
            dependent on their current value (what was set at the top)*/
            stageMain.setHeight(100);
            stageMain.setWidth(255);
            stageMain.setMaxWidth(stageMain.getWidth());
            stageMain.setMaxHeight(stageMain.getHeight());
            stageMain.setMinWidth(stageMain.getWidth());
            stageMain.setMinHeight(stageMain.getHeight());
        }

    }



    public static void main(String[] args) {
        launch(args);
    }
}
