package com.att.tapyou.ui.navigation

import android.content.Intent

data class IntentParams(
    val clazz: Class<*>? = null,
    val intent: Intent? = null,
    val finish: Boolean = true
)