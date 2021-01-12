package com.example.advancedtest

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_asynctask.*
import java.lang.Exception
import java.net.URL

class AsynctaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask)

        imgDownBtn.setOnClickListener {
            val asyncTask = @SuppressLint("StaticFieldLeak")
            object: AsyncTask<String, Void, Bitmap?>(){
                override fun doInBackground(vararg params: String?): Bitmap? {
                    val urlString = params[0]!!
                    try{
                        val url = URL(urlString)
                        val stream = url.openStream()
                        return BitmapFactory.decodeStream((stream))
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                        return null
                    }
                }

                override fun onProgressUpdate(vararg values: Void?) {
                    super.onProgressUpdate(*values)
                }

                override fun onPostExecute(result: Bitmap?) {
                    super.onPostExecute(result)

                    if(result != null){
                        downImg.setImageBitmap(result)
                    }
                    else{
                        Toast.makeText(this@AsynctaskActivity,"다운로드 오류. URL을 다시 확인해주세요.", Toast.LENGTH_LONG).show()
                    }
                }
            }
            asyncTask?.execute(imgUrlText.text.toString())
        }


    }
}