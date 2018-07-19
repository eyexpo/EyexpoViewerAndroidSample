package com.eyexpo.tour

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by Ashton Chen on 2018-07-18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val token = sharedPreference.getString("token", "")

        var intent: Intent?
        if (token.isNotEmpty()) {
            intent = Intent(baseContext, GalleryActivity::class.java)
        } else {
            intent = Intent(baseContext, AuthActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
