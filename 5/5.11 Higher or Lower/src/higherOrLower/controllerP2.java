package higherOrLower;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class controllerP2
{
    //FXML injected nodes
    @FXML Text lblDispResults;
    @FXML TextField txtInput;
    @FXML Button btnAction;

    //game variables to store current guess number and the target to try and guess.
    int target;
    int noGuess;

    //initialisation method that sets up player 2 after loading and injecting the FXML (and nodes)
    @FXML
    private void initialize()
    {
        //tells player 2 to wait
        lblDispResults.setText("Wait for player 1 to set a number.");

        //set up btnAction to invoke a method on being clicked, sets its text.
        btnAction.setOnAction(this::btnActionPressed);
        btnAction.setText("Guess");

        //disables button and input to prevent player 2 from submitting a guess before a target is set.
        btnAction.setDisable(true);
        txtInput.setDisable(true);
    }

    //on btnAction being pressed
    public void btnActionPressed(ActionEvent ev)
    {
        //define a guess and valid flag to convert a string to an int.
        int guess = 0;
        boolean flagGuessValid = true;

        try
        {
            //try to convert the text of txtInput to an int.
            guess = Integer.parseInt(txtInput.getText());
        }
        catch (NumberFormatException ex)
        {
            //if NumberFormatException is thrown, txtInput could not be converted to an int, guess was invalid, change flag.
            flagGuessValid = false;
        }

        //if the guess was valid
        if (flagGuessValid)
        {
            //increment guess number by 1. This is the new guess number for this guess.
            noGuess++;

            //if player 2 guessed too high.
            if (guess > target)
            {
                //tell player 2 what they need to do next, tell player 1 what player 2 guessed.
                lblDispResults.setText("Your guess is too high.");
                Main.controllerP1.player2Guess(1, noGuess, guess);
            }
            else if (guess < target)//if player 2 guessed too low.
            {
                //tell player 2 what they need to do next, tell player 1 what player 2 guessed.
                lblDispResults.setText("Your guess is too low.");
                Main.controllerP1.player2Guess(2, noGuess, guess);
            }
            else//if player 2 guessed correctly (only other condition).
            {
                //tell player 1 player 2 guessed correct, tell player 2 they guessed correct.
                Main.controllerP1.player2Guess(0, noGuess, 0);
                lblDispResults.setText("You guessed correctly in " + noGuess + " guesses.");

                //disable input and button to prevent player 2 from sending new guesses.
                btnAction.setDisable(true);
                txtInput.setDisable(true);

                //clear the input and unset the guess number
                txtInput.setText("");
                noGuess = 0;

            }
        }
        else
        {
            //else if guess was invalid tell player 2 what they did wrong.
            lblDispResults.setText("Your guess must be a number.");
        }
    }

    //on player 1 deciding a target value.
    void p1SetValue(int target)
    {
        //set the class instance variable for target to the target from player 1.
        this.target = target;

        //enable txtInput and btnAction to allow player 2 to guess.
        txtInput.setDisable(false);
        btnAction.setDisable(false);

        //tell player 2 what they need to do.
        lblDispResults.setText("Try and guess the target number player 1 has set.");

        //reset guess number to show player 1 and 2 how many guesses it took to guess correctly.
        noGuess = 0;
    }
}
