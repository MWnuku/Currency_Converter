import connectionApi.ApiConnection;
import connectionApi.JsonConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        //launch(args);
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/";
        ApiConnection apiConnection = new ApiConnection(url);
        JsonConverter jsonConverter = new JsonConverter();
        //StringBuilder sb = apiConnection.getRate("euro", "gbp", 5d);
        String sb = apiConnection.getRate("gbp");
        Double data = jsonConverter.getDataConversion(sb);
        System.out.println("the result conversion is " + data);
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