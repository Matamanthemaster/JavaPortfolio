package studentMarks;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class Controller
{
    /* static variable stores the instance of this class that is being used by FXML. Set when loading the FXML in Main
    class, used by Student class to invoke the method removeStudentTab*/
    public static Controller CurrentController = null;

    //Instances of FXML elements, that we are going to manipulate in some way.
    @FXML private Button btnAddStudent;
    @FXML private TabPane tabpaneStudents; //Tabs stored in here, we can add new students which generates a tab added into the pane.
    @FXML private Text lblDispNoRoom; //Only shown when noStudents == studentTabs.length

    //Array stores instances of class Student. Array size can be changed and all code will work normally.
    private Student[] studentTabs = new Student[50];

    //Stores number of students. If adding, the value is used as next student id. e.g. 0 students, the next id is 0.
    private int noStudents = 0;

    //Method called when btnAddStudent is pressed, event managed by class FXMLLoader
    public void btnAddStudentPressed()
    {
        //Add a new instance of the Student class to the next free array position. Requires the tabpane and new ID.
        studentTabs[noStudents] = new Student(tabpaneStudents, noStudents);

        //increment number of students as new student has been added.
        noStudents++;

        //if we have hit the tab limit, disable input button so can't create more students, show message why.
        if (noStudents == studentTabs.length)
        {
            lblDispNoRoom.setVisible(true);
            btnAddStudent.setDisable(true);
        }
    }

    //method invoked when the delete button for a student has been pressed. Called from the student
    public void removeStudentTab(int id)
    {
        //check the student we are trying to remove exists.
        if (studentTabs[id] != null)
        {
            /*if id is 0 and is the only element, we can't shift the next element down, so manually delete the student
            and set noStudents to 0 (no students exist). id 0 should be the only one that exists at 1 student according
            to this method*/
            if (id == 0 && noStudents == 1)
            {
                studentTabs[0] = null;
                noStudents = 0;
                return;
            }
            else if (id > -1 && id < studentTabs.length) //if we are between the valid ranges (and noStudents is > 1)
            {
                //delete the current student.
                studentTabs[id] = null;

                //start at the next ID, work down.
                for (int i = id+1; i < studentTabs.length; i++)
                {
                    //if we have not hit the end of array
                    if (studentTabs[i] != null)
                    {
                        /*shift the current element down by 1, decrease its id to the new id based on array position,
                        call update method to fix the name.*/
                        studentTabs[i-1] = studentTabs[i];
                        studentTabs[i-1].id--;
                        studentTabs[i-1].Update(null);//null provided as we are not updating from an ActionEvent.

                        /*set the current element to null as we have finished with it. If the end of elements in array
                        it allows for detecting end on other deletions.*/
                        studentTabs[i] = null;
                    }
                    else
                    {
                        /*if the next element is null then assume we have hit the current end of array, decrement number
                        of students and return as we have removed specific ID from array. Ends loop early. Enable button
                        and remove end of array text as array is not full*/

                        lblDispNoRoom.setVisible(false);
                        btnAddStudent.setDisable(false);

                        noStudents--;
                        return;
                    }
                }
                /*once we have gone through every element decrement number of students as we have been able to remove
                the element at specific ID. enable button and hide text as array is not full.*/

                lblDispNoRoom.setVisible(false);
                btnAddStudent.setDisable(false);

                noStudents--;
            }
            else
            {
                //id is not in valid range. Throw an exception.
                throw new IllegalArgumentException("Value of 'ID' was invalid. Value was " + id + ", it should have been between 0 and " + (studentTabs.length-1) + ".");
            }
        }
        else
        {
            //Could not remove id because it did not exist, throw an exception.
            throw new IllegalArgumentException("Value of 'ID' was invalid. Element at position " + id + " did not exist (was null), so cannot be deleted.");
        }
    }
}