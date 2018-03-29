package moduleMarks;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.text.DecimalFormat;
import java.util.Optional;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_RIGHT;

public class Student
{
    //Student's tab attributes
    public Tab tab;
    public int id;
    public TextField txtForename;
    public TextField txtSurname;
    public TextField txtMark;
    public Button btnMarkLeft;
    public Button btnMarkRight;
    public Text lblOutAvgMark;
    public Text lblOutResult;
    public Button btnDelete;
    public double[] arrMarks = new double[6];
    public int curMark;
    public double avgMark;

    //method invoked on creating new instance of Student. Generates a tab with controls and event handling.
    public Student(TabPane parent, int newID)
    {
        /*create a tab, store it as the student's tab attribute, set the ID from the ID provided in the creation and set
        a title for new students.*/
        this.tab = new Tab();
        this.id = newID;
        this.tab.setText("S" + newID + ": New Student");

        /*create elements that will become nested in the tab, and nested in this nested element. Second and Third are
        arbitrary names as their only point is to structure the scene objects. We need to reference them to append
        children. Center objects to everything appears in the middle of the tab.*/
        HBox hbSecond = new HBox();
        VBox vbThird = new VBox();
        hbSecond.setAlignment(CENTER);
        vbThird.setAlignment(CENTER);

        //creates image objects from png files. Images are from http://www.famfamfam.com/lab/icons/silk/
        Image imgArrowLeft = new Image(getClass().getResourceAsStream("img/arrow_left.png"));
        Image imgArrowRight = new Image(getClass().getResourceAsStream("img/arrow_right.png"));

        //create javafx scene objects and assign them to this classes attributes. Set up attributes for the scene objects.
        this.txtForename = new TextField();
        this.txtSurname = new TextField();
        this.txtMark = new TextField();
        this.txtMark.setText("0");
        this.btnMarkLeft = new Button();
        this.btnMarkLeft.setGraphic(new ImageView(imgArrowLeft));
        this.btnMarkLeft.setDisable(true);
        this.btnMarkRight = new Button();
        this.btnMarkRight.setGraphic(new ImageView(imgArrowRight));
        this.lblOutAvgMark = new Text();
        this.lblOutResult = new Text();
        this.lblOutResult.setVisible(false);
        this.btnDelete = new Button("Delete Current Student");

        /*Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event
        type.*/
        this.txtForename.setOnKeyReleased(this::Update);
        this.txtSurname.setOnKeyReleased(this::Update);
        this.txtMark.setOnKeyReleased(this::Update);
        this.btnMarkLeft.setOnAction(this::changeMark);
        this.btnMarkRight.setOnAction(this::changeMark);
        this.btnDelete.setOnAction(this::Delete);

        //Display 0 in txtMark by default as all elements in arrMarks start as 0.
        this.txtMark.setText("0");

        /*encapsulate the text entries within a hbox, and have a text label before them. alignment so text fields match
        in alignment.*/
        HBox hbForename = new HBox();
        hbForename.getChildren().add(new Text("Forename: "));
        hbForename.getChildren().add(this.txtForename);
        hbForename.setAlignment(CENTER);

        HBox hbSurname = new HBox();
        hbSurname.getChildren().add(new Text("Surname: "));
        hbSurname.getChildren().add(this.txtSurname);
        hbSurname.setAlignment(CENTER);

        HBox hbMark = new HBox();
        hbMark.getChildren().add(new Text("Marks: "));
        hbMark.getChildren().add(this.btnMarkLeft);
        hbMark.getChildren().add(this.txtMark);
        hbMark.getChildren().add(this.btnMarkRight);
        hbMark.setAlignment(CENTER);


        /*add the scene objects (and scene objects through their encapsulating hboxes) to their root. Create and add
        spacing so there is space between scene objects.*/
        vbThird.setSpacing(5);
        vbThird.getChildren().add(hbForename);
        vbThird.getChildren().add(hbSurname);
        vbThird.getChildren().add(hbMark);
        vbThird.getChildren().add(this.lblOutAvgMark);
        vbThird.getChildren().add(this.lblOutResult);
        vbThird.getChildren().add(this.btnDelete);

        //sets margins to prettify the application.
        vbThird.setMargin(hbForename, new Insets(10, 0, 0, 0));
        vbThird.setMargin(hbMark, new Insets(15, 0, 0, 0));
        vbThird.setMargin(this.lblOutResult, new Insets(10, 0, 0, 0));
        vbThird.setMargin(this.btnDelete, new Insets(10, 0, 0, 0));

        //Add the child to tabRoot (that contains all control objects) to tabRoot
        hbSecond.getChildren().add(vbThird);

        //add the tab root to the current tab. Add the current tab to the parent, the tabpane.
        this.tab.setContent(hbSecond);
        parent.getTabs().add(this.tab);
    }

