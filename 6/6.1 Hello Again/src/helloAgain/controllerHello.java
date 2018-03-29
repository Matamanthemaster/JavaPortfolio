package helloAgain;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class controllerHello
{
    @FXML TextField txtInQuantity;
    @FXML ListView<String> lstOut;

    @FXML
    void txtInQuantityTextChanged()
    {
        int quantity;
        try
        {
            quantity = Integer.parseInt(txtInQuantity.getText());

        }
        catch (NumberFormatException ex)
        {
            quantity = -1;
        }

        if (quantity == -1)
        {
            //if invalid clear the list and tell the user.
            lstOut.getItems().clear();
            lstOut.getItems().add("Quantity value is not a number.");

        }
        else if (quantity > 0 && quantity < 10000001)
        {
            display(quantity);
        }
        else
        {
            lstOut.getItems().clear();
            lstOut.getItems().add("Quantity value must be between 1 and 10000000 (Ten Million).");
        }
    }

    private void display(int x)
    {
        lstOut.getItems().clear();
        for (int i = 0; i < x; i++)
        {
            lstOut.getItems().add("Hello World");
        }
    }
}
