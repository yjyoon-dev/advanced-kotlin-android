package com.example.advancedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_s_q_lite.*

class SQLiteActivity : AppCompatActivity() {
    val helper = SQLiteHelper(this,"memo",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_q_lite)
        val adapter = RecyclerAdapter()
        adapter.helper = helper
        adapter.listData.addAll(helper.selectMemo())
        recyclerMemo.adapter = adapter
        recyclerMemo.layoutManager = LinearLayoutManager(this)
        saveMemoBtn.setOnClickListener {
            if(editMemo.text.toString().isNotEmpty()){
                val memo = Memo(null,editMemo.text.toString(),System.currentTimeMillis())
                helper.insertMemo(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
                editMemo.setText("")
            }
        }
    }
}