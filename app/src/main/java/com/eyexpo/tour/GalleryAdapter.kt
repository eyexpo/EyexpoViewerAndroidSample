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

class GalleryAdapter :
        RecyclerView.Adapter<GalleryViewHolder> {
    private val context: AppCompatActivity
    private val galleryList: List<String>
    private var viewHolderList: MutableList<GalleryViewHolder>? = ArrayList()
    private val viewHolderWidth: Int
    private val viewHolderHeight: Int

    constructor(context: AppCompatActivity, galleryList: List<String>) : super() {
        this.context = context
        this.galleryList = galleryList

        val barHeight = this.context.findViewById<View>(R.id.title_bar).height

        val displayMetrics = DisplayMetrics()
        this.context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        viewHolderWidth = displayMetrics.widthPixels
        viewHolderHeight = (displayMetrics.heightPixels - barHeight) / 4 - 10
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GalleryViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.viewholder_tile, viewGroup, false)
        val viewHolder = GalleryViewHolder(view, context, viewHolderWidth, viewHolderHeight)
        viewHolderList!!.add(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: GalleryViewHolder, i: Int) {
        viewHolder.data = galleryList[i]
        viewHolder.onResume()
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    fun onResume() {
        for (viewHolder in viewHolderList!!) {
            viewHolder.onResume()
        }
    }

    fun onPause() {
        for (viewHolder in viewHolderList!!) {
            viewHolder.onPause()
        }
    }

    fun onDestroy() {
        for (viewHolder in viewHolderList!!) {
            viewHolder.onDestroy()
        }
    }
}
