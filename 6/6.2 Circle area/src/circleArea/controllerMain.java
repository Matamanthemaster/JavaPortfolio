package circleArea;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class controllerMain
{
    @FXML private VBox root;//Used for positioning lblOutArea.
    @FXML private TextField txtInRadius;//Input of radius
    @FXML private Text lblOutStatus;//Status messages telling the user their errors.
    @FXML private StackPane stackpaneCircle;//used for positioning lblOutArea
    @FXML private Circle circleOut;//Circle display
    @FXML private Text lblOutArea;//Displaying area

    @FXML
    void txtInRadiusTextChanged()
    {
        //define the radius and a flag to determine if we could get a radius or if it was invalid.
        double radius = 0.0;
        boolean flagRadiusValid = true;

        try
        {
            //try to convert the text of txtInRadius to a Double.
            radius = Double.parseDouble(txtInRadius.getText());
        }
        catch (NumberFormatException ex)
        {
            //if NumberFormatException is thrown (thrown when Double.parseDouble cannot parse), set invalid flag to false.
            flagRadiusValid = false;
        }

        //in case the text is empty override the radius to 0 and the flag to true. Effectively changes the status message.
        if (txtInRadius.getText().equals(""))
        {
            radius = 0;
            flagRadiusValid = true;
        }

        if (flagRadiusValid)
        {
            //if values are between the range (upper from testing).
            if (radius > 0 && radius < 501)
            {
                //calculate the area
                double area = calcCircleArea(radius);

                //Decimal format for formatting decimals to 2 decimal places.
                DecimalFormat df2DP = new DecimalFormat("##########0.0#");

                //removes lblOutArea from all possible parent nodes, so we can reassign where it should go.
                stackpaneCircle.getChildren().remove(lblOutArea);
                root.getChildren().remove(lblOutArea);

                /*Based on testing, if below this arbitrary number the text is too large for the circle, so put it below
                the circle otherwise it will fit so put it inside the circle. Only tested on windows. so could
                be different for a different OS, or even a different resolution.*/
                if (radius < 34)
                {
                    root.getChildren().add(lblOutArea);
                }
                else
                {
                    stackpaneCircle.getChildren().add(lblOutArea);
                }

                /*Hide the status as we can display the area, set the area as a formatted decimal of the area + units.
                Set the circle radius to the new radius and make sure lblOutArea is visible.*/
                lblOutStatus.setVisible(false);
                lblOutArea.setText(String.valueOf(df2DP.format(area) + "px²"));
                circleOut.setRadius(radius);
                lblOutArea.setVisible(true);
            }
            else
            {
                /*if values are not between the ranges, tell the user what they did wrong, and hide the area displays.
                Setting circle radius rather than visible will make so when the last area was very large, lblOutStatus
                and txtInRadius will be visible on screen.*/
                lblOutArea.setVisible(false);
                circleOut.setRadius(0);//
                lblOutStatus.setText("The radius must be between\n1 and 500.");
                lblOutStatus.setVisible(true);
            }
        }
        else
        {
            /*if the radius was invalid, tell the user what they did wrong and hide the area displays.
            Setting circle radius rather than visible will make so when the last area was very large, lblOutStatus
            and txtInRadius will be visible on screen.*/
            lblOutArea.setVisible(false);
            circleOut.setRadius(0);
            lblOutStatus.setText("The radius is invalid.\nIt must be a positive decimal number.");
            lblOutStatus.setVisible(true);
        }
    }

    public double calcCircleArea(double radius)
    {
        //calculate the area of any circle. Return.
        return Math.PI*(Math.pow(radius, 2));
    }
}
