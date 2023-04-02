import connectionApi.ApiConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
       //launch(args);
        String urlRate = "http://api.nbp.pl/api/exchangerates/rates/a/";
        //String urlSymbols = "https://api.nbp.pl/api/exchangerates/tables/a";
        ApiConnection apiConnection = new ApiConnection(urlRate);
        //ApiConnection apiConnectionS = new ApiConnection(urlSymbols);
        String json = apiConnection.jsonToString("gbp");
        String json2 = apiConnection.jsonToString("chf");
        System.out.println("The " + apiConnection.getSingleRate(json).getSignification() + "(" + apiConnection.getSingleRate(json).getSymbol() + ") rate is " + apiConnection.getSingleRate(json).getRate().toString());
        System.out.println("The " + apiConnection.getSingleRate(json2).getSignification() + "(" + apiConnection.getSingleRate(json2).getSymbol() + ") rate is " + apiConnection.getSingleRate(json2).getRate().toString());
        System.out.println("The result conversion is: " + (apiConnection.getSingleRate(json).getRate() / apiConnection.getSingleRate(json2).getRate()) * 5.5);
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