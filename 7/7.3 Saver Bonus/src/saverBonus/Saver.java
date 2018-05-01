package saverBonus;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_RIGHT;

public class Saver
{
    //Saver's tab attributes
    public Tab tab;
    private Controller parent;
    public int id;
    public double balance = 0;
    public TextField txtForename;
    public TextField txtSurname;
    public Label lblOutBalance;
    public TextField txtChangeBalance;
    public static int maxBalance = 10000;
    public Button btnChangeBalance;
    public Button btnDelete;

    //method invoked on creating new instance of Saver. Generates a tab with controls and event handling.
    public Saver(Controller parent, TabPane tabParent, int newID)
    {
        //set starting variables.
        this.parent = parent;
        this.tab = new Tab();
        this.id = newID;

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
        this.lblOutBalance = new Label();
        this.txtChangeBalance = new TextField();
        this.btnChangeBalance = new Button("Change Balance");
        this.btnDelete = new Button("Delete Current Saver");

        /*Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event
        type.*/
        this.txtForename.setOnKeyReleased(this::UpdateName);
        this.txtSurname.setOnKeyReleased(this::UpdateName);
        this.btnChangeBalance.setOnAction(this::UpdateBalance);
        this.btnDelete.setOnAction(this::Delete);

        //Label + TextField
        HBox hbForename = new HBox();
        hbForename.getChildren().add(new Label("Forename: "));
        hbForename.getChildren().add(this.txtForename);
        hbForename.setAlignment(CENTER_RIGHT);

        HBox hbSurname = new HBox();
        hbSurname.getChildren().add(new Label("Surname: "));
        hbSurname.getChildren().add(this.txtSurname);
        hbSurname.setAlignment(CENTER_RIGHT);

        HBox hbTxtChangeBalance = new HBox();
        hbTxtChangeBalance.getChildren().add(new Label("Balance Change: £"));
        hbTxtChangeBalance.getChildren().add(this.txtChangeBalance);
        hbTxtChangeBalance.setAlignment(CENTER_RIGHT);

        VBox vbChangeBalance = new VBox();
        vbChangeBalance.getChildren().add(hbTxtChangeBalance);
        vbChangeBalance.getChildren().add(this.btnChangeBalance);
        //margin to add additional spacing.
        vbChangeBalance.setMargin(this.btnChangeBalance, new Insets(5, 0, 0, 0));
        vbChangeBalance.setAlignment(CENTER);

        /*add the scene objects (and scene objects through their encapsulating hboxes) to their root. Create and add
        spacing so there is space between scene objects.*/
        vbThird.setSpacing(5);
        vbThird.getChildren().add(hbForename);
        vbThird.getChildren().add(hbSurname);
        vbThird.getChildren().add(this.lblOutBalance);
        vbThird.setMargin(this.lblOutBalance, new Insets(5,0, 5, 0));
        vbThird.getChildren().add(vbChangeBalance);
        vbThird.getChildren().add(this.btnDelete);
        vbThird.setMargin(this.btnDelete, new Insets(20, 0, 0, 0));

        //add all elements to their respective parents.
        hbSecond.getChildren().add(vbThird);
        this.tab.setContent(hbSecond);
        tabParent.getTabs().add(this.tab);

        //Set texts to default.
        UpdateName(null);
        DisplayBalance();

    }

    //Double not double so the value can be null, and we can return null.
    public static Double praseTextFieldAsDouble(TextField txt)
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

    public static Integer parseTextFieldAsInt(TextField txt)
    {
        //tries to rerun the Integer value from the current text field.
        try
        {
            return Integer.parseInt(txt.getText());
        }
        catch (NumberFormatException ex)
        {
            //If NumberFormatException is thrown (the text could not be converted to an Integer), return null as error happened.
            return null;
        }
    }

    public void UpdateBalance(ActionEvent ev)
    {
        if (!txtChangeBalance.getText().equals(""))
        {
            //parse the balance.
            Double tempBalance = praseTextFieldAsDouble(txtChangeBalance);

            //if parsed
            if (tempBalance != null)
            {
                //add tempBalance to the balance. -ive + +ive = subtraction.
                this.balance += tempBalance;

                //make sure that balance is within maxBalance*-1 and maxBalance
                if (this.balance > maxBalance)
                {
                    this.balance = maxBalance;
                }
                else if (this.balance < maxBalance * -1)
                {
                    this.balance = maxBalance * -1;
                }

                DisplayBalance();
            }
        }
    }

    //disp cur balance.
    public void DisplayBalance()
    {
        //DecimalFormat to format balance correctly, rounding mode so large decimals are formatted.
        DecimalFormat dfCurrency = new DecimalFormat("###0.00");
        dfCurrency.setRoundingMode(RoundingMode.HALF_UP);

        //Updates the text.
        lblOutBalance.setText("The saver's current balance is £" + dfCurrency.format(this.balance));
    }

    //update cur tab name.
    public void UpdateName(KeyEvent ev)
    {
        if (this.txtForename.getText().equals(""))
        {
            //set the text of the tab (what is displayed) to show the saver id and "New Saver" if no forename exists.
            this.tab.setText("S" + this.id + ": New Saver");
        }
        else
        {
            //set the text of the tab (what is displayed) to show the saver id and name. if a forename exists.
            //e.g. E0: Smith, M
            this.tab.setText("S" + id + ": " + this.txtSurname.getText() + ", " + this.txtForename.getText().charAt(0));
        }
    }

    void Delete(ActionEvent e)
    {
        //if not debugging
        if (!Controller.flagDebug)
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
        parent.removeTab(this.id);
    }
}
