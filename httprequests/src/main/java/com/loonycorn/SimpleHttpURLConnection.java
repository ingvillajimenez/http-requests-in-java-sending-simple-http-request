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

            URL url = new URL("https://en.wikipedia.org/wiki/Loonycorn");
//            URL url = new URL("https://en.wikipedia.org/wiki/Olivier_Martinez");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            System.out.println("The status is: " + statusCode);
            //The status is: 404

            System.out.println("Returned headers:\n" + conn.getHeaderFields());
            //Returned headers:
            //{date=[Mon, 14 Aug 2023 23:40:21 GMT], null=[HTTP/1.1 404 Not Found], content-length=[39219], server=[mw2269.codfw.wmnet], vary=[Accept-Encoding,Cookie,Authorization], x-client-ip=[187.224.61.200], strict-transport-security=[max-age=106384710; includeSubDomains; preload], set-cookie=[NetworkProbeLimit=0.001;Path=/;Secure;Max-Age=3600, GeoIP=MX:JAL:Guadalajara:20.64:-103.35:v4; Path=/; secure; Domain=.wikipedia.org, WMF-Last-Access-Global=14-Aug-2023;Path=/;Domain=.wikipedia.org;HttpOnly;secure;Expires=Fri, 15 Sep 2023 12:00:00 GMT, WMF-Last-Access=14-Aug-2023;Path=/;HttpOnly;secure;Expires=Fri, 15 Sep 2023 12:00:00 GMT], nel=[{ "report_to": "wm_nel", "max_age": 604800, "failure_fraction": 0.05, "success_fraction": 0.0}], x-content-type-options=[nosniff], x-cache-status=[hit-front], content-type=[text/html; charset=UTF-8], report-to=[{ "group": "wm_nel", "max_age": 604800, "endpoints": [{ "url": "https://intake-logging.wikimedia.org/v1/events?stream=w3c.reportingapi.network_error&schema_uri=/w3c/reportingapi/network_error/1.0.0" }] }], server-timing=[cache;desc="hit-front", host;desc="cp2033"], x-cache=[cp2035 miss, cp2033 hit/1], cache-control=[private, s-maxage=0, max-age=0, must-revalidate], age=[529], content-language=[en]}
            System.out.println("Content size: " + conn.getHeaderField("Content-Length"));
            //Content size: 39219
            System.out.println("Content type: " + conn.getHeaderField("Content-Type"));
            //Content type: text/html; charset=UTF-8

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
                //The request failed : Not Found
            }

            conn.disconnect();

        } catch (MalformedURLException murlx) {
            murlx.printStackTrace();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}
