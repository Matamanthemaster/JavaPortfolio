package moreStudentMarks;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    public Text lblDispResult;
    public Button btnDelete;

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

        //create javafx scene objects and assign them to this classes attributes. Set up attributes for the scene objects.
        this.txtForename = new TextField();
        this.txtSurname = new TextField();
        this.txtMark = new TextField();
        this.lblDispResult = new Text();
        this.lblDispResult.setVisible(false);
        this.btnDelete = new Button("Delete Current Student");

        /*Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event
        type.*/
        this.txtForename.setOnKeyReleased(this::Update);
        this.txtSurname.setOnKeyReleased(this::Update);
        this.txtMark.setOnKeyReleased(this::Update);
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

        HBox hbMark = new HBox();
        hbMark.getChildren().add(new Text("Marks: "));
        hbMark.getChildren().add(this.txtMark);
        hbMark.setAlignment(CENTER_RIGHT);


        /*add the scene objects (and scene objects through their encapsulating hboxes) to their root. Create and add
        spacing so there is space between scene objects.*/
        vbThird.setSpacing(5);
        vbThird.getChildren().add(hbForename);
        vbThird.getChildren().add(hbSurname);
        vbThird.getChildren().add(hbMark);
        vbThird.getChildren().add(this.lblDispResult);
        vbThird.getChildren().add(this.btnDelete);

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


        //catch exceptions thrown
        try
        {
            //convert the text of the TextField to an int, throws NumberFormatException if not a valid int.
            int mark = Integer.parseInt(this.txtMark.getText());

            //basic validation for marks.
            if (mark > -1 && mark < 101)
            {
                //check if the student is above or below grade threshold. pass if above, fail if below.
                if (mark < 40)
                {
                    this.lblDispResult.setText("This student has failed.");
                }
                else if (mark >= 40)
                {
                    this.lblDispResult.setText("This student has passed.");
                }

                //make sure that the result is always visible if no NumberFormatException was thrown.
                this.lblDispResult.setVisible(true);
            }
            else
            {
                this.lblDispResult.setText("Mark must be between 0 and 100");
                this.lblDispResult.setVisible(true);
            }
        }
        catch (NumberFormatException x)
        {
            /*exception thrown when trying to parse a number data type and the input string is NaN (Not a Number).
            Hide lblDispResult on NumberFormatException thrown. They have still technically failed but is not shown
            on the student information.*/
            this.lblDispResult.setVisible(false);
        }

        //Update student statistics.
        Controller.CurrentController.updateStatistics();
    }


    public void Delete(ActionEvent e)
    {
        //Remove the tab from the tabpane
        this.tab.getTabPane().getTabs().remove(this.tab);

        /*Call Controller.removeStudentTab to remove the current student from the array studentTabs (and kill this
        instance). Have to get a statically defined variable from Controller that stores the instance of Controller used
        by the FXMLLoader, as that is the instance of the class that stores the studentTabs array and instance of this
        class*/
        Controller.CurrentController.removeStudentTab(this.id);
    }
}
