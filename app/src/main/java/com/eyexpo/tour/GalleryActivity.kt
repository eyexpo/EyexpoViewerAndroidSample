package com.eyexpo.tour

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI


/**
 * Created by Ashton Chen on 2018-07-18.
 */

class GalleryActivity : AppCompatActivity() {

    private val tourList = arrayListOf<TourModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val logoutButton = findViewById<TextView>(R.id.logout)
        logoutButton.setOnClickListener {
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
            sharedPreference.edit().remove("token").commit()

            var intent = Intent(baseContext, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }

        val callback: (value: JSONObject) -> Unit = {
            val jsonList = it.getJSONArray("publictours")
            var imageDownloadCount = 0
            for (i in 0..(jsonList.length() - 1)) { //jsonList.length()-1) {
                val jsonObject = jsonList.getJSONObject(i)
                val fileName = jsonObject.getString("vr_config")
                val url = jsonObject.getString("public_url")
                val thumbnailURL = jsonObject.getString("cover_img_thumbnail")
                val title = jsonObject.getString("name")

                val tour = TourModel(fileName, title, url, thumbnailURL)

                val onImageDownloadCallback: (bitmap: Bitmap?) -> Unit = {
                    if (it != null) {
                        val uri = writeToFile("gallery", tour.fileName, it)
                        tour.thumbnailURI = uri.toString()
                    }

                    tourList.add(tour)

                    imageDownloadCount++

                    if (imageDownloadCount == jsonList.length()) {
                        val recyclerView = findViewById<RecyclerView>(R.id.gallery_recycler_view)
                        recyclerView.setHasFixedSize(true)
                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.adapter = GalleryAdapter(this, tourList)
                    }
                }
                ImageDownloadTask(tour.thumbnailURL, onImageDownloadCallback).execute()
            }
        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val token = sharedPreference.getString("token", "")

        val request = object : JsonObjectRequest("https://eyexpo.com.cn/api/v1/users/publictours", null,
                Response.Listener { response ->
                    callback(response)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, "Cannot fetch tour list!", Toast.LENGTH_LONG).show()
                }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = token
                return params
            }
        }

        Volley.newRequestQueue(this).add(request)
    }

    private fun writeToFile(directoryName: String, fileName: String, bitmap: Bitmap): URI {

        val directory = File(this.filesDir, directoryName)
        if (!directory.exists()) {
            directory.mkdir()
        }

        val file = File(directory, fileName)

        if (!file.exists()) {
            try {
                file.createNewFile()
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
            } catch (e: FileNotFoundException) {
                Toast.makeText(applicationContext, "Cannot write to file!", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "Cannot write to file!", Toast.LENGTH_SHORT).show()
            } finally {

            }
        }

        return file.toURI()
    }
}
