package connectionApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiConnection {
    private final String url;

    public ApiConnection(String url){
        this.url = url;
    }

    public String getSymbolWithSignification(String from) throws IOException {
        URL objUrl = new URL(url  + "/tables/a/?format=json");
        HttpURLConnection http = (HttpURLConnection) objUrl.openConnection();
        if(http.getResponseCode() == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String jsonLine;
            String json = "";
            while((jsonLine = in.readLine()) != null){
                json = json.concat(jsonLine);
            }
            in.close();
            http.disconnect();
            return json;
        }
        else {
            http.disconnect();
            return "Response code: " + http.getResponseCode() + "-" + http.getResponseMessage();
        }
    }

    public String jsonToString(String from) throws IOException{
        URL objUrl = new URL(url + "exchangerates/rates/a/" + from + "/?format=json");
        HttpURLConnection http = (HttpURLConnection) objUrl.openConnection();
        if(http.getResponseCode() == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String jsonLine;
            String json = "";
            while((jsonLine = in.readLine()) != null){
                json = json.concat(jsonLine);
            }
            in.close();
            http.disconnect();
            return json;
        }
        else {
            http.disconnect();
            return "Response code: " + http.getResponseCode() + "-" + http.getResponseMessage();
        }
    }
    public Double getRate(String json) throws JSONException {
        if(json.startsWith("Response code: ")){
            throw new RuntimeException(json);
        }
        else{
            JSONObject jsonO = new JSONObject(json);
            JSONArray rates = jsonO.getJSONArray("rates");
            Double mid = rates.getJSONObject(0).getDouble("mid");
            return mid;
        }
    }

}
