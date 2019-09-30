package com.example.yaa10

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var newMessage = ""
    var round = 1
    var counter = 0
    lateinit var mainArrayDialog: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        /*rect1.scaleX = 0.55f + 0.1f
        rect1.scaleY = 0.18f + 0.1f*/

        manSpeaking.text = mainArrayDialog[0]

        goddy.setOnClickListener {
            goddySpeaking.text = mainArrayDialog[round * 2 - 1]

            round++

        }

        man.setOnClickListener {
            manSpeaking.text = mainArrayDialog[round * 2 - 2]

            /* animateDp()
             animateText(newMessage)
             animatRec(newMessage)*/
        }
    }

    private fun getData() {
        val text = applicationContext.assets.open("text1.txt").bufferedReader().use {
            it.readText()
        }
        mainArrayDialog = text.split("*").toMutableList()
        for (i in 0 until mainArrayDialog.size) {
            val st = mainArrayDialog[i]
            if ((i % 2) == 0) {
                manSpeaking(st)
            } else {
                godSpeaking(st)
            }
        }
    }

    private fun godSpeaking(st: String) {
        Log.d("clima", " god ==> $st")


    }

    private fun manSpeaking(st: String) {
        Log.d("clima", " man ==> $st")

    }


    private fun animateDp() {

        ViewAnimator
            .animate(goddy)
            .dp().translationY(0f, 80f, 0f)
            .duration(1000)
            .repeatCount(5)
            .onStop {
                val mes = "זהו גמרתי לקפוץ, עכשיו קצת ננוח"
                animateText(mes)
                animatRec(mes)

                //  speakingTextview1.text ="זהו גמרתי לקפוץ, עכשיו קצת ננוח"

            }
            .start()

        /*  ViewAnimator
              .animate(happySmilly, sadSmilly)
              .dp().translationY(0f, 500f)
              .duration(3000)
              .accelerate()
              .thenAnimate(happySmilly, sadSmilly)
              .dp().translationY(500f, 0f)
              .rotation(720f * counter)
              .duration(3000)
              .decelerate()
              *//*.onStop {
                happySmilly.visibility= View.GONE
                happySmilly.animate().rotation(-720f)
                sadSmilly.animate().rotation(-720f)
                happySmilly.visibility= View.VISIBLE
            }*//*
            .start()*/
    }

    private fun animateText(message: String) {

        ViewAnimator
            .animate(goddySpeaking)
            .onStart {
                goddySpeaking.text = message
            }
            .textColor(Color.BLACK, Color.GREEN)
            .backgroundColor(Color.WHITE, Color.BLACK)
            .duration(3000)
            .start()
    }

    private fun animatRec(message: String) {
        var scalX = 1f
        var scalY = 1f

        when (message.length) {
            9 -> {
                scalX = 1.65f
                scalY = 1.28f
            }
            31 -> {
                scalX = 1.90f
                scalY = 0.28f
            }

        }



        ViewAnimator
            .animate(rect1)
            .scaleX(scalX)
            .scaleY(scalY)
            .start()

    }
}
