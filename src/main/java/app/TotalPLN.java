package app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class TotalPLN {
    private DBUtilities dbUtilities = new DBUtilities();
    TotalPLN() {
        total();
    }
    private void total() {
        try {
            Scanner input = new Scanner(System.in);
            if (input.next().equals("total")) {
                String inputCurrency = input.next();
                double total = 0;

                String sum = "select sum(sum) from items";
                ResultSet resultSet = dbUtilities.read(sum);
                while (resultSet.next()) {
                    total = resultSet.getDouble(1);
                }

                Object obj = new JSONParser().parse(jsonGetRequest());
                JSONObject jsonObj = (JSONObject) obj;

                Map rates = ((Map) jsonObj.get("rates"));
                Iterator<Map.Entry> itr = rates.entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry entry = itr.next();
                    String currency = (String) entry.getKey();
                    Number rate = (Number) entry.getValue();
                    double rateDoubleFormat = rate.doubleValue();
                    if (currency.equals(inputCurrency)) {
                        double amount = total * rateDoubleFormat;
                        double rounded = (double) Math.round(amount * 100) / 100;
                        System.out.println("Total amount spent is " + rounded + " " + currency);
                    }
                }
            } else {
                System.out.println("Incorrect input");
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        } finally {
            Main.display();
        }
    }

    private static String streamToString (InputStream inputStream) {
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
    }

    private static String jsonGetRequest() {
        String json = null;
        try {
            URL url = new URL("http://data.fixer.io/api/latest?access_key=0a9e628c22459b427cb641c5f6c7d259");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json;
    }
}
