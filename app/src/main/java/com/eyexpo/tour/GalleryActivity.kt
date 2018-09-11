package com.eyexpo.tour

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Created by Ashton Chen on 2018-07-18.
 */

class GalleryActivity : AppCompatActivity() {

    private val mTourList = arrayListOf<String>()
    private var mAdapter: GalleryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        initLocalTourList()
    }

    private fun initLocalTourList() {
        for (i in 0..6) {
            mTourList.add("panorama/$i.jpg")
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.gallery_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = GalleryAdapter(this, mTourList)
        recyclerView.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        mAdapter?.onResume()
    }

    override fun onPause() {
        mAdapter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAdapter?.onDestroy()
        super.onDestroy()
    }
}
