package com.example.advancedtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openSQL.setOnClickListener {
            val intent = Intent(this,SQLiteActivity::class.java)
            startActivity(intent)
        }
    }
}