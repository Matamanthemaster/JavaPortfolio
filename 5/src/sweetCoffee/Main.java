package sweetCoffee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stageMain) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene sceneMain = new Scene(root, 300, 275);

        stageMain.setTitle("Hello World");
        stageMain.setScene(sceneMain);
        stageMain.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
