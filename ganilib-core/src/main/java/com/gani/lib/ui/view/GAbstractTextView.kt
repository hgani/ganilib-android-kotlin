package com.gani.lib.ui.view

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.View
import com.gani.lib.utils.Res
import java.util.*
import java.util.regex.Pattern

open class GAbstractTextView : AppCompatTextView {
    private var helper: ViewHelper = ViewHelper(this)

    constructor(context: Context) : super(context) {
//        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init()
    }

//    private fun init() {
//        this.helper = ViewHelper(this)
//    }

    private fun self(): GAbstractTextView {
        return this
    }

//    fun relative(): GRelativeLayoutParams<T> {
//        return helper.relative(self())
//    }

    fun spec(spec: Spec): GAbstractTextView {
        spec.decorate(this)
        return this
    }

    fun width(width: Int?): GAbstractTextView {
        helper.width(width)
        return self()
    }

    fun height(height: Int?): GAbstractTextView {
        helper.height(height)
        return self()
    }

    fun processBold(): GAbstractTextView {
        val matchers = ArrayList<String>()
        val text = text.toString()
        val builder = SpannableStringBuilder(text)
        val boldPattern = Pattern.compile("\\*([A-z0-9 ]+)\\*")
        val matcher = boldPattern.matcher(builder)

        while (matcher.find()) {
            matchers.add(matcher.group())
        }

        for (i in matchers.indices.reversed()) {
            val m = matchers[i]
            val startIndex = text.indexOf(m)
            val endIndex = startIndex + m.length

            val str = SpannableString(m.substring(1, m.length - 1))
            str.setSpan(StyleSpan(Typeface.BOLD), 0, str.length, 0)
            builder.replace(startIndex, endIndex, str)
        }

        setText(builder)

        return this
    }

    fun bgColor(code: String): GAbstractTextView {
        bgColor(Res.color(code))
        return self()
    }

    fun bgColor(color: Int): GAbstractTextView {
        setBackgroundColor(color)
        return self()
    }

    fun color(code: String): GAbstractTextView {
        return color(Res.color(code))
    }

    fun color(color: Int): GAbstractTextView {
        setTextColor(color)
        return self()
    }

    fun paddings(l: Int?, t: Int?, r: Int?, b: Int?): GAbstractTextView {
        helper.padding(l, t, r, b)
        return self()
    }

    fun margins(l: Int?, t: Int?, r: Int?, b: Int?): GAbstractTextView {
        helper.margin(l, t, r, b)
        return self()
    }

    fun bold(): GAbstractTextView {
        return typeface(Typeface.DEFAULT_BOLD)
    }

    fun typeface(typeface: Typeface): GAbstractTextView {
        setTypeface(typeface)
        return this
    }

    fun text(text: String): GAbstractTextView {
        setText(text)
        return this
    }

    fun textSize(textSize: Float): GAbstractTextView {
        setTextSize(textSize)
        //    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return this
    }

    //  public GTextView textSize(int unit, float textSize) {
    //    setTextSize(unit, textSize);
    //    return this;
    //  }

    fun gravity(alignment: Int): GAbstractTextView {
        gravity = alignment
        return this
    }

    fun onClick(listener: View.OnClickListener): GAbstractTextView {
        helper.click(listener)
        return this
    }

    // NOTE: Has to be called before setting the text.
    // See https://stackoverflow.com/questions/27927930/android-linkify-clickable-telephone-numbers
    fun linkify(mask: Int): GAbstractTextView {
        autoLinkMask = mask
        linksClickable = true
        return this
    }

    fun ellipsize(where: TextUtils.TruncateAt): GAbstractTextView {
        ellipsize = where
        return this
    }

    fun maxLines(max: Int): GAbstractTextView {
        maxLines = max
        return this
    }


    //  EnumSet<FileAccess> readWrite = EnumSet.of(FileAccess.Read, FileAccess.Write);


    open class Spec {
        companion object {
            val cellTitle = GAbstractTextView.Spec() { view ->
                view.textSize(18f).bold()
            }

            val cellSubtitle = GAbstractTextView.Spec() { view ->
                view.textSize(16f)
            }
        }

        private val decorator: (GAbstractTextView) -> Unit

        constructor(decorator: (GAbstractTextView) -> Unit ) {
            this.decorator = decorator
        }

        fun decorate(view: GAbstractTextView) {
            decorator(view)
        }
    }
}
