package studentMarks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static javafx.geometry.Pos.CENTER;

public class Student
{
    //Student's tab attributes
    public Boolean exists;
    public Tab tab;
    public int id;
    public TextField txtForename;
    public TextField txtSurname;
    public TextField txtMark;
    public Text lblDispResult;
    public Button btnCurDelete;


    public Student(TabPane parent, int newID)
    {
        //create a tab, store it as the student's tab attribute, set the ID from the ID provided in the creation and set a title for new students.
        this.tab = new Tab();
        this.id = newID;
        this.tab.setText("S" + newID + ": New Student");

        //create elements that will become root and the child of the root (parent to all javafx scene objects). Center objects to everything appears in the middle of the tab.
        HBox tabRoot = new HBox();
        VBox tabRootsChild = new VBox();
        tabRoot.setAlignment(CENTER);
        tabRootsChild.setAlignment(CENTER);

        //create javafx scene objects and assign them to this classes attributes. Set up attributes for the scene objects.
        this.exists = true;
        this.txtForename = new TextField();
        this.txtSurname = new TextField();
        this.txtMark = new TextField();
        this.lblDispResult = new Text();
        this.lblDispResult.setVisible(false);
        this.btnCurDelete = new Button("Delete Current Student");

        //Events handled by existing method. Compiler deals with how we get to the method. Method must implement the event type.
        this.txtForename.setOnKeyReleased(this::Update);
        this.txtSurname.setOnKeyReleased(this::Update);
        this.txtMark.setOnKeyReleased(this::Update);
        this.btnCurDelete.setOnAction(this::Delete);

        //add the scene objects to their root. Create and add spacing so there is space between scene objects
        tabRootsChild.setSpacing(5);
        tabRootsChild.getChildren().add(this.txtForename);
        tabRootsChild.getChildren().add(this.txtSurname);
        tabRootsChild.getChildren().add(this.txtMark);
        tabRootsChild.getChildren().add(this.lblDispResult);
        tabRootsChild.getChildren().add(this.btnCurDelete);

        //Add the child to tabRoot (that contains all control objects) to tabRoot
        tabRoot.getChildren().add(tabRootsChild);

        //add the tab root to the current tab. Add the current tab to the parent, the tabpane.
        this.tab.setContent(tabRoot);
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
        catch (NumberFormatException x)
        {
            //exception thrown when trying to parse a number data type and the input string is NaN (Not a Number).
            //Hide lblDispResult on NumberFormatException thrown.
            this.lblDispResult.setVisible(false);
        }
    }


    public void Delete(ActionEvent e)
    {
        
    }
}
