package sweetCoffee;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class Controller
{

    //gets instances of elements from the FXML via their fx:id.
    @FXML private Button btnSweetenNone;
    @FXML private Button btnSweetenSugar;
    @FXML private Button btnSweetenSweetener;
    @FXML private Text lblSweetenNone;
    @FXML private Text lblSweetenSugar;
    @FXML private Text lblSweetenSweetener;
    @FXML private Text lblNoVotes;

    //incremental ints.
    private int noNone = 0 ;
    private int noSugar = 0;
    private int noSweetener = 0;
    private int totalVotes = 0;

    //called when btnSweetNome is pressed
    @FXML protected void btnSweetenNonePressed()
    {
        noNone++;

        //set the number who voted none to the none display, grammatically formatting it.
        if (noNone == 1)
        {
            lblSweetenNone.setText("1 person does not sweeten coffee.");
        }
        else if (noNone >= 1)
        {
            lblSweetenNone.setText(noNone + " people do not sweeten coffee.");
        }

        /*runs method that should run each time a button is pressed.
        Provides utilities such as checking visibility, counting total votes and disabling at 5 votes.*/
        buttonPressed();
    }

    //called when btnSweetenSugar is pressed.
    @FXML protected void btnSweetenSugarPressed()
    {
        noSugar++;

        //set the number who voted sugar to the none display, grammatically formatting it.
        if (noSugar == 1)
        {
            lblSweetenSugar.setText("1 person uses sugar.");
        }
        else if (noSugar >= 1)
        {
            lblSweetenSugar.setText(noSugar + " people use sugar.");
        }

        buttonPressed();
    }

    //called when btnSweetenSweetener is pressed.
    @FXML protected void btnSweetenSweetenerPressed()
    {

        noSweetener++;

        //set the number who voted sweetener to the none display, grammatically formatting it.
        if (noSweetener == 1)
        {
            lblSweetenSweetener.setText("1 person uses sweetener.");
        }
        else if (noSweetener >= 1)
        {
            lblSweetenSweetener.setText(noSweetener + " people use sweetener.");
        }

        buttonPressed();
    }

    private void buttonPressed()
    {
        //makes sure that the outputs are visible.
        if (!lblSweetenNone.isVisible())
        {
            lblSweetenNone.setVisible(true);
            lblSweetenSugar.setVisible(true);
            lblSweetenSweetener.setVisible(true);
        }

        //calculates the new total of votes.
        totalVotes = noNone + noSugar + noSweetener;

        //displays the total votes on lblNoVotes with correct grammar.
        if (totalVotes == 1)
        {
            lblNoVotes.setText("1 person has voted.");
        }
        else if (totalVotes > 1)
        {
            lblNoVotes.setText(totalVotes + " people have voted.");
        }

        //ends the voting by disabling buttons when we reach the maximum votes specified in the question.
        if (totalVotes > 4)
        {
            btnSweetenNone.setDisable(true);
            btnSweetenSugar.setDisable(true);
            btnSweetenSweetener.setDisable(true);
        }
    }

}
