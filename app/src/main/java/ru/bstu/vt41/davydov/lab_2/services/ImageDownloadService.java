package ru.bstu.vt41.davydov.lab_2.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by macbook on 06.04.2018.
 */

public class ImageDownloadService extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap image;
        try {
            InputStream inputStream = new URL(url).openStream();
            image = BitmapFactory.decodeStream(inputStream);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
