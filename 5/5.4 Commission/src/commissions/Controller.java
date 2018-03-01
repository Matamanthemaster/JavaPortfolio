package commissions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    //Controls in FXML, so we can manipulate them in the controller file.
    @FXML Button btnAdd;
    @FXML TextField txtCommission;
    @FXML Tab tabResults;
    @FXML Text lblDispNoCommissions;
    @FXML Text lblDispSumCommissions;
    @FXML Text lblDispAvgCommissions;

    //variables used for commission calculations and displays.
    int noCommissions = 0;
    double sumCommissions = 0;

    public void btnAddPressed()
    {
        //increment number of commissions and convert txtCommission's text to a double, add that to the sum on event btnAdd is pressed.
        noCommissions++;

        try
        {
            sumCommissions += Double.parseDouble(txtCommission.getText());
            txtCommission.setText("");
        }
        catch (java.lang.NumberFormatException e)
        {
            /*if setting a commission does cause an error, decrement the number of commissions, and pretend they didn't add anything. No value will be added to sumCommissions because that line generated the exception.*/
            noCommissions--;
        }
    }

    public void tabChanged()
    {
        //if makes sure we only run on switching to tabResults. Saves wasted instruction processing.
        if (tabResults.isSelected())
        {
            if (noCommissions == 0)
            {
                //if no commissions have been entered, set text to display results grammatically correctly.
                lblDispNoCommissions.setText("No commissions have been entered.");

                //sets visibility for fields to invisible, cleaner UI, if no commissions have been added then nothing to display that isn't already displayed by lblDispNoCommissions.
                lblDispSumCommissions.setVisible(false);
                lblDispAvgCommissions.setVisible(false);
            }
            else
            {
                //if 1 or more commission has been made (inclusive as some text fields do not care about 1 or more than 1).

                //set text and visibility of sum and average text. In that order just in case the program hangs in between steps. Average commissions is calculated inline setting text.
                lblDispSumCommissions.setText("£" + sumCommissions + " total in commissions has been entered.");
                lblDispAvgCommissions.setText("The average (mean) commission is £" + (sumCommissions/noCommissions));
                lblDispSumCommissions.setVisible(true);
                lblDispAvgCommissions.setVisible(true);

                if (noCommissions == 1)
                {
                    //if 1 commission has been entered, set text to display results grammatically correctly.
                    lblDispNoCommissions.setText("1 commission has been entered.");
                }
                else
                {
                    //if >1 commissions have been entered, grammatically format text for more than 1 commission.
                    lblDispNoCommissions.setText(noCommissions + " commissions have been entered.");
                }
            }
        }
    }
}
