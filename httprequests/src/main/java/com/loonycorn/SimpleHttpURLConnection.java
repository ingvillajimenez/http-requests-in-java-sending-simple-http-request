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

            URL url = new URL("https://reqres.in/api/users?page=2");
//            URL url = new URL("https://reqres.in/api/users");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status is: " + statusCode);
            //The status is: 200
            //The status is: 200

            System.out.println("Returned headers:\n" + conn.getHeaderFields());
            //Returned headers:
            //{null=[HTTP/1.1 200 OK], CF-RAY=[7f72e7ae69434868-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 16:44:55 GMT], Via=[1.1 vegur], Accept-Ranges=[bytes], CF-Cache-Status=[HIT], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Cache-Control=[max-age=14400], Etag=[W/"406-ut0vzoCuidvyMf8arZpMpJ6ZRDw"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=CBuGfsv5%2FIFiboejRJ5TRXE1h5xVLRQUvfKGVL9cfjYxLAFS5kaD3kYdXqKj50beruaTn1oRgiJJH2xdYn8AGM4e2gRl%2FzVEwQ7LFB%2B7rlUAJvMs%2BLa%2F26xg%2Bg%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[1030], Age=[1358], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}
            //{null=[HTTP/1.1 200 OK], CF-RAY=[7f72b3f39d5ae92e-DFW], Server=[cloudflare], Access-Control-Allow-Origin=[*], Connection=[keep-alive], Date=[Tue, 15 Aug 2023 16:09:36 GMT], Via=[1.1 vegur], Accept-Ranges=[bytes], CF-Cache-Status=[HIT], NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Cache-Control=[max-age=14400], Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=mBXtB%2Bcp1TiIr97%2BWY7aB82b8tc0H4GKy9I5mhN9R8m6mq0I5jwSeN1HFo%2BEaiQyw4ZYVbzg96ulK74wH5wbvuBNLtzHB2dMOd%2Bk36cxR9Zb231sxB8DUev8sQ%3D%3D"}],"group":"cf-nel","max_age":604800}], Content-Length=[996], Age=[2437], X-Powered-By=[Express], Content-Type=[application/json; charset=utf-8]}
            System.out.println("Content size: " + conn.getHeaderField("Content-Length"));
            //Content size: 1030
            //Content size: 996
            System.out.println("Content type: " + conn.getHeaderField("Content-Type"));
            //Content type: application/json; charset=utf-8
            //Content type: application/json; charset=utf-8

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

//            if (statusCode >= 200 && statusCode <= 299) {
//
//                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                while ((text = read.readLine()) != null) {
//                    content.append(text);
//                }
//
//                read.close();
//                System.out.println(content.toString());
//                //{"page":1,"per_page":6,"total":12,"total_pages":2,"data":[{"id":1,"email":"george.bluth@reqres.in","first_name":"George","last_name":"Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},{"id":2,"email":"janet.weaver@reqres.in","first_name":"Janet","last_name":"Weaver","avatar":"https://reqres.in/img/faces/2-image.jpg"},{"id":3,"email":"emma.wong@reqres.in","first_name":"Emma","last_name":"Wong","avatar":"https://reqres.in/img/faces/3-image.jpg"},{"id":4,"email":"eve.holt@reqres.in","first_name":"Eve","last_name":"Holt","avatar":"https://reqres.in/img/faces/4-image.jpg"},{"id":5,"email":"charles.morris@reqres.in","first_name":"Charles","last_name":"Morris","avatar":"https://reqres.in/img/faces/5-image.jpg"},{"id":6,"email":"tracey.ramos@reqres.in","first_name":"Tracey","last_name":"Ramos","avatar":"https://reqres.in/img/faces/6-image.jpg"}],"support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}}
//            }
//            else {
//                System.out.println("The request failed : " + conn.getResponseMessage());
//            }

            conn.disconnect();

        } catch (MalformedURLException murlx) {
            murlx.printStackTrace();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}
