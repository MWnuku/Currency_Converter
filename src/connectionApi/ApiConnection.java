package connectionApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;

public class ApiConnection {
    private final String url;

    public ApiConnection(String url){
        this.url = url;
    }
//    public StringBuilder getRate(String from, String to, Double amount) throws  IOException {
//        URL objUrl = new URL(url + "/" + from + "/?format=json");
//        HttpURLConnection http = (HttpURLConnection) objUrl.openConnection();
//        StringBuilder sb = getStringbuilder(http);
//        return sb;
//    }
    public String jsonToString(String from) throws IOException{
        URL objUrl = new URL(url  + from + "/?format=json");
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

}
