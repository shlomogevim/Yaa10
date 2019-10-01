package com.example.yaa10

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val ADAM = "-אדם-"
    val GOD = "-אלוהים-"

    var newMessage = ""
    var round = 0
    var counter = 0
    var lineNum = 1
    lateinit var mainArrayDialog: MutableList<String>
    lateinit var godList: MutableList<String>
    lateinit var manList: MutableList<String>
    var start = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        operateMan(manList[round])

        goddy.setOnClickListener {
          //  operateGoddy(mainArrayDialog[round * 2 - 1])
            operateGoddy(godList[round])
            round++

        }

        man.setOnClickListener {


            //operateMan(mainArrayDialog[round * 2 - 2])

            operateMan(manList[round])

            /* animateDp()
             animateText(newMessage)
             animatRec(newMessage)*/
        }
    }


    private fun operateGoddy(st: String) {
        // val st1 = orgenizeString(st)
        lineNum = st.length - (st.replace("\n", "").length) + 1
        val st1 = st
        var yy = 0f
        val dy = 115f + ((lineNum - 1) * 3)
        if (start) yy = 0f

        animateView(goddySpeaking, 0f, dy)

        goddySpeaking.text = st1
        lineNum = 1
        start = false
    }


    private fun operateMan(st: String) {
        // val st1 = orgenizeString(st)
        lineNum = st.length - (st.replace("\n", "").length) + 1
        Log.d("clima", "lineNum=$lineNum")
        val st1 = st
        var yy = 0f
        if (start) yy = 30f
        yy = 0f
        var dy = 420 - 30 * (lineNum - 1)
        animateView(manSpeaking, 0f, dy.toFloat())

        manSpeaking.text = st1
        lineNum = 1
        start = false
    }


    private fun animateView(view: View, dx: Float, dy: Float) {

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

  private fun improveString(st:String)=st.substring(1,st.length-1)


    private fun getData() {
        var text = applicationContext.assets.open("text12.txt").bufferedReader().use {
            it.readText()
        }
        text = text.replace("\r", "")
        godList = mutableListOf()
        manList = mutableListOf()
        //  var arr = text.split("\n").toMutableList()
        //var arr = text.split("\r").toMutableList()
        var list1 = text.split(ADAM)
        for (element in list1) {
            if (element!="" && element.length>15) {
                var list2 = element.split(GOD)
                val st1=improveString(list2[1])
                val st2=improveString(list2[0])
                godList.add(st1)
                manList.add(st2)
            }
        }


        /*  mainArrayDialog = mutableListOf()
          var ii = 0
          var st = ""
          var st1 = ""
          var st3 = ""
          var newOne = true
          var counter=0
          while (counter<arr.size){
              if (counter==10){
                  st="t"
              }
              st=arr[counter]
              st1 = arr[counter + 1]
              st1 = st1.replace("\r", "")
              if (newSentence(st)) {
                  newOne = true
                  if (st1=="") counter++
              } else {
                  if (newOne) st3 = st


                  if (newSentence(st1)) {
                      st3 = st.replace("\r", "")
                      if (st!=null) mainArrayDialog.add(st3)
                      newOne = true
                  } else {
                      if (st3 == "") {
                          st3 = st
                      } else {
                          st3 = st3 + "\n"+st1
                          counter++
                          st1 = arr[counter + 1]
                          if (newSentence(st1)) {
                              st3 = st3.replace("\r", "")
                              if (st3!=null) mainArrayDialog.add(st3)
                              newOne = true
                          }
                      }
                  }
                  newOne=false
              }
           counter++

          }*/


    }
    /*  private fun getData() {
          val text = applicationContext.assets.open("text12.txt").bufferedReader().use {
              it.readText()
          }
          //  var arr = text.split("\n").toMutableList()
          var arr = text.split("\n").toMutableList()

          mainArrayDialog = mutableListOf()
          var ii = 0
          var st = ""
          var st1 = ""
          var st3 = ""
          var newOne = true
          var counter=0
          while (counter<arr.size){
              if (counter==10){
                  st="t"
              }
              st=arr[counter]
              st1 = arr[counter + 1]
              st1 = st1.replace("\r", "")
              if (newSentence(st)) {
                  newOne = true
                  if (st1=="") counter++
              } else {
                  if (newOne) st3 = st


                  if (newSentence(st1)) {
                      st3 = st.replace("\r", "")
                      if (st!=null) mainArrayDialog.add(st3)
                      newOne = true
                  } else {
                      if (st3 == "") {
                          st3 = st
                      } else {
                          st3 = st3 + "\n"+st1
                          counter++
                          st1 = arr[counter + 1]
                          if (newSentence(st1)) {
                              st3 = st3.replace("\r", "")
                              if (st3!=null) mainArrayDialog.add(st3)
                              newOne = true
                          }
                      }
                  }
                  newOne=false
              }
              counter++

          }


      }*/

    private fun newSentence(st: String) = (st.contains("-אדם-") || st.contains("-אלוהים-"))


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

