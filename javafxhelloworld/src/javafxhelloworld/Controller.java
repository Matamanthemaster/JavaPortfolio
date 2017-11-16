package javafxhelloworld;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class Controller {
    @FXML private Text lblHello;
    @FXML private Button btnHello;

    @FXML protected void btnHelloPressed()
    {
        btnHello.setVisible(false);
        lblHello.setVisible(true);
    }
}
