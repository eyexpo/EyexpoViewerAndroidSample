package com.eyexpo.tour

/**
 * Created by Ashton Chen on 2018-07-12.
 */

class TourModel(private val mFileName: String, private val mTitle: String?,
                private val mURL: String, private val mThumbnailURL: String) {

    val fileName: String
        get() = mFileName
    val title: String?
        get() = mTitle
    val url: String
        get() = mURL
    val thumbnailURL: String
        get() = mThumbnailURL

    private var mThumbnailURI: String? = null
    var thumbnailURI: String?
        get() = mThumbnailURI
        set(value) {
            mThumbnailURI = value
        }
}