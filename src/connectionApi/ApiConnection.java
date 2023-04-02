package connectionApi;

import models.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    private final String url;

    public ApiConnection(String url) {
        this.url = url;
    }

    public String jsonToString(String from) throws IOException {
        URL objUrl = new URL(url + from + "/?format=json");
        HttpURLConnection http = (HttpURLConnection) objUrl.openConnection();
        if (http.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String jsonLine;
            String json = "";
            while ((jsonLine = in.readLine()) != null) {
                json = json.concat(jsonLine);
            }
            in.close();
            http.disconnect();
            return json;
        } else {
            http.disconnect();
            return "Response code: " + http.getResponseCode() + "-" + http.getResponseMessage();
        }
    }

    public Currency getSingleRate(String json) {
        if (json.startsWith("Response code: ")) {
            throw new RuntimeException(json);
        } else {
            JSONObject jsonO = new JSONObject(json);
            JSONArray rates = jsonO.getJSONArray("rates");
            Double mid = rates.getJSONObject(0).getDouble("mid");
            String symbol = jsonO.getString("code");
            String signification = jsonO.getString("currency");
            Currency coin = new Currency(symbol, signification, mid);
            return coin;
        }
    }

    public String getSymbolWithSignification() throws IOException {
        URL objUrl = new URL(url + "/?format=json");
        HttpURLConnection http = (HttpURLConnection) objUrl.openConnection();
        if (http.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String jsonLine;
            String json = "";
            in.skip(1);
            while ((jsonLine = in.readLine()) != null) {
                json = json.concat(jsonLine);
            }
            in.close();
            http.disconnect();
            String st = "";
            JSONObject js = new JSONObject(json);
            JSONArray rt = js.getJSONArray("rates");
            for (int i = 0; i < rt.length(); i++) {
                String symbol = rt.getJSONObject(i).getString("code");
                String sig = rt.getJSONObject(i).getString("currency");
                st += sig + " " + symbol + "\n";
            }
            return st;
        } else {
            http.disconnect();
            return "Response code: " + http.getResponseCode() + "-" + http.getResponseMessage();
        }
    }
}
