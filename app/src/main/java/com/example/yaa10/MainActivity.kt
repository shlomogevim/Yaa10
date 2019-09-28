package com.example.yaa10

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        smilingPerson.setOnClickListener {
            animateDp()
            animateColor()
        }
    }

    private fun animateDp() {

        ViewAnimator
            .animate(smilingPerson)
            .dp().translationY(0f, 80f,0f)
            .duration(1000)
            .repeatCount(5)
            .onStop {
                speakingTextview1.text = "זהו גמרתי לקפוץ, עכשיו קצת ננוח"

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
    private fun animateColor() {
        ViewAnimator
            .animate(speakingTextview1)
            .onStart {
                speakingTextview1.text = "מה קורה נסיך"
            }
            .textColor(Color.BLACK, Color.GREEN)
            .backgroundColor(Color.WHITE, Color.BLACK)
            .duration(3000)
            .start()
    }
}
