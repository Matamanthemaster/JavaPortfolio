package payslips;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private TextField txtHours;
    @FXML private TextField txtRate;
    @FXML private TextField txtDispPay;

    public void textChanged()
    {
        /* define variables to assign the values in the text boxes, which are strings. Default to 0.0 in case
        of NumberFormatException*/
        Double inHoursWorked = 0.0;
        Double inRatePay = 0.0;

        try
        {
            /*get the values of the text inputs and set them to their respective variable, converting them to
             a double*/
            inHoursWorked = Double.parseDouble(txtHours.getText());
            inRatePay = Double.parseDouble(txtRate.getText());
        }
        catch (NumberFormatException e){
            /*On NumberFormatException, do nothing. Suppresses errors in the console which we do not need to
            know about, this is an expected and solved issue.*/
        }

        //calculate the gross pay as double, then set it to the text of the display text box.
        Double outGrossPay = inHoursWorked * inRatePay;

        txtDispPay.setText(outGrossPay.toString());
    }
}
