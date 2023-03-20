import connectionApi.ApiConnection;
import connectionApi.JsonConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException, JSONException {
        //launch(args);
        String url = "http://api.nbp.pl/api/";
        ApiConnection apiConnection = new ApiConnection(url);
        //JsonConverter jsonConverter = new JsonConverter();
        //StringBuilder sb = apiConnection.getRate("euro", "gbp", 5d);
        String json = apiConnection.jsonToString("gbp");
        String json2 = apiConnection.jsonToString("chf");
        Double data = apiConnection.getRate(json);
        Double data2 = apiConnection.getRate(json2);
        System.out.println("the result conversion is " + data);
        System.out.println("the result conversion is " + data2);
        Double amount = 5.5;
        Double result = (data/data2) * amount;
        System.out.println(amount + " the result conversion is " + result);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/gui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CurrencyConverter");
        stage.show();
    }
}