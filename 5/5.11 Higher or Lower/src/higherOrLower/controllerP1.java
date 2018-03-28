package higherOrLower;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class controllerP1
{
    //nodes injected by FXMLLoader
    @FXML Text lblDispResults;
    @FXML TextField txtInput;
    @FXML Button btnAction;

    //initialisation method, called after node injection
    @FXML
    private void initialize()
    {
        //sets default results text, sets the specific action for clicking btnAction, sets btnAction's text.
        lblDispResults.setText("Set a number for player 2 to guess.");
        btnAction.setOnAction(this::btnActionPressed);
        btnAction.setText("Set");
    }

    //On setting a target
    private void btnActionPressed(ActionEvent ev)
    {
        //define a target and a flag to determine if the target input was a valid int
        int target = 0;
        boolean flagTargetValid = true;

        try
        {
            //convert string to integer
            target = Integer.parseInt(txtInput.getText());
        }
        catch (NumberFormatException ex)
        {
            //if NumberFormatException thrown text was not a valid int. Set flag to false
            flagTargetValid = false;
        }

        //if the flag was valid
        if (flagTargetValid)
        {
            //disable player 1 inputs to prevent starting multiple games, locks in so they know the number they set.
            btnAction.setDisable(true);
            txtInput.setDisable(true);

            //Tells player 1 that they don't need to do anything any more.
            lblDispResults.setText("Wait for player 2 to make a vote.");

            //calls the player 2 method to start the game for player 2. Passes the target int that is valid to test against.
            Main.controllerP2.p1SetValue(target);
        }
        else
        {
            //else if flag was false, number was invalid, tell player 1 setting a target failed because it was invalid.
            lblDispResults.setText("Target value must be a number.");
        }
    }

    //on a guess by player 2
    void player2Guess(int type, int noGuess, int guess)
    {
        /*
        type = an arbitrary value passed from one class to another. It represents where in relation player 2 guessed.
            0 = it matched / they are the same
            1 = it was higher
            2 = it was lower
        noGuess = number of the guess sent for display and results.
        guess = number player 2 guessed, for informing player 1 how close player 2 was.
        */

        //if guess == target
        if (type == 0)
        {
            //tell player 1 that player 2 won.
            lblDispResults.setText("Player 2 guessed correctly on guess #" + noGuess + ".\nEnter a new number to play again.");

            //allow player 1 to set a new target.
            txtInput.setDisable(false);
            btnAction.setDisable(false);

            //clear last target.
            txtInput.setText("");
        }
        else if (type == 1)//if guess > target
        {
            //tell player 1 player 2 was too high, the guess number and the guess value.
            lblDispResults.setText("Player 2 guessed incorrectly on guess #" + noGuess + ".\nThey guessed too high with " + guess + ".");
        }
        else if (type == 2)//if guess < target
        {
            //tell player 1 player 2 was too low, the guess number and the guess value.
            lblDispResults.setText("Player 2 guessed incorrectly on guess #" + noGuess + ".\nThey guessed too low with " + guess + ".");
        }
    }
}
