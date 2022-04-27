package com.underground.holamundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var textToSpeech : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this,this)
        findViewById<Button>(R.id.btn_play).setOnClickListener{speak()}
    }

    private fun speak() {
        var msg = findViewById<TextView>(R.id.et_message).text.toString()

        if (msg.isEmpty()){
            msg = "Es en serio? escribi algo de una vez pelotudo."
        }
        textToSpeech!!.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tv_showText).text = getString(R.string.textToSpeechActive)
            textToSpeech!!.language = Locale("ES")
        }else{
            findViewById<TextView>(R.id.tv_showText).text = getString(R.string.textToSpecchDisabled)
        }
    }


    override fun onDestroy() {
        if(textToSpeech != null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }

}
