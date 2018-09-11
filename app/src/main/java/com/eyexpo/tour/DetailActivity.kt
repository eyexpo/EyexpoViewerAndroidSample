package com.eyexpo.tour

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eyexpo.vr.PanoramaWidget


/**
 * Created by Ashton Chen on 2018-08-29.
 */

class DetailActivity : AppCompatActivity() {
    private var mPanoramaView: PanoramaWidget? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mPanoramaView = findViewById(R.id.panoWidget)
        mPanoramaView!!.setImageAssetURI(intent.data.path)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPanoramaView!!.setOnClickListener {
                val intent = Intent(baseContext, ViewerActivity::class.java)
                intent.putExtra("URL", "https://host.eyexpo.com.cn/tours/msc/index.html")
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mPanoramaView!!.onResume()
    }

    override fun onPause() {
        mPanoramaView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mPanoramaView!!.onDestroy()
        super.onDestroy()
    }
}