package com.example.advancedtest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_content_resolver.*

class ContentResolverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    fun startProcess() {
        setContentView(R.layout.activity_content_resolver)
        val adapter = MusicRecyclerAdapter()
        adapter.musicList.addAll(getMusicList())

        musicRecyclerView.adapter = adapter
        musicRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED)
            requestPermission()
        else
            startProcess()
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this,permission,99)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 99){
            var check = true
            for(grant in grantResults){
                if(grant != PackageManager.PERMISSION_GRANTED){
                    check = false
                    break
                }
            }
            if(!check){
                Toast.makeText(this,"권한 요청을 모두 승인해야지만 앱을 실행할 수 있습니다",Toast.LENGTH_LONG)
                finish()
            }
            else startProcess()
        }
    }

    fun getMusicList(): List<Music>{
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )

        val cursor = contentResolver.query(listUrl, proj, null, null, null)

        val musicList = mutableListOf<Music>()

        while(cursor?.moveToNext()==true){
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumID = cursor.getString(3)
            val duration = cursor.getLong(4)

            val music = Music(id, title, artist, albumID, duration)

            musicList.add(music)
        }

        return musicList
    }
}