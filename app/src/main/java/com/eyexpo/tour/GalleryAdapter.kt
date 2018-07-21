package com.eyexpo.tour

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by Ashton Chen on 2018-07-18.
 */

internal class GalleryAdapter :
        RecyclerView.Adapter<GalleryViewHolder> {
    private val context: AppCompatActivity
    private val galleryList: List<TourModel>
    private val viewHolderWidth: Int
    private val viewHolderHeight: Int

    constructor(context: AppCompatActivity, galleryList: List<TourModel>) : super() {
        this.context = context
        this.galleryList = galleryList

        val barHeight = this.context.findViewById<View>(R.id.title_bar).height
        val galleryHeight = this.context.findViewById<View>(R.id.gallery_container).height

        val displayMetrics = DisplayMetrics()
        this.context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        viewHolderWidth = displayMetrics.widthPixels
        viewHolderHeight = (galleryHeight - barHeight) / 2 - 10
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GalleryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.viewholder_tile, viewGroup, false)
        return GalleryViewHolder(view, context, viewHolderWidth, viewHolderHeight)
    }

    override fun onBindViewHolder(viewHolder: GalleryViewHolder, i: Int) {
        viewHolder.data = galleryList[i]
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }
}
