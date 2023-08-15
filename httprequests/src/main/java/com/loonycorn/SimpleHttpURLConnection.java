package com.loonycorn;

import java.io.BufferedReader; // class BufferedReader
import java.io.IOException; // class IOException
import java.io.InputStreamReader; // class InputStreamReader
import java.net.URL; // final class URL
import java.net.HttpURLConnection; // abstract class HttpURLConnection
import java.net.MalformedURLException; // class MalformedURLException
import org.json.JSONObject; // class JSONObject

public class SimpleHttpURLConnection {

    public static void main(String[] args) {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://reqres.in/api/users?delay=5");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("HEAD");
//            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status is: " + statusCode);
            //The status is: 200
            //The status is: 200
            long responseSize = conn.getContentLengthLong();
            System.out.println("Size: " + responseSize);
            //Size: 996
            //Size: 996

            System.out.println("Headers: " + conn.getHeaderFields().toString());
            //Headers: {null=[HTTP/1.1 200 OK], CF-RAY=[7f74297f39904857-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 20:24:42 GMT], Via=[1.1 vegur], CF-Cache-Status=[DYNAMIC], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=Ufl15dfzvv5l5Gx0MBZvkjt%2FwQv%2Bvnyth0x%2BLMLuC6jYcE%2FOWcnc%2BmvveIRf3n8TL9Vw0YrOWrdkwhgIWgNlLmMuvFONaEUTB1T9djLX3%2F7Hc%2BoI76LM8VsP2A%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[996], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}
            //Headers: {null=[HTTP/1.1 200 OK], CF-RAY=[7f74228648ac35a2-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 20:19:56 GMT], Via=[1.1 vegur], CF-Cache-Status=[DYNAMIC], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=7YYPlqxbmKmAFK6S3zhxd2HSAZGQUHp4SnFBxHvKUvmKBTRI83MfFQTx4B2JH02mrOzmZprWjIgyE92jFY6xqoS3VnxRp3mh8jCIoQkQnKc0tH8rjuUw5%2BZnsw%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[996], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}

            if (responseSize > 0) {
//            if (!conn.getRequestMethod().equalsIgnoreCase("GET")
//                    && responseSize > 0) {

                System.out.println("\nThe response body:");

                if (statusCode >= 200 && statusCode < 299) {

                    read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while ((text = read.readLine()) != null) {
                        content.append(text);
                    }

                    read.close();

                    String responseText = content.toString();

                    if (conn.getHeaderField("Content-Type").contains("json")) {
                        JSONObject jsonObj = new JSONObject(responseText);
                        //Exception in thread "main" org.json.JSONException: A JSONObject text must begin with '{' at 0 [character 1 line 1]
                        //	at org.json.JSONTokener.syntaxError(JSONTokener.java:507)
                        //	at org.json.JSONObject.<init>(JSONObject.java:222)
                        //	at org.json.JSONObject.<init>(JSONObject.java:406)
                        //	at com.loonycorn.SimpleHttpURLConnection.main(SimpleHttpURLConnection.java:57)
                        System.out.println("JSON:\n" + jsonObj.toString(4));
                    } else {
                        System.out.println(responseText);
                    }
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

// 204 belongs to the family pf status codes (Success)