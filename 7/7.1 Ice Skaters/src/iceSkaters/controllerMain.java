package iceSkaters;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class controllerMain
{
    //Controls from the FXML for processing, generated from FXMLLoader
    @FXML protected Label lblDescScores;
    @FXML protected Button btnScoreLeft;
    @FXML protected Button btnScoreRight;
    @FXML protected TextField txtInScores;
    @FXML protected CheckBox ckEdgeCases;
    @FXML protected Label lblOutAvg;

    //Application Variables
    protected int curScore = 0;//The score index currently being edited/viewed
    protected double[] arrInScores = new double[6];//Array to store all the scores raw (as inputted).
    protected double[] arrOutScores = new double[arrInScores.length];//Array to store all the scores sorted (for output).
    protected double average;//The average calculated.

    @FXML
    protected void initialize()
    {
        //On initialising set the arrow images on the left and right buttons. Images from
        //  http://www.famfamfam.com/lab/icons/silk/
        btnScoreLeft.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("img/arrow_left.png"))));
        btnScoreRight.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("img/arrow_right.png"))));
    }

    @FXML
    protected void btnScoreLeftPressed()
    {
        //On pressing left button

        //decrease the current score index.
        curScore--;

        //Assume the next element up has to exist (as we have just come from it), allow it to be navigated to.
        btnScoreRight.setDisable(false);

        //if we are at the lowest score index, next element down will be out of bounds, disable button to prevent error.
        if (curScore == 0)
        {
            btnScoreLeft.setDisable(true);
        }

        //display the stored text from the array to the text box for editing / viewing.
        txtInScores.setText(String.valueOf(arrInScores[curScore]));

        //Change the label to show which score is being edited/viewed.
        lblDescScores.setText("Score " + String.valueOf(curScore));
    }

    @FXML
    protected void btnScoreRightPressed()
    {
        //increase the current score index.
        curScore++;

        //Assume the next element down has to exist (as we have just come from it), allow it to be navigated to.
        btnScoreLeft.setDisable(false);

        //If we are at the highest score index, next element up will be out of bounds, disable button to prevent error.
        if (curScore >= arrInScores.length -1)
        {
            btnScoreRight.setDisable(true);
        }

        //display the stored text from the array to the text box for editing / viewing.
        txtInScores.setText(String.valueOf(arrInScores[curScore]));

        //Change the label to show which score is being edited/viewed.
        lblDescScores.setText("Score " + String.valueOf(curScore));

    }

    @FXML
    protected void txtInScoresTextChanged()
    {
        //Assume a blank score is 0.0
        if (txtInScores.getText().equals(""))
        {
            arrInScores[curScore] = 0.0;
        }

        try
        {
            //try to store the text of txtInScores in arrInStores at the current index.
            arrInScores[curScore] = Double.parseDouble(txtInScores.getText());
        }
        catch (NumberFormatException ex)
        {
            //If NumberFormatException is thrown the text could not be a Double.

            /*Set the current element in the array to 0, resetting the value but not the display (until they return to
            the current index).*/
            arrInScores[curScore] = 0.0;

            //tell the user what they did wrong, return to prevent displaying an average and resetting the message.
            lblOutAvg.setText("All scores must be numbers.");
            return;
        }

        //if the score was not between the values (and was not valid)
        if (arrInScores[curScore] < 0 || arrInScores[curScore] > 10)
        {
            /*Set the current element in the array to 0, resetting the value but not the display (until they return to
            the current index).*/
            arrInScores[curScore] = 0;

            //tell the user what they did wrong, return to prevent displaying an average and resetting the message.
            lblOutAvg.setText("All scores must be between 0 and 10.");
            return;
        }

        //The value was valid (or blank), calculate average and display the result to the user.
        displayAverage();
    }

    @FXML
    protected void ckEdgeCasesCheckChanged()
    {
        /*On updating the preference for if the user wants to count min and max values, display the average to show an
        average with their preference.*/
        displayAverage();
    }

    protected void displayAverage()
    {
        /*Integer stores how many scores are being taken into account for the average calculation. Starts at the max
        number in order to take away if ckEdgeCases is not checked, or as the default if it is.*/
        int noScores = arrInScores.length;

        if (ckEdgeCases.isSelected())
        {
            /*if ckEdgeCases is checked, the user wants to use all values, clone arrInScores to arrOutScores. noScores
            was already set to the max, which means we want to take all into consideration for the average.*/
            arrOutScores = arrInScores.clone();
        }
        else
        {
            /*if ckEdgeCases is not checked, the user does not want to use the minimum and maximum values in the average
            calculation.*/

            //Set arrOutScores to arrInScores, sorting the values using a bubble sort from lowest to highest.
            arrOutScores = doubleBubbleSort(arrInScores.clone());

            /*Because the array is sorted, the first and last elements are highest and lowest, set temporary variables
            to store the minimum and maximum score, in order to determine other elements that have the same min and max
            values, to remove them from the average calculation.*/
            double minScore = arrOutScores[0];
            double maxScore = arrOutScores[arrOutScores.length -1];

            /*loop through each element in arrOutScores, remove elements that have the same value as min and max, decrease
            the number of scores are going into the average calculation and move the scores not going into the calculation
            to the back (technically not needed, explained later, but makes the array look slightly cleaner).*/
            for (int i = 0; i < noScores; i++)
            {
                //if the current score is the min or max, we want to remove it.
                if (arrOutScores[i] == minScore || arrOutScores[i] == maxScore)
                {
                    //go through the array to the end, moving every element down by 1. Last element will be set to 0.
                    for (int x = i; x < arrOutScores.length -1; x++)
                    {
                        arrOutScores[x] = arrOutScores[x+1];
                        arrOutScores[x+1] = 0;
                    }

                    //decrease i so we check the new element 0 for if it is at the min or max value.
                    i--;

                    /*decrease noScores. Starts at the max, each score that is min or max is taken away so the average
                    calculation is divided by the correct value and the average is calculated correctly Also means that
                    we do not check elements at the end that have already been checked and moved to the other end.*/
                    noScores--;
                }
            }
        }

        //set average to 0 so we can reset it.
        average = 0;

        /*If all scores are min and max then noScored will be 0 because all the scores we do not want to use in the
        calculations. Instead we default to using the score when ckEdgeCases was checked, and we want to calculate the
        average from all the values. Override noScores back to the max and replace arrOutScores with arrInScores fresh.*/
        if (noScores <= 0)
        {
            arrOutScores = arrInScores.clone();
            noScores = arrOutScores.length;
        }

        /*for each score we are taking into account, add it to the average value (We could have kept all the removed
        values as 0 and divided by noScores to get the average, and had this loop as i < arrOutScores.length, but as
        stated before I wanted the array to look more clean and have the empty values at the end of the string).*/
        for (int i = 0; i < noScores; i++)
        {
            average += arrOutScores[i];
        }

        //divide average by the number of scores that went into the calculation. This is the new average.
        average /= noScores;

        /*Create a DecimalFormat to round the average to 1 decimal place. Set rounding mode so it should round the same
        way as taught in school, rather to the nearest whole even number when landing on .5 (exactly between both sides).*/
        DecimalFormat df1DP = new DecimalFormat("#0.0");
        df1DP.setRoundingMode(RoundingMode.HALF_UP);

        //set the average text to display the average, formatting the output to 1 decimal place.
        lblOutAvg.setText("The average score is " + df1DP.format(average) + ".");
    }

    /*Method bubble sorts a double array, lowest to highest. I chose bubble sort as there are not many elements to sort,
    and a quick sort is not needed with this few elements and might be slower.
    Inputs the array and returns the sorted array.*/
    public double[] doubleBubbleSort(double[] a)
    {
        //for each position in the array.
        for (int i = 0; i < a.length; i++)
        {
            /*loop through each position (staring from index 1), check if the current position (of loop 2) is greater than
            the element before it.*/
            for (int x = 1; x < a.length; x++)
            {
                //if the current element and the prior element are not in order.
                if (a[x] < a[x-1])
                {
                    //swap
                    double temp = a[x];
                    a[x] = a[x-1];
                    a[x-1] = temp;
                }
            }
        }

        //the array will be sorted. Return it.
        return a;
    }
}
