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
        var bitmap: Bitmap? = null
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        callback(result)
    }
}