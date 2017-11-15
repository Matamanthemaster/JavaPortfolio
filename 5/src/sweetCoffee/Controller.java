package sweetCoffee;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class Controller
{

    @FXML private Button btnSweetNo;
    @FXML private Button btnSweetSugar;
    @FXML private Button btnSweetSweetner;
    @FXML private Text lblSweetNo;
    @FXML private Text lblSweetSugar;
    @FXML private Text lblSweetSweetener;

    private int noNothing = 0 ;
    private int noSugar = 0;
    private int noSweetener = 0;

    @FXML protected void btnSweetNoPressed()
    {
        noNothing++;

        if (noNothing == 1)
        {
            lblSweetNo.setText("1 person does not sweeten coffee.");
        }
        else if (noNothing >= 1)
        {
            lblSweetNo.setText(noNothing + "people do not sweeten coffee.");
        }
    }

    @FXML protected void btnSweetSugarPressed()
    {
        noSugar++;

        if (noSugar == 1)
        {
            lblSweetSugar.setText("1 person uses sugar.");
        }
        else if (noSugar >= 1)
        {
            lblSweetSugar.setText(noSugar + "people use sugar.");
        }
    }

    @FXML protected void btnSweetSweetenerPressed()
    {
        noSweetener++;

        if (noSweetener == 1)
        {
            lblSweetSweetener.setText("1 person uses sweetener.");
        }
        else if (noSweetener >= 1)
        {
            lblSweetSweetener.setText(noSweetener + "people use sweetener.");
        }

    }

}
