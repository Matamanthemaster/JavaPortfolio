package multiplicationTable;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Controller
{
    //instances of variables injected from FXMLLoader
    @FXML private VBox vbRoot;
    @FXML private TextField txtIntTable;

    /*array stores each node to display the multiplication calculation. Changing the array size changes the number of
    sums displayed. Length -1 sums happen because element 0 is not used. e.g. if length = 13, 12 sums are shown.*/
    private Text[] lblOutMultiplication = new Text[13];

    //method ran after FXMLLoader has injected FXML values
    @FXML
    public void initialize()
    {

        for (int i = 1; i < lblOutMultiplication.length; i++)
        {
            //initialise each element.
            lblOutMultiplication[i] = new Text();

            //Make each element invisible.
            lblOutMultiplication[i].setVisible(false);

            //Add each element to the vbox.
            vbRoot.getChildren().add(lblOutMultiplication[i]);
        }

        //override a previous action on element 1 and set it visible so we can use it to display messages (its special).
        lblOutMultiplication[1].setVisible(true);
    }

    public void txtIntTableTextChanged()
    {
        //get the string value entered into txtIntTable, saves constantly calling the specific method, saving clock cycles.
        String txtIntTableText = txtIntTable.getText();

        //If the text entry is blank, we want to not show a message to the user.
        if (txtIntTableText.equals(""))
        {
            txtIntTableValidationFailed("");

            //return as we have hit an end point
            return;
        }

        int intTable;

        try
        {
            //try to parse the text in txtIntTable as an integer, if not a number throws NumberFormatException.
            intTable = Integer.parseInt(txtIntTableText);
        }
        catch (NumberFormatException e)
        {
            /*on NumberFormatException, when the text of txtIntTable is Not a Number, display it is not a number. Hide
            all nodes after 1.*/
            txtIntTableValidationFailed("Value Not a Number");

            //return as we have hit an end point.
            return;
        }

        //validate that value is between and including 1 and 100.
        if (intTable < 1 || intTable > 100)
        {
            //if not then display that this validation failed. Hide all nodes after 1.
            txtIntTableValidationFailed("Must be between 1 and 100");
        }
        else
        {
            //for each node (starting from 1, because element position corresponds with the multiplication).
            for (int i = 1; i < lblOutMultiplication.length; i++)
            {
                //set the text to be displayed to include the sum and the result of the sum.
                lblOutMultiplication[i].setText(i + "x" + intTable + " = " + (i*intTable));

                /*set the current node visible, in case it was not visible before (in place of checking visibility and
                setting visibility on the event it was already visible.*/
                lblOutMultiplication[i].setVisible(true);
            }
        }
    }

    private void txtIntTableValidationFailed(String message)
    {
        //Set the text of the first node (which is used to display messages) to the reason why validation failed.
        lblOutMultiplication[1].setText(message);

        //for all nodes (except 1 as we need that to show why it failed), make them invisible.
        for (int i = 2; i < lblOutMultiplication.length; i++)
        {
            lblOutMultiplication[i].setVisible(false);
        }
    }

}
