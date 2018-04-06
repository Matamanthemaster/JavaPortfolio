package morePay;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class Controller
{
    //static variables store important application variables.
    static double hoursNormalCutoff = 40;//hours over this value will be paid in overtime. Below in normal hours.
    static boolean flagVerifyDelete = true; //flag for enabling and disabling the deletion verification dialogue.

    //Instances of FXML elements, that we are going to manipulate in some way.
    @FXML private Button btnAddEmployee;
    @FXML private TabPane tbpnEmployees; //Tabs stored in here, we can add new employees which generates a tab added into the pane.
    @FXML private Label lblDispNoRoom; //Only shown when noEmployees == employeeTabs.length

    //Array stores instances of class Employee. Array size can be changed and all code will work normally.
    private Employee[] employeeTabs = new Employee[50];

    //Stores number of employees. If adding, the value is used as next employee id. e.g. 0 employees, the next id is 0.
    private int noEmployees = 0;

    //Method called when btnAddEmployee is pressed, event managed by class FXMLLoader
    @FXML
    void btnAddEmployeePressed()
    {
        /*Add a new instance of the Employee class to the next free array position. Requires the current instance of this
        class (the parent to the class instance we are making), tabpane and new ID.*/
        employeeTabs[noEmployees] = new Employee(this, tbpnEmployees, noEmployees, hoursNormalCutoff);
        //increment number of employees as new employee has been added.
        noEmployees++;

        //if we have hit the tab limit, disable input button so can't create more employees, show message why.
        if (noEmployees == employeeTabs.length)
        {
            lblDispNoRoom.setVisible(true);
            btnAddEmployee.setDisable(true);
        }
    }

    //method invoked when the delete button for a employee has been pressed. Called from the employee
    void removeEmployeeTab(int id)
    {
        //check the employee we are trying to remove exists.
        if (employeeTabs[id] != null)
        {
            /*if id is 0 and is the only element, we can't shift the next element down, so manually delete the employee
            and set noEmployees to 0 (no employees exist). id 0 should be the only one that exists at 1 employee according
            to this method*/
            if (id == 0 && noEmployees == 1)
            {
                employeeTabs[0] = null;
                noEmployees = 0;
                return;
            }
            else if (id > -1 && id < employeeTabs.length) //if we are between the valid ranges (and noEmployees is > 1)
            {
                //delete the current employee.
                employeeTabs[id] = null;

                //start at the next ID, work down.
                for (int i = id+1; i < employeeTabs.length; i++)
                {
                    //if we have not hit the end of array
                    if (employeeTabs[i] != null)
                    {
                        /*shift the current element down by 1, decrease its id to the new id based on array position,
                        call update method to fix the name.*/
                        employeeTabs[i-1] = employeeTabs[i];
                        employeeTabs[i-1].id--;
                        employeeTabs[i-1].Update(null);//null provided as we are not updating from an ActionEvent.

                        /*set the current element to null as we have finished with it. If the end of elements in array
                        it allows for detecting end on other deletions.*/
                        employeeTabs[i] = null;
                    }
                    else
                    {
                        /*if the next element is null then assume we have hit the current end of array, decrement number
                        of employees and return as we have removed specific ID from array. Ends loop early. Enable button
                        and remove end of array text as array is not full*/

                        lblDispNoRoom.setVisible(false);
                        btnAddEmployee.setDisable(false);

                        noEmployees--;
                        return;
                    }
                }
                /*once we have gone through every element decrement number of employees as we have been able to remove
                the element at specific ID. enable button and hide text as array is not full.*/

                lblDispNoRoom.setVisible(false);
                btnAddEmployee.setDisable(false);

                noEmployees--;
            }
            else
            {
                //id is not in valid range. Throw an exception.
                throw new IllegalArgumentException("Value of 'ID' was invalid. Value was " + id + ", it should have been between 0 and " + (employeeTabs.length-1) + ".");
            }
        }
        else
        {
            //Could not remove id because it did not exist, throw an exception.
            throw new IllegalArgumentException("Value of 'ID' was invalid. Element at position " + id + " did not exist (was null), so cannot be deleted.");
        }
    }
}