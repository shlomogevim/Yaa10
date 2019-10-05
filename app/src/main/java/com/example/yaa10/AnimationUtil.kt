package com.example.yaa10

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.github.florent37.viewanimator.ViewAnimator


class AnimationUtil(context: Context?) {

    val wi = Resources.getSystem().displayMetrics.widthPixels.toFloat()
    val hi = Resources.getSystem().displayMetrics.heightPixels.toFloat()

    fun styleItGod1(view: TextView, st: String): TextView {
        var view1 = view

        view1.setBackgroundColor(Color.YELLOW)
        // view1.setBackgroundResource(R.color.yelow)
        view1.setTextColor(Color.parseColor("#bdbdbd"))
        //view1.setTextColor(resources.getColor(R.color.red))
        view1.text = st
        view1.setPadding(20, 0, 20, 0)

        return view1
    }

    fun styleItMan1(textview: TextView, st: String): TextView {
        var textView1 = textview

        textView1.setBackgroundColor(Color.DKGRAY)
        // view1.setBackgroundResource(R.color.yelow)
        textView1.setTextColor(Color.parseColor("#bdbdbd"))
        //  view1.setTextColor(resources.getColor(R.color.red))
        textView1.text = st

        //  textView1.setPadding(20,0,20,0)
        //   textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,10f)

        return textView1
    }


    fun godTranslaion1(arr: List<String>, textView1: TextView, textView2: TextView) {
        val textView10 = styleItGod1(textView1, arr[0])
        val textView20 = styleItGod1(textView2, arr[0])
        ViewAnimator
            .animate(textView10)
            .translationX(-wi / 2, 0f)
            .translationY(hi, 0f)
            .scale(0f, 2f)
            .andAnimate(textView20)
            .translationX(wi / 2, 0f)
            .translationY(hi, 0f)
            .scale(0f, 2f)
            .duration(2000)
            .start()
    }


    fun styleItMan51(tv: TextView, st: String): TextView {
        var textView = tv
        textView.text = st
        textView.setBackgroundColor(Color.DKGRAY)
        textView.setTextColor(Color.WHITE)
        textView.setPadding(30, 0, 30, 0)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        textView.gravity=Gravity.HORIZONTAL_GRAVITY_MASK
        return textView
    }

    fun manTranslaion51(
        arr: List<String>, textView1: TextView, textView2: TextView,
        textView3: TextView, textView4: TextView, textView5: TextView
    ) {

        val textView10 = styleItMan51(textView1, arr[4])
        val textView11 = styleItMan51(textView2, arr[3])
        val textView12 = styleItMan51(textView3, arr[2])
        val textView13 = styleItMan51(textView4, arr[1])
        val textView14 = styleItMan51(textView5, arr[0])
         var dur=1000L

        ViewAnimator
            .animate(textView10,textView11,textView12,textView13,textView14)
            .scale(0f)
            .duration(1)
            .thenAnimate(textView14)
            .scale(0f,1f)
            .duration(dur)
            .thenAnimate(textView13)
            .scale(0f,1f)
            .duration(dur)
            .thenAnimate(textView12)
            .scale(0f,1f)
            .duration(dur)
            .thenAnimate(textView11)
            .scale(0f,1f)
            .duration(dur)
            .thenAnimate(textView10)
            .scale(0f,1f)
            .duration(dur)
            .thenAnimate(textView10)
            .scale(1f,0f,1f)
            .repeatCount(2)
            .duration(dur)
            .start()
    }

    fun manTranslation1(ind: Int, view1: View, view2: View) {
        if (ind == 0) {
            ViewAnimator
                .animate(view2)
                .alpha(1f, 0f)
                .duration(1)

                .thenAnimate(view1)
                .translationX(-wi / 2, 0f)
                .translationY(-hi, 0f)
                .scale(0f, 1f)

                .thenAnimate(view2)
                .alpha(0f, 1f)
                .duration(1)
                .translationX(wi / 2, 0f)
                .translationY(-hi, 0f)
                .duration(2000)

                .start()
        }
    }
}

/*
if (ind==0) {
    ViewAnimator
        .animate(textView)
        .translationX(0f, 300f, -300f, 0f)
        .translationY(0f, 500f, -500f, 0f)
        .duration(2000)
        .repeatCount(3)
        .thenAnimate(textView)
        .translationX(0f, -300f, 300f, 0f)
        .translationY(0f, -500f, 500f, 0f)
        .duration(2000)
        .repeatCount(3)
        .start()
}*/
