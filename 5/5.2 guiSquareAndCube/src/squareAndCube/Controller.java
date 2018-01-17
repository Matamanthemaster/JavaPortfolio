package squareAndCube;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {

    @FXML private TextField txtExponent;
    @FXML private TextField txtBase;
    @FXML private Label lblResult;

    public void changeText()
    {
        /*default values for base and exponent. If a NumberFormatException is thrown (which it is whenever
         Double.parseDouble() is ran on not a number) then these default values will be used, as this method
         is ran on any text change, and resets base and exponent any time it is invoked.*/
        Double base = 1.0;
        Double exponent = 0.0;

        try
        {
            /*get the values of the text inputs and set them to their respective variable, converting them to
             a double*/
            base = Double.parseDouble(txtBase.getText());
            exponent = Double.parseDouble(txtExponent.getText());
        }
        catch (NumberFormatException e) {
            /*On NumberFormatException, do nothing. Suppresses errors in the console which we do not need to
            know about, this is an expected and solved issue.*/
        }

        //calculate the new result, based on the current values of exponent and base, then set it to the output.
        Double result = Math.pow(base, exponent);

        lblResult.setText(result.toString());
    }
}
