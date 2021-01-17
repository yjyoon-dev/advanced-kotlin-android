package com.example.advancedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_url_connect.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class UrlConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_connect)
        urlConnectBtn.setOnClickListener {
            thread(start=true){
                try{
                    var urlStr = urlText.text.toString()
                    if(!urlStr.startsWith("https")) urlStr = "https://${urlStr}"
                    var url = URL(urlStr)

                    val urlConnection = url.openConnection() as HttpURLConnection

                    urlConnection.requestMethod="GET"

                    if(urlConnection.responseCode == HttpURLConnection.HTTP_OK){
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)

                        val content = StringBuilder()
                        while(true){
                            val line = buffered.readLine() ?: break
                            content.append(line)
                        }

                        buffered.close()
                        urlConnection.disconnect()

                        runOnUiThread { websiteText.text = content.toString() }
                    }
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}