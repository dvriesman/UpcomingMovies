package ucml.drsv.upcommingmovieslist.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by denny on 25/12/17.
 */

public class DownloadTmdbImage {

    private static final String baseURL = "http://image.tmdb.org/t/p/w92";

    public static Bitmap getBitmap(String url) {
        Bitmap result = null;
        if (url != null) {
            try {
                result = downloadImage(baseURL + url);
            } catch (IOException e) {
                Log.e("donwload_image_error", e.getMessage());

            }
        }
        return result;
    }

    private static Bitmap downloadImage(String url) throws IOException {
        Bitmap result = null;
        InputStream is = getHttpConnection(url);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
        result = BitmapFactory.decodeStream(is, null, bmOptions);
        is.close();
        return result;
    }

    private static InputStream getHttpConnection(String urlString) throws IOException {
        InputStream is = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setRequestMethod("GET");
        httpConnection.connect();
        if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            is = httpConnection.getInputStream();
        }
        return is;
    }



}


