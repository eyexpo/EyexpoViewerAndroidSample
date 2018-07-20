package com.eyexpo.tour

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ashton Chen on 2018-07-18.
 */

internal class GalleryAdapter(private val context: Context, private var galleryList: List<TourModel>) :
        RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GalleryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.viewholder_tile, viewGroup, false)
        return GalleryViewHolder(view, context)
    }

    override fun onBindViewHolder(viewHolder: GalleryViewHolder, i: Int) {
        viewHolder.data = galleryList[i]
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }
}
