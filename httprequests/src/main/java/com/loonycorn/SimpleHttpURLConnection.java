package com.loonycorn;

import java.io.BufferedReader; // class BufferedReader
import java.io.IOException; // class IOException
import java.io.InputStreamReader; // class InputStreamReader
import java.net.URL; // final class URL
import java.net.HttpURLConnection; // abstract class HttpURLConnection
import java.net.MalformedURLException; // class MalformedURLException

public class SimpleHttpURLConnection {

    public static void main(String[] args) {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://en.wikipedia.org/wiki/Olivier_Martinez");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status is: " + statusCode);
            //The status is: 200

            System.out.println("\nThe response body:");

            if (statusCode >= 200 && statusCode <= 299) {

                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((text = read.readLine()) != null) {
                    content.append(text);
                }

                read.close();
                System.out.println(content.toString());
            }
            else {
                System.out.println("The request failed : " + conn.getResponseMessage());
            }

            conn.disconnect();

        } catch (MalformedURLException murlx) {
            murlx.printStackTrace();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}
