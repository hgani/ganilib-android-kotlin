package com.gani.lib.ui.menu

import android.graphics.drawable.Drawable
import android.view.MenuItem

import com.gani.lib.ui.icon.GIcon

class GMenuItem(private val backend: MenuItem) {
    fun alwaysShow(): GMenuItem {  // Not applicable for certain types of menu, e.g. popup menu.
        backend.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return this
    }

    fun icon(icon: GIcon): GMenuItem {
        return icon(icon.drawable().sizeDp(GIcon.ACTION_BAR_SIZE))
    }

    fun icon(drawable: Drawable): GMenuItem {
        backend.icon = drawable
        return this
    }

    fun onClick(listener: (GMenuItem) -> Unit): GMenuItem {
        backend.setOnMenuItemClickListener {
            listener(this)
            true
        }
        return this
    }
}