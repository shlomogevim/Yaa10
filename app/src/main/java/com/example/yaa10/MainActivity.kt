package com.example.yaa10

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Constraints
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var newMessage = ""
    var round = 1
    var counter = 0
    var lineNum = 1
    lateinit var mainArrayDialog: MutableList<String>
    var start=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        /*rect1.scaleX = 0.55f + 0.1f
        rect1.scaleY = 0.18f + 0.1f*/

        // manSpeaking.text = orgenizeString(mainArrayDialog[0])
        operateMan(mainArrayDialog[0])

        goddy.setOnClickListener {
            operateGoddy(mainArrayDialog[round * 2 - 1])

            round++

        }

        man.setOnClickListener {


            operateMan(mainArrayDialog[round * 2 - 2])

            /* animateDp()
             animateText(newMessage)
             animatRec(newMessage)*/
        }
    }



    private fun operateGoddy(st:String){
        val st1=orgenizeString(st)
        var yy=0f
        val dy=115f+((lineNum-1)*3)
        if (start) yy=0f

        animateView(goddySpeaking,0f,dy)

        /*when (lineNum){
            1->animateView(goddySpeaking,0f,90f+yy)
            2->animateView(goddySpeaking,0f,120f+yy)
            3->animateView(goddySpeaking,0f,150f+yy)
            4->animateView(goddySpeaking,0f,180f+yy)
            5->animateView(goddySpeaking,0f,210f+yy)

        }*/
        goddySpeaking.text = st1
        lineNum=1
        start=false
    }






     private fun operateMan(st:String){
         val st1=orgenizeString(st)
         var yy=0f
         if (start) yy=30f
                    yy=0f
         var dy=420-30*(lineNum-1)
         animateView(manSpeaking,0f,dy+yy)
        /* when (lineNum){
             1->animateView(manSpeaking,0f,390-30*(lineNum-1)f+yy)
             2->animateView(manSpeaking,0f,360f+yy)
             3->animateView(manSpeaking,0f,330f+yy)
             4->animateView(manSpeaking,0f,300f+yy)
             5->animateView(manSpeaking,0f,270f+yy)
             6->animateView(manSpeaking,0f,240f+yy)

         }*/
         manSpeaking.text = st1
         lineNum=1
         start=false
     }


    private fun animateView(view:View,dx:Float,dy:Float) {

        ViewAnimator
            .animate(view)
            .dp().translationY(dx)
            .dp().translationY(dy)

            .duration(10)

            .start()
    }

    private fun orgenizeString(originalS: String): String {
        val arr: Array<String>
        var s1 = ""
        var localS = ""
        var originalS1=originalS.replace("אדם:","")
         originalS1=originalS1.replace("אלוהים","")
        var countChar = 0
        if (originalS1.length > 20) {
            arr = originalS.split(" ").toTypedArray()
            for (element in arr) {
                countChar += element.length
                if (countChar <= 20) {
                    if (s1 == "") {
                        s1 = element
                    } else {
                        s1 = "$s1 $element"
                        countChar++
                    }
                } else {
                    if (localS == "") {
                        localS = s1
                    } else {
                        localS = localS + "\n" + s1

                    }
                    s1 = element
                    countChar = element.length
                    lineNum++
                }
            }
            localS = localS + "\n" + s1
            return localS
        } else {
            localS = originalS1
        }
        return localS
    }


    private fun getData() {
        //val text = applicationContext.assets.open("text1.txt").bufferedReader().use {
        val text = applicationContext.assets.open("text3.txt").bufferedReader().use {
            it.readText()
        }
        var arr= text.split("\n").toMutableList()

        mainArrayDialog= mutableListOf()

        for (i in 0 until arr.size){
            val st=arr[i]
            if (st!="אדם" && st!="אלוהים" && st!="-" && st!=""){
                mainArrayDialog.add(st)
            }
        }

    }

    private fun stamGodSpeaking(st: String) {
        Log.d("clima", " god ==> $st")


    }

    private fun stamManSpeaking(st: String) {
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
