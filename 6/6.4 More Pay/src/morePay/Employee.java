package morePay;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_RIGHT;

public class Employee
{
    //Employee's tab attributes
    private Controller parent;
    public Tab tab;
    public int id;
    public double hoursNormalCutoff;
    public double payGross;
    public double payNet;
    public TextField txtForename;
    public TextField txtSurname;
    public TextField txtPayRate;
    public TextField txtOvertimePayRate;
    public TextField txtHoursWorked;
    public CheckBox ckMarried;
    public Label lblOutGrossPay;
    public Label lblOutNetPay;
    public Button btnDelete;

    //method invoked on creating new instance of Employee. Generates a tab with controls and event handling.
    public Employee(Controller parent, TabPane tabParent, int newID, double normalHoursCutoff)
    {
        /*create a tab, store it as the employee's tab attribute, set the ID from the ID provided in the creation and set
        a title for new employees.*/
        this.parent = parent;
        this.tab = new Tab();
        this.id = newID;
        this.tab.setText("E" + newID + ": New Employee");

        //integer determines the cut off between normal hours and overtime hours.
        this.hoursNormalCutoff = normalHoursCutoff;

        /*create elements that will become nested in the tab, and nested in this nested element. Second and Third are
        arbitrary names as their only point is to structure the scene objects. We need to reference them to append
        children. Center objects to everything appears in the middle of the tab.*/
        HBox hbSecond = new HBox();
        VBox vbThird = new VBox();
        hbSecond.setAlignment(CENTER);
        vbThird.setAlignment(CENTER);

        //create javafx scene objects and assign them to this classes attributes. Set up attributes for the scene objects.
        this.txtForename = new TextField();
        this.txtSurname = new TextField();
        this.txtPayRate = new TextField();
        this.txtOvertimePayRate = new TextField();
        this.txtHoursWorked = new TextField();
        this.ckMarried = new CheckBox("Married?");
        this.lblOutGrossPay = new Label();
        this.lblOutGrossPay.setVisible(false);
        this.lblOutNetPay = new Label();
        this.lblOutNetPay.setVisible(false);
        this.btnDelete = new Button("Delete Current Employee");

        /*Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event
        type.*/
        this.txtForename.setOnKeyReleased(this::Update);
        this.txtSurname.setOnKeyReleased(this::Update);
        this.txtPayRate.setOnKeyReleased(this::Update);
        this.txtOvertimePayRate.setOnKeyReleased(this::Update);
        this.txtHoursWorked.setOnKeyReleased(this::Update);
        /*ckMarried fires off an ActionEvent when check status is changed as opposed to the KeyEvent update expects.
        We create a new EventHandler of type ActionEvent that when triggered will run the method handle. handle will
        call the method Update and not provide a KeyEvent, which is not actually required by update to run.*/
        this.ckMarried.setOnAction(ActionEvent -> Update(null));
        this.btnDelete.setOnAction(this::Delete);

        /*encapsulate the text entries within a hbox, and have a text label before them. alignment so text fields match
        in alignment.*/
        HBox hbForename = new HBox();
        hbForename.getChildren().add(new Label("Forename: "));
        hbForename.getChildren().add(this.txtForename);
        hbForename.setAlignment(CENTER_RIGHT);

        HBox hbSurname = new HBox();
        hbSurname.getChildren().add(new Label("Surname: "));
        hbSurname.getChildren().add(this.txtSurname);
        hbSurname.setAlignment(CENTER_RIGHT);

        HBox hbRate = new HBox();
        hbRate.getChildren().add(new Label("Hourly Rate: £"));
        hbRate.getChildren().add(this.txtPayRate);
        hbRate.setAlignment(CENTER_RIGHT);

        HBox hbOvertimeRate = new HBox();
        hbOvertimeRate.getChildren().add(new Label("Overtime Hourly Rate: £"));
        hbOvertimeRate.getChildren().add(this.txtOvertimePayRate);
        hbOvertimeRate.setAlignment(CENTER_RIGHT);

        HBox hbHours = new HBox();
        hbHours.getChildren().add(new Label("Hours Worked: "));
        hbHours.getChildren().add(this.txtHoursWorked);
        hbHours.setAlignment(CENTER_RIGHT);

        VBox vbLblOut = new VBox();
        vbLblOut.getChildren().add(this.lblOutGrossPay);
        vbLblOut.getChildren().add(this.lblOutNetPay);
        vbLblOut.setAlignment(CENTER);
        this.lblOutGrossPay.setTextAlignment(TextAlignment.CENTER);

        /*add the scene objects (and scene objects through their encapsulating hboxes) to their root. Create and add
        spacing so there is space between scene objects.*/
        vbThird.setSpacing(5);
        vbThird.getChildren().add(hbForename);
        vbThird.getChildren().add(hbSurname);
        vbThird.getChildren().add(hbRate);
        vbThird.getChildren().add(hbOvertimeRate);
        vbThird.getChildren().add(hbHours);
        vbThird.getChildren().add(this.ckMarried);
        vbThird.getChildren().add(vbLblOut);
        vbThird.getChildren().add(this.btnDelete);

        //Add the child to tabRoot (that contains all control objects) to tabRoot
        hbSecond.getChildren().add(vbThird);

        //add the tab root to the current tab. Add the current tab to the parent, the tabpane.
        this.tab.setContent(hbSecond);
        tabParent.getTabs().add(this.tab);
    }

