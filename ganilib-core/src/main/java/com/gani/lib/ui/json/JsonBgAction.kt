package com.gani.lib.ui.json

import com.gani.lib.json.GJson
import com.gani.lib.json.GJsonObject
import com.gani.lib.logging.GLog

abstract class JsonBgAction(val spec: GJson) {
    fun execute() {
        if (!silentExecute()) {
            GLog.w(javaClass, "Invalid action spec: $spec")
        }
    }

    abstract fun silentExecute(): Boolean

    companion object {
        private fun create(spec: GJson): JsonBgAction? {
            val klass = JsonUi.loadClass(spec["action"].stringValue, JsonBgAction::class.java, "actions")
            val constructor = klass?.getConstructor(GJsonObject::class.java)
            if (constructor != null) {
                return constructor.newInstance(spec)
            }
            GLog.w(JsonBgAction::class.java, "Failed loading action: $spec")
            return null
        }

        fun executeAll(spec: GJson) {
            spec.arrayValue.forEach {
                create(it)?.execute()
            }
        }
    }
}