package com.example.advancedtest

import android.net.Uri
import android.provider.MediaStore

class Music(id:String, title:String?, artist:String?, albumID:String?, duration: Long?) {
    var id: String=""
    var title: String?
    var artist: String?
    var albumID: String?
    var duration: Long?
    init{
        this.id=id
        this.title=title
        this.artist=artist
        this.albumID=albumID
        this.duration=duration
    }

    fun getMusicUri(): Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }

    fun getAlbumUri():Uri{
        return Uri.parse("content://media/external/audio/albumart/" + albumID)
    }
}