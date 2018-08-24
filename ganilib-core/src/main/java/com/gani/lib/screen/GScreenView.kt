package com.gani.lib.screen

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.*
import com.gani.lib.R
import com.gani.lib.ui.icon.GIcon


open class GScreenView(private val activity: GActivity) : IScreenView(activity) {

    private val layout: ViewGroup
    private val body: ViewGroup
    protected val drawer: DrawerLayout
    private var selectedItem: MenuItem? = null
    private var navMenu: NavigationMenu? = null
    private val badge: NavigationHomeBadge
//    override val toolbar: Toolbar
//        get() = layout.findViewById<View>(R.id.screen_toolbar) as Toolbar
    override final val toolbar: Toolbar

    init {
        this.layout = LayoutInflater.from(context).inflate(R.layout.view_screen, this) as ViewGroup
        this.body = layout.findViewById<View>(R.id.screen_body) as ViewGroup
        this.drawer = findViewById<View>(R.id.screen_drawer) as DrawerLayout
        this.badge = NavigationHomeBadge(this)
        this.toolbar = layout.findViewById<View>(R.id.screen_toolbar) as Toolbar
    }

    override fun openDrawer() {
        drawer.openDrawer(GravityCompat.START)
    }

//    override fun getToolbar(): Toolbar {
//        return layout.findViewById<View>(R.id.screen_toolbar) as Toolbar
//    }

    override fun setBody(resId: Int) {
        LayoutInflater.from(context).inflate(resId, body)
    }

    ///// Navigation /////

    override public fun initNavigation(topNavigation: Boolean, actionBar: ActionBar) {
        val drawer = drawer
        val navView = drawer.findViewById<View>(R.id.view_navigation) as NavigationView

        this.navMenu = NavigationMenu(navView.menu, actionBar)

        if (topNavigation) {
            val icon = menuIcon()
            if (icon != null) {
                actionBar.setHomeAsUpIndicator(badge.drawable)
            }
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    fun updateBadge(count: Int) {
        badge.setCount(count)
    }

    open fun menuIcon(): Drawable? {
        return null
    }

    protected fun addMenu(icon: GIcon, label: String, intent: Intent): MenuItem {
        return navMenu!!.addItem(GROUP_PRIMARY, icon, label, intent)
    }

    /////


    private inner class NavigationMenu internal constructor(private val menu: Menu, private val bar: ActionBar) {

        private fun intentEquals(menuIntent: Intent): Boolean {
            val activityIntent = activity.intent
            if (activityIntent.component != menuIntent.component) {
                return false
            }

            val activityExtras = activityIntent.extras
            val menuExtras = menuIntent.extras

            if (activityExtras != null && menuExtras != null) {
                for (key in activityExtras.keySet()) {
                    if (activityExtras.get(key) != menuExtras.get(key)) {
                        return false
                    }
                }
            }
            return true
        }

        internal fun addItem(groupId: Int, icon: GIcon?, string: String, intent: Intent): MenuItem {
            val item = menu.add(groupId, Menu.NONE, Menu.NONE, string)
            item.intent = intent
            item.isCheckable = true  // Needed for setChecked() to work
            if (icon != null) {
                item.icon = icon.drawable()
            }

            if (intentEquals(intent)) {
                selectedItem = item
                item.isChecked = true
                bar.title = string
            }
            return item
        }
    }

    companion object {
        private val GROUP_PRIMARY = 1
        private val GROUP_SECONDARY = 2
    }
}