    //Double not double so the value can be null, and we can return null.
    public static Double getDoubleValueFromTextfield(TextField txt)
    {
        //tries to rerun the Double value from the current text field.
        try
        {
            return Double.parseDouble(txt.getText());
        }
        catch (NumberFormatException ex)
        {
            //If NumberFormatException is thrown (the text could not be converted to a Double), return null as error happened.
            return null;
        }
    }

    public static int getTaxPercent(CheckBox checkbox)
    {
        //if married rate is 25, if not rate is 30.
        if (checkbox.isSelected())
        {
            return 25;
        }
        else
        {
            return 30;
        }
    }

    public void calcGrossPay()
    {
        //input double values from the text fields.
        Double hoursWorked = getDoubleValueFromTextfield(this.txtHoursWorked);
        Double payRateNormal = getDoubleValueFromTextfield(this.txtPayRate);
        Double payRateOvertime = getDoubleValueFromTextfield(this.txtOvertimePayRate);

        //if all variables are not null (were valid doubles)
        if (hoursWorked != null && payRateNormal != null && payRateOvertime != null)
        {
            //if hours worked are less than or equal to the cut off (from the class variable).
            if (hoursWorked <= this.hoursNormalCutoff)
            {
                //all hours were covered not under overtime, calculate gross pay.
                this.payGross = payRateNormal * hoursWorked;
            }
            else
            {
                /*hours were covered under overtime. Calculate the total number not under overtime, then calculate the
                rest under overtime.*/
                this.payGross = payRateNormal * this.hoursNormalCutoff;
                hoursWorked -= this.hoursNormalCutoff;
                this.payGross += payRateOvertime * hoursWorked;
            }
        }
    }

    public void calcNetPay()
    {
        //Calculates net pay Net = gross - tax((gross/100) * tax percentage from marital status))
        this.payNet = this.payGross - ((this.payGross / 100) * getTaxPercent(this.ckMarried));
    }

    public void dispPay()
    {
        //Creates a DecimalFormat to format the decimals when outputted.
        DecimalFormat dfPrice = new DecimalFormat("############0.00");

        /*Rounding Mode manipulates how DecimalFormat rounds. I noticed that this did not work but according
        to the documentation for the library it should, so I have left it in.*/
        dfPrice.setRoundingMode(RoundingMode.HALF_UP);
        //System.out.println(dfPrice.getRoundingMode());//Testing to see if rounding mode is actually set, which it is.

        //sets the text of the outputs with formatting.
        this.lblOutGrossPay.setText("Gross Pay: £" + dfPrice.format(this.payGross));
        this.lblOutNetPay.setText("Net Pay: £" + dfPrice.format(this.payNet));

        //make sure both outputs are visible.
        this.lblOutGrossPay.setVisible(true);
        this.lblOutNetPay.setVisible(true);
    }

