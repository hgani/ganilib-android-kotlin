package com.gani.lib.ui.view

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.widget.RadioButton

class GRadioButton : RadioButton, IView {
    private var helper: ViewHelper = ViewHelper(this)

    private var customClickSound: MediaPlayer? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        this.helper = ViewHelper(this)
    }

    override fun width(width: Int?): GRadioButton {
        helper.width(width)
        return self()
    }

    override fun height(height: Int?): GRadioButton {
        helper.height(height)
        return self()
    }

    override fun padding(l: Int?, t: Int?, r: Int?, b: Int?): GRadioButton {
        helper.padding(l, t, r, b)
        return self()
    }

    override fun margin(l: Int?, t: Int?, r: Int?, b: Int?): GRadioButton {
        helper.margin(l, t, r, b)
        return self()
    }

    override fun bgColor(color: Int): GRadioButton {
        helper.bgColor(color)
        return self()
    }

    fun text(text: String): GRadioButton {
        this.text = text
        return self()
    }

    private fun self(): GRadioButton {
        return this
    }
}