package studentMarks;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class Controller
{
    @FXML Button  btnAddStudent;
    @FXML TabPane tabpaneStudents;
    @FXML Text lblDispNoRoom;

    public Student[] studentTabs = new Student[50];

    int nextStudentID = 0, noStudents = 0;

    public void btnAddStudentPressed()
    {
        if (noStudents < 50)
        {
            noStudents++;
            studentTabs[nextStudentID] = new Student(tabpaneStudents, nextStudentID);
            nextStudentID++;
        }
        else
        {
            lblDispNoRoom.setVisible(true);
            btnAddStudent.setDisable(true);
        }
    }
}