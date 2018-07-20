package com.eyexpo.tour

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
            val identifier = identifierView.text.toString()
            val password = passwordView.text.toString()

            val parameters = HashMap<String, String>()
            parameters.put("identifierType", "email")
            parameters.put("identifierValue", identifier)
            parameters.put("password", password)
            val jsonObject = JSONObject(parameters)

            val request = JsonObjectRequest("https://eyexpo.com.cn/api/v1/users/login",
                    jsonObject,
                    Response.Listener<JSONObject> { response ->
                        val token = response.getString("token")
                        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
                        sharedPreference.edit().putString("token", token).commit()

                        var intent = Intent(baseContext, GalleryActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    Response.ErrorListener {
                        Toast.makeText(applicationContext, "Server error!", Toast.LENGTH_LONG).show()
                    })
            Volley.newRequestQueue(this).add(request)
        }
    }
}