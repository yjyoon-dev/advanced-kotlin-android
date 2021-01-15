package com.example.advancedtest

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.music_recycler.view.*
import java.text.SimpleDateFormat

class MusicRecyclerAdapter : RecyclerView.Adapter<MusicRecyclerAdapter.Holder>() {
    var musicList = mutableListOf<Music>()
    var mediaPlayer: MediaPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.music_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList.get(position)
        holder.setMusic(music)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var musicUri: Uri? = null
        init{
            itemView.setOnClickListener{
                if(mediaPlayer != null){
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }
        fun setMusic(music:Music){
            itemView.albumArt.setImageURI(music.getAlbumUri())
            itemView.musicArtistText.text = music.artist
            itemView.musicTitleText.text = music.title

            val duration = SimpleDateFormat("mm:ss").format(music.duration)
            itemView.musicDurText.text = duration
            this.musicUri = music.getMusicUri()
        }
    }
}

