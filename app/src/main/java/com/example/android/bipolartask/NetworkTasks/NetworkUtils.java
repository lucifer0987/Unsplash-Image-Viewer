package com.example.android.bipolartask.NetworkTasks;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    public static String getJsonResponse(String urlc) throws IOException {

        URL url = createURL(urlc);
        String jsonString = makehttprequest(url);

        return jsonString;
    }

    private static URL createURL(String urlc) {
        URL url = null;
        try {
            url = new URL(urlc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v("this", "Built URL");
        return url;
    }

    private static String makehttprequest(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        try {
            InputStream in = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            httpsURLConnection.disconnect();
        }
    }

}