    public void Update(KeyEvent e)
    {
        if (this.txtForename.getText().equals(""))
        {
            //set the text of the tab (what is displayed) to show the employee id and "New Employee" if no forename exists.
            this.tab.setText("E" + this.id + ": New Employee");
        }
        else
        {
            //set the text of the tab (what is displayed) to show the employee id and name. if a forename exists.
            //e.g. E0: Smith, M
            this.tab.setText("E" + id + ": " + this.txtSurname.getText() + ", " + this.txtForename.getText().charAt(0));
        }

        //if either any text input has no value.
        if (txtPayRate.getText().equals("") || txtOvertimePayRate.getText().equals("") || txtHoursWorked.getText().equals(""))
        {
            //hide the output
            this.lblOutGrossPay.setVisible(false);
            this.lblOutGrossPay.setText("");//set to remove the multi-line text (for formatting).
            this.lblOutNetPay.setVisible(false);
        }
        else
        {
            //if both txtPayRate and txtHoursWorked have a value, show the output. Try and calculate gross and net pay.
            lblOutGrossPay.setVisible(true);

            //Input txtPayRate Set text
            Double payRateNormal = getDoubleValueFromTextfield(txtPayRate);

            //determines payRateNormal is valid, store in flagPayNormalInvalid
            boolean flagPayNormalInvalid = payRateNormal == null;

            //input txtPayRateOvertime
            Double payRateOvertime = getDoubleValueFromTextfield(txtOvertimePayRate);

            //determines if payRateOvertime is valid, store in flagPayOvertimeInvalid.
            boolean flagPayOvertimeInvalid = payRateOvertime == null;

            //Input txtHoursWorked
            Double hoursWorked = getDoubleValueFromTextfield(txtHoursWorked);

            //Determines if hoursWorked is valid, store in flagHoursInvalid.
            boolean flagHoursInvalid = hoursWorked == null;



            //Test to format a reason for a failed calculation.
            if (flagHoursInvalid && (flagPayNormalInvalid) || flagPayOvertimeInvalid) //All 3 reasons are invalid.
            {
                this.lblOutGrossPay.setText("The pay rates and hours worked must be\npositive decimal numbers.");
                this.lblOutNetPay.setVisible(false);
            }
            else if (flagPayNormalInvalid || flagPayOvertimeInvalid) //either rate flag is invalid.
            {
                this.lblOutGrossPay.setText("The pay rates must be positive decimal\nnumbers.");
                this.lblOutNetPay.setVisible(false);
            }
            else if (flagHoursInvalid) //Hours invalid
            {
                this.lblOutGrossPay.setText("The hours worked must be a positive decimal\nnumber.");
                this.lblOutNetPay.setVisible(false);
            }
            else //All valid
            {
                /*re-uses temporary flags, assigns them to the result of expressions that determine if they are valid
                (within ranges). E.g. if payRate > 0 and < 5001, then the flag will be false. Only if both flags were
                false before (valid) will we hit the else, so will both start out as false.*/
                flagPayNormalInvalid = !(payRateNormal > 0 && payRateNormal < 5001);
                flagPayOvertimeInvalid = !(payRateOvertime > 0 && payRateOvertime < 5001);
                flagHoursInvalid = !(hoursWorked > 0 && hoursWorked <= 168);

                //check validity flag
                if (!flagPayNormalInvalid && !flagPayOvertimeInvalid && !flagHoursInvalid) //All valid
                {
                    //both values are valid so we can use them to calculate gross and net pay.

                    //update the gross pay for this class.
                    calcGrossPay();

                    //update the net pay for this class.
                    calcNetPay();

                    //display net and gross pay to the user.
                    dispPay();
                }
                else if ((flagPayNormalInvalid || flagPayOvertimeInvalid) && flagHoursInvalid)//if all were invalid.
                {
                    this.lblOutGrossPay.setText("The pay rates must be between 1 and 5000,\n and hours worked between 1 and 168.");
                    this.lblOutNetPay.setVisible(false);
                }
                else if (flagPayNormalInvalid || flagPayOvertimeInvalid) //if either pay rates were invalid.
                {
                    this.lblOutGrossPay.setText("The pay rates must be between 1 and 5000.");
                    this.lblOutNetPay.setVisible(false);
                }
                else if (flagHoursInvalid) //if hours was invalid.
                {
                    this.lblOutGrossPay.setText("The hours worked must be between 1 and 168.");
                    this.lblOutNetPay.setVisible(false);
                }
            }
        }
    }

    void Delete(ActionEvent e)
    {
        //if we want to verify that the user wants to delete the current employee
        if (Controller.flagVerifyDelete)
        {
            /*crate a new alert of type warning, with a title and content text, which informs the user the implications
            of clicking yes. StageStyle.utility means it has no icon.*/
            Alert verifyDeletion = new Alert(Alert.AlertType.WARNING);
            verifyDeletion.setTitle("Verify Action");
            verifyDeletion.setHeaderText(null);
            verifyDeletion.setContentText("Are you sure you want to delete the current user? This action cannot be undone.");
            verifyDeletion.initStyle(StageStyle.UTILITY);

            //button for yes, allows us to detect if the user clicks yes.
            ButtonType verifyDeletionBtnYes = new ButtonType("Yes");

            /*adds button types to the dialogue. Adds the yes button, and creates a no and cancel button (which we don't
            need to store in memory because they will not be used for testing what was pressed).*/
            verifyDeletion.getButtonTypes().setAll(verifyDeletionBtnYes, new ButtonType("No"), new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));

            /*Create an object called result of type optional, meaning that it can have no value. show and wait for a
            result of the dialogue. The result is used to determine what the user clicked on the dialogue.*/
            Optional<ButtonType> result = verifyDeletion.showAndWait();

            /*If the user clicked anything other than yes, return, skipping the deletion part in this method. If the user
            did click yes then it will skip the return, completing the scope of the current if, and then completing the
            deletion portion of this method.*/
            if (result.get() != verifyDeletionBtnYes)
            {
                return;
            }
        }

        //Remove the tab from the tabpane.
        this.tab.getTabPane().getTabs().remove(this.tab);

        //call the method to remove the reference to the instance of this class from the parent class (controller).
        parent.removeEmployeeTab(this.id);
    }
}
