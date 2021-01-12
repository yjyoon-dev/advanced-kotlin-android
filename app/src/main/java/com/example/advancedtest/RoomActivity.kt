package com.example.advancedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {
    var helper: RoomHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        val adapter = RecyclerAdapter()
        helper = Room.databaseBuilder(this,RoomHelper::class.java, "room_memo")
                .allowMainThreadQueries()
                .build()
        adapter.helper = helper
        adapter.listData = (helper?.roomMemoDao()?.getAll() ?: mutableListOf()) as MutableList<RoomMemo>
        recyclerMemo.adapter = adapter
        recyclerMemo.layoutManager = LinearLayoutManager(this)
        saveMemoBtn.setOnClickListener {
            if(editMemo.text.toString().isNotEmpty()){
                val memo = RoomMemo(editMemo.text.toString(),System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper?.roomMemoDao()?.getAll() ?: mutableListOf())
                adapter.notifyDataSetChanged()
                editMemo.setText("")
            }
        }
    }
}