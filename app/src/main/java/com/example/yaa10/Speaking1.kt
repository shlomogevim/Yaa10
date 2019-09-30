package com.example.yaa10

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class Speaking1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
     val hight=100
    val wight=100

    companion object {
        private const val DEFAULT1 = Color.CYAN
        private const val DEFAULT2 = Color.BLACK
        private const val DEFAULT3 = Color.BLACK
        private const val DEFAULT_BOURDER = Color.BLACK
        private const val DEFAULT_WIDTH = 4.0f
        private const val DEFAULT_TEXT_SIZE = 24.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    private var color1 = DEFAULT1
    private var color2 = DEFAULT2
    private var color3 = DEFAULT3
    private var borderColor = DEFAULT_BOURDER
    private var borderWidth = DEFAULT_WIDTH
    private var textSize = DEFAULT_TEXT_SIZE

    private val paint = Paint()
    private val mouthPath = Path()
    private var size = 0

    var happinessState = HAPPY
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.DrawAssitance, 0, 0)

        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.DrawAssitance_state, HAPPY.toInt()).toLong()
        color1 = typedArray.getColor(R.styleable.DrawAssitance_color1, DEFAULT1)
        color2 = typedArray.getColor(R.styleable.DrawAssitance_color2, DEFAULT2)
        color3 = typedArray.getColor(R.styleable.DrawAssitance_color3, DEFAULT3)
        borderColor = typedArray.getColor(R.styleable.DrawAssitance_color4, DEFAULT_BOURDER)
        borderWidth = typedArray.getDimension(R.styleable.DrawAssitance_borderWidth, DEFAULT_WIDTH)
        textSize = typedArray.getDimension(R.styleable.DrawAssitance_textSize, DEFAULT_TEXT_SIZE)

        typedArray.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
       // setMeasuredDimension(size, size)
        var w = wight
        var h = hight
        setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)
        drawRec(canvas)

        /* drawFaceBackground(canvas)
         drawEyes(canvas)
         drawMouth(canvas)*/
    }


    private fun drawRec(canvas: Canvas) {
        paint.color = color1
        paint.style = Paint.Style.FILL
        var x1 = 0f
        var y1 = 0f
        var x2 = wight.toFloat()
        var y2 = hight.toFloat()
        canvas.drawRect(x1, y1, x2, y2, paint)
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth




        canvas.drawRect(x1, y1, x2, y2, paint)

    }

    private fun drawFaceBackground(canvas: Canvas) {
        // 1
        paint.color = color1
        paint.style = Paint.Style.FILL

        // 2
        val radius = size / 2f

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        // 1
        paint.color = color2
        paint.style = Paint.Style.FILL

// 2
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

// 3
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas) {

        // Clear
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            // Happy mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            // Sad mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

        paint.color = color3
        paint.style = Paint.Style.FILL

        // Draw mouth path
        canvas.drawPath(mouthPath, paint)
    }

    override fun onSaveInstanceState(): Parcelable {
        // 1
        val bundle = Bundle()
        // 2
        bundle.putLong("happinessState", happinessState)
        // 3
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        // 4
        var viewState = state
        if (viewState is Bundle) {
            // 5
            happinessState = viewState.getLong("happinessState", HAPPY)
            // 6
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }


}