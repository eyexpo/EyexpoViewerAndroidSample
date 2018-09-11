package com.eyexpo.tour

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.eyexpo.vr.PanoramaWidget


/**
 * Created by Ashton Chen on 2018-07-18.
 */

class GalleryViewHolder : RecyclerView.ViewHolder {
    private val mContext: Context
    private val mPanoramaWidget: PanoramaWidget

    @SuppressLint("ClickableViewAccessibility")
    constructor(view: View, _context: Context, width: Int, height: Int) : super(view) {
        mContext = _context
        val cardView = view.findViewById(R.id.card_view) as CardView
        mPanoramaWidget = view.findViewById(R.id.panoWidget)
        mPanoramaWidget.width = height / 2
        mPanoramaWidget.height = height / 2

        mPanoramaWidget.setOnClickListener {
            onClick()
        }

        cardView.setOnClickListener {
            onClick()
        }
    }

    var data: String? = null
        set(value) {
            field = value
            val inputStream = mContext.assets.open(value, AssetManager.ACCESS_BUFFER)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            mPanoramaWidget.setImageBitmap(bitmap)
        }

    private fun onClick() {
        val intent = Intent(mContext, DetailActivity::class.java)
        intent.data = Uri.parse(data)
        startActivity(mContext, intent, null)
    }

    fun onResume() {
        mPanoramaWidget?.onResume()
    }

    fun onPause() {
        mPanoramaWidget?.onPause()
    }

    fun onDestroy() {
        mPanoramaWidget?.onDestroy()
    }
}