package higherOrLower;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    static Stage stageP1;
    static Stage stageP2;
    static controllerP1 controllerP1;
    static controllerP2 controllerP2;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //store instances of stages within the class, including a new stage.
        this.stageP1 = primaryStage;
        this.stageP2 = new Stage();

        //create an FXMLLoader to load the FXML files
        FXMLLoader fxmlLoaderP1 = new FXMLLoader(getClass().getResource("player.fxml"));
        FXMLLoader fxmlLoaderP2 = new FXMLLoader(getClass().getResource("player.fxml"));

        //create a new instance of the controllers
        this.controllerP1 = new higherOrLower.controllerP1();
        this.controllerP2 = new higherOrLower.controllerP2();

        //assign the instance of the controllers to the fxmlLoaders loading the FXML.
        fxmlLoaderP1.setController(this.controllerP1);
        fxmlLoaderP2.setController(this.controllerP2);

        //load the fxml scenes from the loader and store them in a temporary variable.
        Parent rootNodeP1 = fxmlLoaderP1.load();
        Parent rootNodeP2 = fxmlLoaderP2.load();

        //set the titles of the scenes
        this.stageP1.setTitle("5.11 Higher or Lower - Player 1");
        this.stageP2.setTitle("5.11 Higher or Lower - Player 2");

        //set the root nodes to be the scenes for the stages.
        this.stageP1.setScene(new Scene(rootNodeP1, 400, 275));
        this.stageP2.setScene(new Scene(rootNodeP2, 400, 275));

        //show the stages
        this.stageP1.show();
        this.stageP2.show();

        //set onclose event handlers to exit the application when either windows is closed
        stageP1.setOnCloseRequest(this::onClose);
        stageP2.setOnCloseRequest(this::onClose);
    }

    private void onClose(WindowEvent ev)
    {
        //close the application, if one window is closed then assume the clients want to close both.
        Platform.exit();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
