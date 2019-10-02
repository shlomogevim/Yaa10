package com.example.yaa10

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val ADAM = "-אדם-"
    val GOD = "-אלוהים-"

    private var round = 0
    var lineNum = 1
    lateinit var mainArrayDialog: MutableList<String>
    lateinit var godList: MutableList<String>
    lateinit var manList: MutableList<String>
    var start = true
    private var manMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()

        generalOperation()

       // operateMan(manList[round])

        goddy.setOnClickListener {
            if (!manMode) {
                generalOperation()
                manMode=true
            } else {
                Toast.makeText(this, "נסה שוב, זה התור של האדם לדבר", Toast.LENGTH_LONG).show()
            }

            /*operateGoddy(godList[round])
            round++*/
        }

        man.setOnClickListener {
            //operateMan(manList[round])
            if (manMode) {
                generalOperation()
                manMode=false
            } else {
                Toast.makeText(this, "נסה שוב, זה התור של האין סוף להגיב", Toast.LENGTH_LONG).show()

            }

            /* animateDp()
             animateText(newMessage)
             animatRec(newMessage)*/
        }
    }

    private fun generalOperation() {
        if (manMode) {
            //Its man position
            operateMan(manList[round])
            manMode = false
        } else {
            // lts God position
            operateGoddy(godList[round])
            round++
            manMode = true
        }
    }


    private fun operateGoddy(st: String) {
        lineNum = st.length - (st.replace("\n", "").length) + 1
        val st1 = st
        var yy = 0f
        val dy = 115f + ((lineNum - 1) * 3)
        if (start) yy = 0f

        animateView(goddySpeaking, 0f, 0f)
        animationFad(0,manSpeaking)
        animationFad(1,goddySpeaking)
        goddySpeaking.text = st1
        lineNum = 1
        start = false
    }

    private fun operateMan(st: String) {
        lineNum = st.length - (st.replace("\n", "").length) + 1
        val st1 = st
        var dy = 420 - 30 * (lineNum - 1)
        animateView(manSpeaking, 0f, 0f)
        animationFad(0,goddySpeaking)
        animationFad(1,manSpeaking)
        manSpeaking.text = st1
        lineNum = 1
        start = false
    }

    private fun animationFad(ind:Int,view:View) {
        if (ind==0) {
            ViewAnimator
                .animate(view)
                .alpha(1f, 0.2f)
                .duration(300)
                .start()
        }
        if (ind==1) {
            ViewAnimator
                .animate(view)
                .alpha(0.2f, 1f)
                .duration(300)
                .start()
        }
    }


    private fun animateView(view: View, dx: Float, dy: Float) {

        ViewAnimator
            .animate(view)
            .dp().translationY(dx)
            .dp().translationY(dy)
            .duration(1)
            .start()
    }


    private fun improveString(st: String) = st.substring(1, st.length - 1)


    private fun getData() {
          var text = applicationContext.assets.open("text121.txt").bufferedReader().use {
       // var text = applicationContext.assets.open("text121.txt").bufferedReader().use {
            it.readText()
        }
        text = text.replace("\r", "")
        godList = mutableListOf()
        manList = mutableListOf()
        var list1 = text.split(ADAM)
        for (element in list1) {
            if (element != "" && element.length > 15) {
                var list2 = element.split(GOD)
                val st1 = improveString(list2[1])
                val st2 = improveString(list2[0])
                godList.add(st1)
                manList.add(st2)
            }
        }

    }

    private fun orgenizeString(originalS: String): String {
        val arr: Array<String>
        var s1 = ""
        var localS = ""
        var countChar = 0
        if (originalS.length > 20) {
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
            localS = originalS
        }
        return localS
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

