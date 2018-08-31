package com.gani.lib.ui.json

import com.gani.lib.json.GJson
import com.gani.lib.logging.GLog
import com.gani.lib.screen.GActivity
import com.gani.lib.screen.GFragment
import com.gani.lib.ui.json.views.panels.VerticalV1
import com.gani.lib.ui.panel.GVerticalPanel

class JsonUi {
    companion object {
        fun parse(spec: GJson, fragment: GFragment) {
            val screen = fragment.gActivity
            val container = fragment.container
            if (screen == null || container == null) {
                return
            }

            initVerticalPanel(container.header, spec["header"], screen)
            initVerticalPanel(container.content, spec["content"], screen)
            initVerticalPanel(container.footer, spec["footer"], screen)
            JsonAction.executeAll(spec["onLoad"], screen)
        }

        fun parse(spec: GJson) {
            JsonBgAction.executeAll(spec["onLoad"])
        }

        private fun initVerticalPanel(panel: GVerticalPanel, spec: GJson, screen: GActivity) {
            VerticalV1(panel, spec, screen).createView()
        }

        fun <T> loadClass(name: String, type: Class<T>, namespace: String): Class<T>? {
            val substrings = name.split("/")
            val prefix = substrings.dropLast(1).joinToString(".")
            val className = substrings.lastOrNull()?.replace("-v", "V")?.capitalize()
            val prefixedName = if (prefix.length > 0) "$prefix.$className" else className
            val qualifiedName = "com.gani.lib.ui.json.$namespace.$prefixedName"
            GLog.i(JsonUi::class.java, "Loading $qualifiedName from $name")
            try {
                return Class.forName(qualifiedName) as? Class<T>
            } catch (ex: Exception) {
                return null
            }
        }
    }
}