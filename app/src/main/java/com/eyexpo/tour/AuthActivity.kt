package com.eyexpo.tour

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

/**
 * Created by Ashton Chen on 2018-07-18.
 */

class AuthActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val button = findViewById<View>(R.id.btn_login)
        button.setOnClickListener {
            val identifierView = findViewById<View>(R.id.input_identifier) as EditText
            val passwordView = findViewById<View>(R.id.input_password) as EditText
            var identifier = identifierView.text.toString()
            var password = passwordView.text.toString()

            try {
                val parameters = HashMap<String, String>()
                parameters.put("identifierType", "email")
                parameters.put("identifierValue", identifier)
                parameters.put("password", password)

                val queue = Volley.newRequestQueue(this)
                val request = JsonObjectRequest("https://stage.eyexpo.com/api/v1/users/login",
                        JSONObject(parameters),
                        Response.Listener<JSONObject> { response ->
                            val token = response.getString("token")
                            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
                            sharedPreference.edit().putString("token", token).commit()

                            var intent = Intent(baseContext, GalleryActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        Response.ErrorListener {
                            print("error")
                        })
                queue.add(request)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}