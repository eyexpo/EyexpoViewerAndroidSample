package com.eyexpo.tour

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener
import com.google.vr.sdk.widgets.pano.VrPanoramaView


/**
 * Created by Ashton Chen on 2018-07-18.
 */

class GalleryViewHolder : RecyclerView.ViewHolder {
    private val context: Context
    private val panoramaView: VrPanoramaView
    private val titleView: TextView
    private val viewOptions = VrPanoramaView.Options()
    private var url: String? = null

    constructor(view: View, _context: Context) : super(view) {
        context = _context
        panoramaView = view.findViewById(R.id.panorama)
        titleView = view.findViewById(R.id.title)
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO
        panoramaView.setInfoButtonEnabled(false)
        panoramaView.setFullscreenButtonEnabled(false)
        panoramaView.setStereoModeButtonEnabled(false)
        panoramaView.setEventListener(object : VrPanoramaEventListener() {
            override fun onClick() {
                val intent = Intent(context, ViewerActivity::class.java)
                intent.putExtra("URL", url)
                context.startActivity(intent)
            }
        })
    }

    var data: TourModel? = null
        set(value) {
            titleView.text = value!!.title
            val imageUri = Uri.parse(value!!.thumbnailURI)
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            panoramaView.loadImageFromBitmap(bitmap, viewOptions)
            url = value.url
        }
}