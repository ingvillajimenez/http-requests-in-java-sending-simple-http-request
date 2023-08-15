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

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(10000);
//            conn.setReadTimeout(10000);
//            conn.setReadTimeout(2000);

            int statusCode = conn.getResponseCode();
            //java.net.SocketTimeoutException: Read timed out
            //	at java.base/sun.nio.ch.NioSocketImpl.timedRead(NioSocketImpl.java:284)
            //	at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:310)
            //	at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:351)
            //	at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:802)
            //	at java.base/java.net.Socket$SocketInputStream.read(Socket.java:937)
            //	at java.base/sun.security.ssl.SSLSocketInputRecord.read(SSLSocketInputRecord.java:450)
            //	at java.base/sun.security.ssl.SSLSocketInputRecord.bytesInCompletePacket(SSLSocketInputRecord.java:68)
            //	at java.base/sun.security.ssl.SSLSocketImpl.readApplicationRecord(SSLSocketImpl.java:1409)
            //	at java.base/sun.security.ssl.SSLSocketImpl$AppInputStream.read(SSLSocketImpl.java:1022)
            //	at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:245)
            //	at java.base/java.io.BufferedInputStream.read1(BufferedInputStream.java:285)
            //	at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:344)
            //	at java.base/sun.net.www.http.HttpClient.parseHTTPHeader(HttpClient.java:746)
            //	at java.base/sun.net.www.http.HttpClient.parseHTTP(HttpClient.java:689)
            //	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1610)
            //	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1515)
            //	at java.base/java.net.HttpURLConnection.getResponseCode(HttpURLConnection.java:527)
            //	at java.base/sun.net.www.protocol.https.HttpsURLConnectionImpl.getResponseCode(HttpsURLConnectionImpl.java:308)
            //	at com.loonycorn.SimpleHttpURLConnection.main(SimpleHttpURLConnection.java:29)

            System.out.println("The status is: " + statusCode);
            //The status is: 200
            long responseSize = conn.getContentLengthLong();
            System.out.println("Size: " + responseSize);
            //Size: 996
            System.out.println("Headers: " + conn.getHeaderFields().toString());
            //Headers: {null=[HTTP/1.1 200 OK], CF-RAY=[7f75101f1ab7e53e-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 23:02:08 GMT], Via=[1.1 vegur], CF-Cache-Status=[DYNAMIC], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=blAGbFnzfDlV%2F%2B3Ier0DSQKnmGckxdU2n6s9pRdOlI4MCpLcV8zVNTfqHamQdgXivwdekPcangerGHa40LhakMcKzhSc89q9D75JiRrVgdTdApnddUKp2%2BI6vg%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[996], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}

            if (responseSize > 0) {

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
                        System.out.println("JSON:\n" + jsonObj.toString(4));
                        //JSON:
                        //{
                        //    "per_page": 6,
                        //    "total": 12,
                        //    "data": [
                        //        {
                        //            "last_name": "Bluth",
                        //            "id": 1,
                        //            "avatar": "https://reqres.in/img/faces/1-image.jpg",
                        //            "first_name": "George",
                        //            "email": "george.bluth@reqres.in"
                        //        },
                        //        {
                        //            "last_name": "Weaver",
                        //            "id": 2,
                        //            "avatar": "https://reqres.in/img/faces/2-image.jpg",
                        //            "first_name": "Janet",
                        //            "email": "janet.weaver@reqres.in"
                        //        },
                        //        {
                        //            "last_name": "Wong",
                        //            "id": 3,
                        //            "avatar": "https://reqres.in/img/faces/3-image.jpg",
                        //            "first_name": "Emma",
                        //            "email": "emma.wong@reqres.in"
                        //        },
                        //        {
                        //            "last_name": "Holt",
                        //            "id": 4,
                        //            "avatar": "https://reqres.in/img/faces/4-image.jpg",
                        //            "first_name": "Eve",
                        //            "email": "eve.holt@reqres.in"
                        //        },
                        //        {
                        //            "last_name": "Morris",
                        //            "id": 5,
                        //            "avatar": "https://reqres.in/img/faces/5-image.jpg",
                        //            "first_name": "Charles",
                        //            "email": "charles.morris@reqres.in"
                        //        },
                        //        {
                        //            "last_name": "Ramos",
                        //            "id": 6,
                        //            "avatar": "https://reqres.in/img/faces/6-image.jpg",
                        //            "first_name": "Tracey",
                        //            "email": "tracey.ramos@reqres.in"
                        //        }
                        //    ],
                        //    "page": 1,
                        //    "total_pages": 2,
                        //    "support": {
                        //        "text": "To keep ReqRes free, contributions towards server costs are appreciated!",
                        //        "url": "https://reqres.in/#support-heading"
                        //    }
                        //}
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