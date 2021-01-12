package com.example.advancedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_timer.*
import kotlin.concurrent.thread

class TimerActivity : AppCompatActivity() {
    var total = 0
    var started = false
    val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d",total/60)
            val second = String.format("%02d",total%60)
            timerText.text = "$minute:$second"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        timerStartBtn.setOnClickListener {
            if(!started){
                started=true
                thread(start=true){
                    while(started){
                        Thread.sleep(1000)
                        if(started){
                            total+=1
                            handler?.sendEmptyMessage(0)
                        }
                    }
                }
            }
        }

        timerStopBtn.setOnClickListener {
            if(started){
                started = false
                total = 0
                timerText.text = "00:00"
            }
        }
    }
}