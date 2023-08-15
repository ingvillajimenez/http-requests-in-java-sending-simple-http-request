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

            URL url = new URL("https://reqres.in/api/users?page=2&delay=5");
//            URL url = new URL("https://reqres.in/api/users?page=2");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status is: " + statusCode);
            //The status is: 200

            System.out.println("Returned headers:\n" + conn.getHeaderFields());
            //Returned headers:
            //{null=[HTTP/1.1 200 OK], CF-RAY=[7f7322fbaec6e7b3-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 17:25:24 GMT], Via=[1.1 vegur], Accept-Ranges=[bytes], CF-Cache-Status=[HIT], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Cache-Control=[max-age=14400], Etag=[W/"406-ut0vzoCuidvyMf8arZpMpJ6ZRDw"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=vvzYxpG6JW18icq5rYMe7gNB5u5Ltqf7WEGrVo%2BCJaMZbs4Q%2F8uTMHTLW%2BOGLGim3ixXc9qXjnPdRKKS2pxh6a7gJS2kbzIc9U2aTerodyWcyKakmAyBm6l6aQ%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[1030], Age=[514], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}
            System.out.println("Content size from header: " + conn.getHeaderField("Content-Length"));
            //Content size from header: 1030
            System.out.println("Content size from method: " + conn.getContentLengthLong());
            //Content size from method: 1030
            System.out.println("Content type: " + conn.getHeaderField("Content-Type"));
            //Content type: application/json; charset=utf-8

//            System.out.println("Returned headers:\n" + conn.getHeaderFields());
//            //Returned headers:
//            //{null=[HTTP/1.1 200 OK], CF-RAY=[7f73164cdb1fe7c7-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 17:16:50 GMT], Via=[1.1 vegur], Accept-Ranges=[bytes], CF-Cache-Status=[MISS], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Cache-Control=[max-age=14400], Etag=[W/"406-ut0vzoCuidvyMf8arZpMpJ6ZRDw"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=sQi6MNBTaR5DzgKsVSS02lR9HZbOugQzLEyp86kPQUYreIRXXejXbr9QBFlQfGCdU%2FMk4oLW6qXFdST0tSbfl%2FUr5V5T83uBNVl5s%2FrYtjn1cwPFrUNW4gsSqw%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[1030], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}
//            System.out.println("Content size: " + conn.getHeaderField("Content-Length"));
//            //Content size: 1030
//            System.out.println("Content type: " + conn.getHeaderField("Content-Type"));
//            //Content type: application/json; charset=utf-8

            System.out.println("\nThe response body:");

            if (statusCode >= 200 && statusCode <= 299) {

                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((text = read.readLine()) != null) {
                    content.append(text);
                }

                read.close();

                String responseText = content.toString();

                if (conn.getHeaderField("Content-Type").contains("json")) {
                    JSONObject jsonObject = new JSONObject(responseText);
                    System.out.println("JSON:\n" + jsonObject.toString(4));
                    //JSON:
                    //{
                    //    "per_page": 6,
                    //    "total": 12,
                    //    "data": [
                    //        {
                    //            "last_name": "Lawson",
                    //            "id": 7,
                    //            "avatar": "https://reqres.in/img/faces/7-image.jpg",
                    //            "first_name": "Michael",
                    //            "email": "michael.lawson@reqres.in"
                    //        },
                    //        {
                    //            "last_name": "Ferguson",
                    //            "id": 8,
                    //            "avatar": "https://reqres.in/img/faces/8-image.jpg",
                    //            "first_name": "Lindsay",
                    //            "email": "lindsay.ferguson@reqres.in"
                    //        },
                    //        {
                    //            "last_name": "Funke",
                    //            "id": 9,
                    //            "avatar": "https://reqres.in/img/faces/9-image.jpg",
                    //            "first_name": "Tobias",
                    //            "email": "tobias.funke@reqres.in"
                    //        },
                    //        {
                    //            "last_name": "Fields",
                    //            "id": 10,
                    //            "avatar": "https://reqres.in/img/faces/10-image.jpg",
                    //            "first_name": "Byron",
                    //            "email": "byron.fields@reqres.in"
                    //        },
                    //        {
                    //            "last_name": "Edwards",
                    //            "id": 11,
                    //            "avatar": "https://reqres.in/img/faces/11-image.jpg",
                    //            "first_name": "George",
                    //            "email": "george.edwards@reqres.in"
                    //        },
                    //        {
                    //            "last_name": "Howell",
                    //            "id": 12,
                    //            "avatar": "https://reqres.in/img/faces/12-image.jpg",
                    //            "first_name": "Rachel",
                    //            "email": "rachel.howell@reqres.in"
                    //        }
                    //    ],
                    //    "page": 2,
                    //    "total_pages": 2,
                    //    "support": {
                    //        "text": "To keep ReqRes free, contributions towards server costs are appreciated!",
                    //        "url": "https://reqres.in/#support-heading"
                    //    }
                    //}
                }
                else {
                    System.out.println(responseText);
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