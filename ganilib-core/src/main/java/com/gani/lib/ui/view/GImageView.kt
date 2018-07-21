package com.gani.lib.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gani.lib.utils.Res

class GImageView : AppCompatImageView {
    private var helper: ViewHelper = ViewHelper(this)

    constructor(context: Context) : super(context) {
//        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init()
    }


//    fun imageUrl(url: String?): GImageView {
//        if (url != null) {
////            Glide.with(context.applicationContext) //using application context prevents app crash when closing activity during image load
////                    .load(url)
////                    .into(this)
//        }
//        return this
//    }

    fun width(width: Int?): GImageView {
        helper.width(width)
        return this
    }

    fun height(height: Int?): GImageView {
        helper.height(height)
        return this
    }

    fun bgColor(code: String): GImageView {
        setBackgroundColor(Res.color(code))
        return this
    }

    fun bgColor(res: Int): GImageView {
        helper.bgColor(res)
        return this
    }

    fun bg(res: Int): GImageView {
        helper.bg(res)
        return this
    }

    fun source(url: String?): GImageView {
        if (url != null) {
            Glide.with(context.applicationContext) //using application context prevents app crash when closing activity during image load
                    .load(url)
                    .into(this)
        }
        return this
    }

//    fun drawable(drawable: Drawable): GImageView {
//        setImageDrawable(drawable)
//        return this
//    }

    fun source(drawable: Drawable): GImageView {
        setImageDrawable(drawable)
        return this
    }

    fun source(bitmap: Bitmap): GImageView {
        setImageBitmap(bitmap)
        return this
    }

//    fun source(icon: GIcon): GImageView {
//        return source(drawable = icon.drawable())
//    }

//    fun drawable(resId: Int): GImageView {
//        setImageDrawable(Ui.drawable(resId))
//        return this
//    }

    fun margin(left: Int?, top: Int?, right: Int?, bottom: Int?): GImageView {
        helper.margin(left, top, right, bottom)
        return this
    }

    fun adjustBounds(): GImageView {
        adjustViewBounds = true
        return this
    }

    fun centerCrop(): GImageView {
        setScaleType(ImageView.ScaleType.CENTER_CROP);
        return this
    }

    fun onClick(listener: View.OnClickListener): GImageView {
        helper.click(listener)
        return this
    }
}
