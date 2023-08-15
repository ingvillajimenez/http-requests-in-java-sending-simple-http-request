package com.loonycorn;

import java.io.BufferedReader; // class BufferedReader
import java.io.IOException; // class IOException
import java.io.InputStreamReader; // class InputStreamReader
import java.io.OutputStream; // abstract class OutputStream
import java.net.URL; // final class URL
import java.net.HttpURLConnection; // abstract class HttpURLConnection
import java.net.MalformedURLException; // class MalformedURLException
import org.json.JSONObject; // class JSONObject

public class SimpleHttpURLConnection {

    public static void main(String[] args) {

        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://reqres.in/api/users");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setDoOutput(true);

            String postData = "{'email':'alice@loonycorn.com'," +
                    "'firstName':'Alice','lastName':'Alison'}";

            try (OutputStream outputStream = conn.getOutputStream()) {

                byte[] postBytes = postData.getBytes("utf-8");
                outputStream.write(postBytes, 0, postBytes.length);
            }

            System.out.println("Response code: " + conn.getResponseCode());
            //Response code: 201
            System.out.println("Response message: " + conn.getResponseMessage());
            //Response message: Created

            try (BufferedReader read = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {

                StringBuilder responseText = new StringBuilder();

                while ((text = read.readLine()) != null) {
                    responseText.append(text.trim());
                }

                if (conn.getHeaderField("Content-Type").contains("json")) {
                    JSONObject jsonObject = new JSONObject(responseText.toString());
                    System.out.println("JSON:\n" + jsonObject.toString(4));
                    //JSON:
                    //{
                    //    "createdAt": "2023-08-15T18:13:20.881Z",
                    //    "id": "277",
                    //    "{'email':'alice@loonycorn.com','firstName':'Alice','lastName':'Alison'}": ""
                    //}
                } else {
                    System.out.println(responseText.toString());
                }
            }

            conn.disconnect();

        } catch (MalformedURLException murlx) {
            murlx.printStackTrace();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}

// The HttpURLConnection class
// https://doc.oracle.com/en/java/javase/15/docs/api/java.base/java/net/HttpURLConnection.html