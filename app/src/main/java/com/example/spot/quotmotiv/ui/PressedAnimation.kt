package com.example.spot.quotmotiv.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.spot.quotmotiv.R

object PressedAnimation : AppCompatActivity() {

    fun addAnimation(view: View)
    {
        val attrs = intArrayOf(R.attr.selectableItemBackgroundBorderless)
        val typedArray = view.context.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
          view.setBackgroundResource(backgroundResource)
    }

}