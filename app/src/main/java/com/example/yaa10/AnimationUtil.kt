package com.example.yaa10

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.Dimension
import com.github.florent37.viewanimator.ViewAnimator
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class AnimationUtil(context: Context?)  {

    val wi=Resources.getSystem().displayMetrics.widthPixels.toFloat()
    val hi=Resources.getSystem().displayMetrics.heightPixels.toFloat()




    fun godTranslaion1(view1:View,view2:View){
        ViewAnimator
            .animate(view1)
            .translationX(-wi/2,0f)
            .translationY(hi,0f)
            .scale(0f,2f)
            .andAnimate(view2)
            .translationX(wi/2,0f)
            .translationY(hi,0f)
            .scale(0f,2f)
            .duration(2000)
            .start()





    }
     fun manTranslation1(ind:Int,view1:View,view2:View) {
        if (ind==0) {
            ViewAnimator
                .animate(view2)
                .alpha(1f,0f)
                .duration(1)

                .thenAnimate(view1)
                .translationX(-wi/2,0f)
                .translationY(-hi,0f)
                .scale(0f,1f)

                .thenAnimate(view2)
                .alpha(0f,1f)
                .duration(1)
                .translationX(wi/2,0f)
                .translationY(-hi,0f)
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
