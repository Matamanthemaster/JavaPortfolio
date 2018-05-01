package saverBonusMethod;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class Controller
{
    //static variables store important application variables.
    static boolean flagDebug = false; //debug flag. If true we are debugging.

    //Instances of FXML elements, that we are going to manipulate in some way.
    @FXML private Button btnAddSaver;
    @FXML private TabPane tbpnParent; //Tabs stored in here, we can add new savers which generates a tab added into the pane.
    @FXML private Label lblDispNoRoom; //Only shown when noSavers == arrSaverTabs.length
    @FXML private TextField txtPercentBonus;

    //Array stores instances of class Saver. Array size can be changed and all code will work normally.
    private Saver[] arrSaverTabs = new Saver[4];

    //Stores number of savers. If adding, the value is used as next saver id. e.g. 0 savers, the next id is 0.
    private int noSavers = 0;

    //Method called when btnAddSaver is pressed, event managed by class FXMLLoader
    @FXML
    void btnAddSaverPressed()
    {
        /*Add a new instance of the Saver class to the next free array position. Requires the current instance of this
        class (the parent to the class instance we are making), tabpane and new ID.*/
        arrSaverTabs[noSavers] = new Saver(this, tbpnParent, noSavers);
        //increment number of savers as new saver has been added.
        noSavers++;

        //if we have hit the tab limit, disable input button so can't create more savers, show message why.
        if (noSavers == arrSaverTabs.length)
        {
            lblDispNoRoom.setVisible(true);
            btnAddSaver.setDisable(true);
        }
    }

    @FXML
    void btnApplyBonusPressed()
    {
        Integer bonusPercentage = Saver.parseTextFieldAsInt(txtPercentBonus);

        //validate txtPercentBonus
        boolean flagPercentBonusValid = false;

        if (bonusPercentage != null)
        {
            flagPercentBonusValid = bonusPercentage > -1 && bonusPercentage < 101;
        }

        if (flagPercentBonusValid)
        {
            for (int i = 0; i < noSavers; i++)
            {
                arrSaverTabs[i].AddPercentBonus(bonusPercentage);
            }
        }
    }

    //method invoked when the delete button for a saver has been pressed. Called from the saver
    void removeTab(int id)
    {
        //check the saver we are trying to remove exists.
        if (arrSaverTabs[id] != null)
        {
            /*if id is 0 and is the only element, we can't shift the next element down, so manually delete the saver
            and set noSavers to 0 (no savers exist). id 0 should be the only one that exists at 1 saver according
            to this method*/
            if (id == 0 && noSavers == 1)
            {
                arrSaverTabs[0] = null;
                noSavers = 0;
                return;
            }
            else if (id > -1 && id < arrSaverTabs.length) //if we are between the valid ranges (and noSavers is > 1)
            {
                //delete the current saver.
                arrSaverTabs[id] = null;

                //start at the next ID, work down.
                for (int i = id+1; i < arrSaverTabs.length; i++)
                {
                    //if we have not hit the end of array
                    if (arrSaverTabs[i] != null)
                    {
                        /*shift the current element down by 1, decrease its id to the new id based on array position,
                        call update method to fix the name.*/
                        arrSaverTabs[i-1] = arrSaverTabs[i];
                        arrSaverTabs[i-1].id--;
                        arrSaverTabs[i-1].UpdateName(null);//null provided as we are not updating from an ActionEvent.

                        /*set the current element to null as we have finished with it. If the end of elements in array
                        it allows for detecting end on other deletions.*/
                        arrSaverTabs[i] = null;
                    }
                    else
                    {
                        /*if the next element is null then assume we have hit the current end of array, decrement number
                        of savers and return as we have removed specific ID from array. Ends loop early. Enable button
                        and remove end of array text as array is not full*/

                        lblDispNoRoom.setVisible(false);
                        btnAddSaver.setDisable(false);

                        noSavers--;
                        return;
                    }
                }
                /*once we have gone through every element decrement number of savers as we have been able to remove
                the element at specific ID. enable button and hide text as array is not full.*/

                lblDispNoRoom.setVisible(false);
                btnAddSaver.setDisable(false);

                noSavers--;
            }
            else
            {
                //id is not in valid range. Throw an exception.
                throw new IllegalArgumentException("Value of 'ID' was invalid. Value was " + id + ", it should have been between 0 and " + (arrSaverTabs.length-1) + ".");
            }
        }
        else
        {
            //Could not remove id because it did not exist, throw an exception.
            throw new IllegalArgumentException("Value of 'ID' was invalid. Element at position " + id + " did not exist (was null), so cannot be deleted.");
        }
    }
}