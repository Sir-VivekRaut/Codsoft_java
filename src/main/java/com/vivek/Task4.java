package com.vivek;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Task4 {

    private static final String API_KEY = "7e398a1a167f5447ed57fbd5";  // Replace with your actual API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 1st currency code :");
        String fromCurrency = sc.next();
        System.out.println("Enter 2nd currency code :");
        String toCurrency = sc.next();
        System.out.println("Enter amount :");
        double amount = sc.nextDouble();

        try {
            double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
            System.out.println(amount + " " + fromCurrency + " is equal to " + convertedAmount + " " + toCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception {
        String urlStr = API_URL + fromCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            double exchangeRate = jsonResponse.getJSONObject("conversion_rates").getDouble(toCurrency);

            return amount * exchangeRate;
        }
    }
}
