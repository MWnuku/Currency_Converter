package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class guiController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private ComboBox<?> comboFrom;

    @FXML
    private ComboBox<?> comboTo;

    @FXML
    private Text conv1;

    @FXML
    private Text conv2;

    @FXML
    private Text conv3;

    @FXML
    private Button convButton;

    @FXML
    private TextField txtAmount;


}
