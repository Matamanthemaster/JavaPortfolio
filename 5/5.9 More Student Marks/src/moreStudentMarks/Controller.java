package moreStudentMarks;

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
    @FXML private Text lblOutNoStudents;
    @FXML private Text lblOutNoPass;
    @FXML private Text lblOutNoFail;
    @FXML private Text lblOutAvgMark;

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

        //update user statistics. Pushes average down, increases noFails.
        updateStatistics();

        //if we have hit the tab limit, disable input button so can't create more students, show message why.
        if (noStudents == studentTabs.length)
        {
            lblDispNoRoom.setVisible(true);
            btnAddStudent.setDisable(true);
        }
    }

    //Method called to update the application statistics and counts displayed to the user.
    public void updateStatistics()
    {
        if (noStudents < 1)
        {
            //if no students state that there are none, hide the statistics labels as they can't show anything.
            lblOutNoStudents.setText("There are no students.");

            lblOutNoPass.setVisible(false);
            lblOutNoFail.setVisible(false);
            lblOutAvgMark.setVisible(false);
        }
        else
        {
            //Format the text of lblOutNoStudents for the context of singular or multiple.
            if (noStudents == 1)
            {
                lblOutNoStudents.setText("There is 1 student.");
            }
            else
            {
                lblOutNoStudents.setText("There are " + noStudents + "  students.");
            }

            //set temporary variables for storing the statistics per Student element.
            int noPass = 0;
            int noFail = 0;
            int avgMark = 0;
            int noValidMarks = 0;//Used to calculate average based only on valid marks. (not on NumberFormatException)

            //for each student element
            for (int i = 0; i < noStudents; i++)
            {
                //Get the string value of txtMark to evaluate. Set a variable to store the int version.
                String curMarkText = studentTabs[i].txtMark.getText();
                int curMark;

                try
                {
                    //convert the text to an intager.
                    curMark = Integer.parseInt(curMarkText);
                }
                catch (NumberFormatException e)
                {
                    //if the text could not be converted default to -1 (invalid).
                    curMark = -1;
                }

                //if valid
                if (curMark > -1 && curMark < 101 )
                {
                    //increment the average by the current mark, increment number of valid marks.
                    avgMark += curMark;
                    noValidMarks++;

                    //determine if they passed or fail to display.
                    if (curMark >= 40)
                    {
                        noPass++;
                    }
                    else if (curMark < 40 && curMark != -1)
                    {
                        noFail++;
                    }
                }
            }

            //Display how many passed, formatting it grammatically.
            if (noPass == 0)
            {
                lblOutNoPass.setText("No students have passed.");
            }
            else if (noPass == 1)
            {
                lblOutNoPass.setText("1 student has passed.");
            }
            else
            {
                lblOutNoPass.setText(noPass + " students have passed.");
            }

            //Display how many failed, formatting it grammatically.
            if (noFail == 0)
            {
                lblOutNoFail.setText("No students have failed.");
            }
            else if (noFail == 1)
            {
                lblOutNoFail.setText("1 student has failed.");
            }
            else
            {
                lblOutNoFail.setText(noFail + " students have failed.");
            }

            /*calculate the average from the total and number of students that had valid marks if there are valid marks.
            Otherwise there are no valid marks, avgMark will not have been incremented so should be 0, do not divide
            (otherwise you would divide 0 by 0).*/
            if (noValidMarks > 0)
            {
                avgMark = avgMark/noValidMarks;
            }

            //Display the average
            lblOutAvgMark.setText("The average mark is " + avgMark + ".");

            //if the statistics out labels were not visible before, make them visible (basing off of a single label).
            if (!lblOutAvgMark.isVisible())
            {
                lblOutNoPass.setVisible(true);
                lblOutNoFail.setVisible(true);
                lblOutAvgMark.setVisible(true);
            }
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
                updateStatistics();
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
                        //if the next element is null then assume we have hit the current end of array, break the loop.
                        break;
                    }
                }
                /*once we have gone through every element decrement number of students as we have been able to remove
                the element at specific ID. enable button and hide text as array is not full. Updates statistics to
                show less students and might lower the average.*/

                lblDispNoRoom.setVisible(false);
                btnAddStudent.setDisable(false);

                noStudents--;

                updateStatistics();
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