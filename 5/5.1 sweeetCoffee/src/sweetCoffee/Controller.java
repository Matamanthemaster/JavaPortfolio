package sweetCoffee;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class Controller {

    //gets instances of elements from the FXML via their fx:id.
    @FXML private Button btnNothing;
    @FXML private Button btnSugar;
    @FXML private Button btnSweetener;
    @FXML private Text lblOutNothing;
    @FXML private Text lblOutSugar;
    @FXML private Text lblOutSweetener;
    @FXML private Text lblOutNoVotes;

    //incremental ints.
    private int noNone = 0;
    private int noSugar = 0;
    private int noSweetener = 0;
    private int totalVotes = 0;

    //called when btnNothing is pressed
    @FXML
    protected void btnNothingPressed()
    {
        noNone++;

        //set the number who voted none to the none display, grammatically formatting it.
        if (noNone == 1)
        {
            lblOutNothing.setText("1 person does not sweeten coffee.");
        }
        else if (noNone >= 1)
        {
            lblOutNothing.setText(noNone + " people do not sweeten coffee.");
        }

        /*runs method that should run each time a button is pressed.
        Provides utilities such as checking visibility, counting total votes and disabling at 5 votes.*/
        buttonPressed();
    }

    //called when btnSugar is pressed.
    @FXML
    protected void btnSugarPressed()
    {
        noSugar++;

        //set the number who voted sugar to the none display, grammatically formatting it.
        if (noSugar == 1)
        {
            lblOutSugar.setText("1 person uses sugar.");
        }
        else if (noSugar >= 1)
        {
            lblOutSugar.setText(noSugar + " people use sugar.");
        }

        buttonPressed();
    }

    //called when btnSweetener is pressed.
    @FXML
    protected void btnSweetenerPressed()
    {

        noSweetener++;

        //set the number who voted sweetener to the none display, grammatically formatting it.
        if (noSweetener == 1)
        {
            lblOutSweetener.setText("1 person uses sweetener.");
        }
        else if (noSweetener >= 1)
        {
            lblOutSweetener.setText(noSweetener + " people use sweetener.");
        }

        buttonPressed();
    }

    private void buttonPressed()
    {
        //makes sure that the outputs are visible.
        if (!lblOutNothing.isVisible())
        {
            lblOutNothing.setVisible(true);
            lblOutSugar.setVisible(true);
            lblOutSweetener.setVisible(true);
        }

        //calculates the new total of votes.
        totalVotes = noNone + noSugar + noSweetener;

        //displays the total votes on lblNoVotes with correct grammar.
        if (totalVotes == 1)
        {
            lblOutNoVotes.setText("1 person has voted.");
        }
        else if (totalVotes > 1)
        {
            lblOutNoVotes.setText(totalVotes + " people have voted.");
        }

        //ends the voting by disabling buttons when we reach the maximum votes specified in the question. This prevents additional inputs.
        if (totalVotes > 4)
        {
            btnNothing.setDisable(true);
            btnSugar.setDisable(true);
            btnSweetener.setDisable(true);
        }
    }

}
