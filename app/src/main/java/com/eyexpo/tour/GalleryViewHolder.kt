package com.eyexpo.tour

import android.content.Context
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

class GalleryViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val panoramaView: VrPanoramaView = view.findViewById(R.id.panorama)
    private val titleView: TextView = view.findViewById(R.id.title)

    var onClickPanoramaCallback: (Uri) -> Unit = {}
    var data: TourModel? = null
        set(value) {
            titleView.text = value!!.title
            val imageUri = Uri.parse(value!!.thumbnailURI)
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            val viewOptions = VrPanoramaView.Options()
            viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO
            panoramaView.loadImageFromBitmap(bitmap, viewOptions)
            panoramaView.setInfoButtonEnabled(false)
            panoramaView.setFullscreenButtonEnabled(false)
            panoramaView.setStereoModeButtonEnabled(false)
            panoramaView.setEventListener(object : VrPanoramaEventListener() {
                override fun onClick() {
                    onClickPanoramaCallback(Uri.parse(value.url))
                }
            })
        }

    fun setOnClickPanoramaListener(callback: (Uri) -> Unit) {
        onClickPanoramaCallback = callback
    }
}