    public void Update(KeyEvent e)
    {
        if (this.txtForename.getText().equals(""))
        {
            //set the text of the tab (what is displayed) to show the student id and "New Student" if no forename exists.
            this.tab.setText("S" + this.id + ": New Student");
        }
        else
        {
            //set the text of the tab (what is displayed) to show the student id and name. if a forename exists.
            //e.g. S0: Smith, M
            this.tab.setText("S" + id + ": " + this.txtSurname.getText() + ", " + this.txtForename.getText().charAt(0));
        }



        try
        {
            //convert the text of the TextField to an int, throws NumberFormatException if not a valid int.
            double mark = Double.parseDouble(this.txtMark.getText());

            if (mark < 0 || mark > 100)
            {
                //set mark to -1 if it is invalid
                arrMarks[curMark] = -1;
            }
            else
            {
                //set the current marks element to the converted value of mark if mark is valid.
                arrMarks[curMark] = mark;
            }
        }
        catch (NumberFormatException x)
        {
            /*On NumberFormatException we know mark is not a number, set the mark value in arrMarks to -1 so we know a
            mark is invalid.*/
            arrMarks[curMark] = -1;
        }

        //flag catches invalid marks.
        boolean flagMarksValid = true;

        //resets avgMark to recalculate the average
        avgMark = 0;

        for (int i = 0; i < arrMarks.length; i++)
        {
            //increment avgMark by the current element
            avgMark += arrMarks[i];

            //if the current mark (any in the array) is invalid, set the flag to false.
            if (arrMarks[i] == -1)
            {
                flagMarksValid = false;
            }
        }

        //if valid flag is true (no elements were invalid)
        if (flagMarksValid)
        {
            //show the output labels.
            lblOutResult.setVisible(true);
            lblOutAvgMark.setVisible(true);

            //divide avgMark by the number of elements in the array (calculate average from total)
            avgMark /= arrMarks.length;

            DecimalFormat df3DP = new DecimalFormat("##0.0#");

            //display the average
            lblOutAvgMark.setText("The average mark is " + df3DP.format(avgMark));

            //check if the student is above or below grade threshold. pass if above, fail if below.
            if (avgMark < 40)
            {
                this.lblOutResult.setText("This student has failed.");
            }
            else if (avgMark >= 40)
            {
                this.lblOutResult.setText("This student has passed.");
            }
        }
        else
        {
            //if invalid hide output labels and clear avgMark.
            lblOutResult.setVisible(false);
            lblOutAvgMark.setVisible(false);
            avgMark = 0;
        }
    }

    public void changeMark(ActionEvent ev)
    {

        if (ev.getSource() == this.btnMarkLeft)
        {
            //if left button decrement the current mark by 1
            curMark--;
        }
        else if (ev.getSource() == this.btnMarkRight)
        {
            //if right button increment the current mark by 1
            curMark++;
        }



        //<1 = 0 or less, > 4 = 5 or more, else = between 1 and 5.
        if (curMark < 1)
        {
            //if we are at the lowest value or less prevent going too low.
            btnMarkLeft.setDisable(true);
        }
        else if (curMark > arrMarks.length-2)
        {
            //if we are at the highest value or more prevent going too high.
            btnMarkRight.setDisable(true);
        }
        else
        {
            //if our values are between highest and lowest, override previous conditions.
            btnMarkLeft.setDisable(false);
            btnMarkRight.setDisable(false);
        }



        if (arrMarks[curMark] == -1)
        {
            //if invalid set the current mark value to 0 (Only if shifting up or down to current position).
            arrMarks[curMark] = 0;
        }

        //set the text of txtMark to the current mark selected.
        txtMark.setText(String.valueOf(arrMarks[curMark]));
    }

    public void Delete(ActionEvent e)
    {
        //if we want to verify that the user wants to delete the current student
        if (Controller.flagVerifyDelete)
        {
            /*crate a new alert of type warning, with a title and content text, which informs the user the implications
            of clicking yes. StageStyle.utility means it has no icon.*/
            Alert verifyDeletion = new Alert(Alert.AlertType.WARNING);
            verifyDeletion.setTitle("Verify Action");
            verifyDeletion.setHeaderText(null);
            verifyDeletion.setContentText("Are you sure you want to delete the current student? This action cannot be undone.");
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

        /*Call Controller.removeStudentTab to remove the current student from the array studentTabs (and kill this
        instance). Have to get a statically defined variable from Controller that stores the instance of Controller used
        by the FXMLLoader, as that is the instance of the class that stores the studentTabs array and instance of this
        class*/
        Controller.currentController.removeStudentTab(this.id);
    }
}
