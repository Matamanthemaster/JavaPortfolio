package saverBonus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //gets a list of unnamed parameters, the arguments.
        List<String> args = getParameters().getUnnamed();

        //for each argument, check value, on finding an argument do something.
        for (String arg : args)
        {
            if (arg.equals("debug"))
            {
                Controller.flagDebug = true;
            }
        }

        //Create an FXMLLoader to load Main.fxml, and load it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("7.3 Saver Bonus");
        Scene sceneMain = new Scene(root, 300, 400);

        //Add the scene to the stage, and show it.
        primaryStage.setScene(sceneMain);

        /*Set the stage logo. Icon from silk icons, http://www.famfamfam.com/lab/icons/silk/. Uses a Creative Commons
        Attribution 2.5 License.*/
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon.png")));

        //min height essential for making sure the user can't hide the delete button at lower window sizes.
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(300);

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
