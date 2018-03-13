package payroll;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    public Tab tab;
    private Controller classParent;
    public int id;
    public double payGross;
    public double payNet;
    public TextField txtForename;
    public TextField txtSurname;
    public TextField txtPayRate;
    public TextField txtHoursWorked;
    public Text lblOutGrossPay;
    public Text lblOutNetPay;
    public Button btnDelete;

    //method invoked on creating new instance of Employee. Generates a tab with controls and event handling.
    public Employee(Controller classParent, TabPane tabParent, int newID)
    {
        this.classParent = classParent;

        /*create a tab, store it as the employee's tab attribute, set the ID from the ID provided in the creation and set
        a title for new employees.*/
        this.tab = new Tab();
        this.id = newID;
        this.tab.setText("E" + newID + ": New Employee");

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
        this.txtHoursWorked = new TextField();
        this.lblOutGrossPay = new Text();
        this.lblOutGrossPay.setVisible(false);
        this.lblOutNetPay = new Text();
        this.lblOutNetPay.setVisible(false);
        this.btnDelete = new Button("Delete Current Employee");

        /*Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event
        type.*/
        this.txtForename.setOnKeyReleased(this::Update);
        this.txtSurname.setOnKeyReleased(this::Update);
        this.txtPayRate.setOnKeyReleased(this::Update);
        this.txtHoursWorked.setOnKeyReleased(this::Update);
        this.btnDelete.setOnAction(this::Delete);

        /*encapsulate the text entries within a hbox, and have a text label before them. alignment so text fields match
        in alignment.*/
        HBox hbForename = new HBox();
        hbForename.getChildren().add(new Text("Forename: "));
        hbForename.getChildren().add(this.txtForename);
        hbForename.setAlignment(CENTER_RIGHT);

        HBox hbSurname = new HBox();
        hbSurname.getChildren().add(new Text("Surname: "));
        hbSurname.getChildren().add(this.txtSurname);
        hbSurname.setAlignment(CENTER_RIGHT);

        HBox hbRate = new HBox();
        hbRate.getChildren().add(new Text("Hourly Rate: £"));
        hbRate.getChildren().add(this.txtPayRate);
        hbRate.setAlignment(CENTER_RIGHT);

        HBox hbHours = new HBox();
        hbHours.getChildren().add(new Text("Hours Worked: "));
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
        vbThird.getChildren().add(hbHours);
        vbThird.getChildren().add(vbLblOut);
        vbThird.getChildren().add(this.btnDelete);

        //Add the child to tabRoot (that contains all control objects) to tabRoot
        hbSecond.getChildren().add(vbThird);

        //add the tab root to the current tab. Add the current tab to the parent, the tabpane.
        this.tab.setContent(hbSecond);
        tabParent.getTabs().add(this.tab);
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

        //if either txtPayRate or txtHoursWorked have no value.
        if (txtPayRate.getText().equals("") || txtHoursWorked.getText().equals(""))
        {
            //hide the output
            lblOutGrossPay.setVisible(false);
            this.lblOutNetPay.setVisible(false);
        }
        else
        {
            //if both txtPayRate and txtHoursWorked have a value, show the output. Try and calculate gross and net pay.
            lblOutGrossPay.setVisible(true);


            //set temporary working variables to track double values and flags for varied output messages.
            double payRate = 0;
            double hoursWorked = 0;

            //flags determine if a value is invalid, used to display specific outputs. in relation to what the current issue is.
            boolean flagPayInvalid = false;
            boolean flagHoursInvalid = false;



            //Input txtPayRate

            //catch exceptions thrown
            try
            {
                //convert the text of the TextField to an int, throws NumberFormatException if not a valid int.
                payRate = Double.parseDouble(this.txtPayRate.getText());

            } catch (NumberFormatException x)
            {

                //exception thrown when trying to parse a number data type and the input string is NaN (Not a Number).
                flagPayInvalid = true;

            }



            //Input txtHoursWorked

            //catch exceptions thrown.
            try
            {
                hoursWorked = Double.parseDouble(this.txtHoursWorked.getText());
            } catch (NumberFormatException x)
            {
                //exception thrown when trying to parse a number data type and the input string is NaN (Not a Number).
                flagHoursInvalid = true;
            }



            //Test to format a reason for a failed calculation.
            if (flagHoursInvalid && flagPayInvalid) //Both hours and pay invalid
            {
                this.lblOutGrossPay.setText("The pay rate and hours worked must be\npositive decimal numbers.");
                this.lblOutNetPay.setVisible(false);
            }
            else if (flagPayInvalid) //Pay invalid
            {
                this.lblOutGrossPay.setText("The pay rate must be a positive decimal\nnumber.");
                this.lblOutNetPay.setVisible(false);
            }
            else if (flagHoursInvalid) //Hours invalid
            {
                this.lblOutGrossPay.setText("The hours worked must be a positive decimal\nnumber.");
                this.lblOutNetPay.setVisible(false);
            }
            else //Both valid
            {
                /*re-uses temporary flags, assigns them to the result of expressions that determine if they are valid
                (within ranges). E.g. if payRate > 0 and < 5001, then the flag will be false. Only if both flags were
                false before (valid) will we hit the else, so will both start out as false.*/
                flagPayInvalid = !(payRate > 0 && payRate < 5001);
                flagHoursInvalid = !(hoursWorked > 0 && hoursWorked <= 168);

                //check validity flag
                if (!flagPayInvalid && !flagHoursInvalid) //Both valid.
                {
                    //both values are v alid so we can use them to calculate gross and net pay.

                    //Calculate gross pay from valid pay rate and hours.
                    this.payGross = payRate * hoursWorked;

                    //Set the net pay to gross pay minus the tax rate percentage (30% in the question).
                    this.payNet = this.payGross - ((this.payGross / 100) * Controller.taxPercentage);

                    //set the display outputs
                    this.lblOutNetPay.setVisible(true);

                    //Creates a DecimalFormat to format the decimals when outputted.
                    DecimalFormat df = new DecimalFormat("#####0.00");

                    /*Rounding Mode manipulates how DecimalFormat rounds. I noticed that this did not work but according
                    to the documentation for the library it should, so I have left it in.*/
                    df.setRoundingMode(RoundingMode.HALF_UP);

                    this.lblOutGrossPay.setText("Gross Pay: £" + df.format(this.payGross));
                    this.lblOutNetPay.setText("Net Pay: £" + df.format(this.payNet));
                }
                else if (flagPayInvalid && flagHoursInvalid)//if both were invalid.
                {
                    this.lblOutGrossPay.setText("The pay rate must be between 1 and 5000,\n and hours worked between 1 and 168.");
                    this.lblOutNetPay.setVisible(false);
                }
                else if (flagPayInvalid) //if pay was invalid.
                {
                    this.lblOutGrossPay.setText("The pay rate must be between 1 and 5000.");
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

    private void Delete(ActionEvent e)
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

        //Remove the tab from the tabpane
        this.tab.getTabPane().getTabs().remove(this.tab);

        //call the method to remove the reference to the instance of this class from the parent class (controller).
        this.classParent.removeEmployeeTab(this.id);
    }
}
