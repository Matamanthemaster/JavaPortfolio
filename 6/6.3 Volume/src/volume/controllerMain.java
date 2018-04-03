package volume;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class controllerMain
{
    @FXML private VBox root;//Used for positioning lblOutArea.
    @FXML private StackPane stackpaneCylinder;//used for positioning lblOutArea
    @FXML private Text lblOutStatus;//Status messages telling the user their errors.
    @FXML private TextField txtInRadius;//Input of radius
    @FXML private TextField txtInHeight;
    @FXML private Cylinder cylinderOut;//Cylinder display
    @FXML private Text lblOutArea;//Displaying area

    @FXML
    void txtInTextChanged()
    {
        //define the radius and height and flags to determine if the radius or height are invalid.
        double radius = 0.0;
        double height = 0.0;
        boolean flagRadiusValid = true;
        boolean flagHeightValid = true;

        //get radius
        try
        {
            //try to convert the text of txtInRadius to a Double.
            radius = Double.parseDouble(txtInRadius.getText());

        } catch (NumberFormatException ex)
        {
            //if NumberFormatException is thrown (thrown when Double.parseDouble cannot parse), set invalid flag to false.
            flagRadiusValid = false;
        }

        //get height
        try
        {
            //try to convert the text of txtInHeight to a double
            height = Double.parseDouble(txtInHeight.getText());
        }
        catch (NumberFormatException ex)
        {
            //if NumberFormatException is thrown, height is invalid, set valid flag to false.
            flagHeightValid = false;
        }

        /*in case either text is empty, override the respective value to 0 and the respective flag to true. Effectively
        changes the status message.*/
        if (txtInRadius.getText().equals(""))
        {
            radius = 0;
            flagRadiusValid = true;
        }

        if (txtInHeight.getText().equals(""))
        {
            height = 0;
            flagHeightValid = true;
        }

        //if radius and heigh were both valid
        if (flagRadiusValid && flagHeightValid)
        {
            //if the radius is not between two arbitrary values (from testing), radius is invalid.
            if (radius <= 0 || radius > 900)
            {
                /*if values are not between the ranges, tell the user what they did wrong, and hide the volume displays.
                Setting cylinder dimentions rather than visible will make so when the last area was very large, lblOutStatus
                and txtInRadius will be visible on screen.*/
                lblOutArea.setVisible(false);
                cylinderOut.setRadius(0);
                cylinderOut.setHeight(0);
                lblOutStatus.setText("The radius must be between\n1 and 900.");
                lblOutStatus.setVisible(true);
            }
            else if (height <= 0 || height > 900) //if height !within arbitrary range (from testing), height is invalid.
            {
                /*if values are not between the ranges, tell the user what they did wrong, and hide the area displays.
                Setting cylinder dimentions rather than visible will make so when the last area was very large, lblOutStatus
                and txtInRadius will be visible on screen.*/
                lblOutArea.setVisible(false);
                cylinderOut.setRadius(0);
                cylinderOut.setHeight(0);
                lblOutStatus.setText("The height must be between\n1 and 900.");
                lblOutStatus.setVisible(true);
            }
            else
            {
                //calculate the area and volume.
                double area = calcCylinderArea(radius);
                double volume = calcCylinderArea(area, height);

                //Decimal format for formatting decimals to 2 decimal places.
                DecimalFormat df2DP = new DecimalFormat("#########0.0#");

                //removes lblOutArea from all possible parent nodes, so we can reassign where it should go.
                stackpaneCylinder.getChildren().remove(lblOutArea);
                root.getChildren().remove(lblOutArea);

                /*Based on testing, if below this arbitrary number the text is too large for the cylinder, so put it below
                the cylinder otherwise it will fit so put it inside the cylinder. Only tested on windows. so could
                be different for a different OS, or a different resolution.*/
                if (radius >= 40 && height >= 22)
                {
                    stackpaneCylinder.getChildren().add(lblOutArea);
                }
                else
                {
                    root.getChildren().add(lblOutArea);
                }

                /*Hide the status as we can display the volume, set the area as a formatted decimal of the area + units.
                Set the cylinder radius to the new radius and make sure lblOutArea is visible.*/
                lblOutStatus.setVisible(false);
                lblOutArea.setText(String.valueOf(df2DP.format(volume) + "px³"));
                cylinderOut.setRadius(radius);
                cylinderOut.setHeight(height);
                lblOutArea.setVisible(true);
            }
        }
        else
        {
            /*if the radius or height were invalid, tell the user what they did wrong and hide the area displays.
            Setting cylinder dimentions rather than visible will make so when the last volume was very large, lblOutStatus
            and txtInRadius will be visible on screen.*/
            lblOutArea.setVisible(false);
            cylinderOut.setRadius(0);

            lblOutStatus.setVisible(true);

            //determines what was invalid and customises the status message to tell the user what they need to fix.
            if (!flagRadiusValid && flagHeightValid)
            {
                lblOutStatus.setText("The radius is invalid.\nIt must be a positive decimal number.");
            }
            else if (flagRadiusValid && !flagHeightValid)
            {
                lblOutStatus.setText("The height is invalid.\nIt must be a positive decimal number.");
            }
            else
            {
                lblOutStatus.setText("The radius and height are invalid.\nThey must be positive decimal numbers.");
            }
        }
    }

    public double calcCylinderArea(double radius)
    {
        //calculate the area of any cylinder.
        return Math.PI*(Math.pow(radius, 2));
    }

    public double calcCylinderArea(double area, double height)
    {
        //calculate volume from area and height.
        return area * height;
    }
}
