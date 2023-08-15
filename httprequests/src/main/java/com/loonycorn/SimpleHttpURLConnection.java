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

            URL url = new URL("https://reqres.in/api/users/277");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("DELETE");
//            conn.setRequestMethod("PUT");

//            conn.setDoOutput(true);
//
//            String postData = "{'email':'alice.alison@loonycorn.com'," +
//                    "'firstName':'Alice','lastName':'Alison'}";
//
//            try (OutputStream outputStream = conn.getOutputStream()) {
//
//                byte[] postBytes = postData.getBytes("utf-8");
//                outputStream.write(postBytes, 0, postBytes.length);
//            }

            System.out.println("Response code: " + conn.getResponseCode());
            //Response code: 204
            //Response code: 204
            //Response code: 200
            System.out.println("Response message: " + conn.getResponseMessage());
            //Response message: No Content
            //Response message: No Content
            //Response message: OK

            long responseSize = conn.getContentLengthLong();
            System.out.println("Size: " + responseSize);
            //Size: 0

            if (responseSize > 0) {

                try (BufferedReader read = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"))) {

                    StringBuilder responseText = new StringBuilder();

                    while ((text = read.readLine()) != null) {
                        responseText.append(text.trim());
                    }

                    if (conn.getHeaderField("Content-Type").contains("json")) {
                        JSONObject jsonObj = new JSONObject(responseText.toString());
                        System.out.println("JSON:\n" + jsonObj.toString(4));
                    } else {
                        System.out.println(responseText.toString());
                    }
                }
            }

//            try (BufferedReader read = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
//
//                StringBuilder responseText = new StringBuilder();
//
//                while ((text = read.readLine()) != null) {
//                    responseText.append(text.trim());
//                }
//                //Exception in thread "main" java.lang.NullPointerException
//                //at com.loonycorn.SimpleHttpURLConnection.main(SimpleHttpURLConnection.java:53)
//                if (conn.getHeaderField("Content-Type").contains("json")) {
//                    JSONObject jsonObject = new JSONObject(responseText.toString());
//                    System.out.println("JSON:\n" + jsonObject.toString(4));
//                    //JSON:
//                    //{"updatedAt": "2023-08-15T19:43:33.223Z"}
//                } else {
//                    System.out.println(responseText.toString());
//                }
//            }

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