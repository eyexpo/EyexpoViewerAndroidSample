package com.eyexpo.tour

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by Ashton Chen on 2018-07-18.
 */


class ImageDownloadTask(private val url: String, private val callback: (Bitmap?) -> Unit) :
        AsyncTask<URL, Int, Bitmap>() {

    override fun doInBackground(vararg urls: URL): Bitmap? {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        callback(result)
    }
}