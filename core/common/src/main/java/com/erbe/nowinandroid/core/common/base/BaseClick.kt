package com.erbe.nowinandroid.core.common.base

import android.util.Log
import android.view.View

fun click(action: (View) -> Unit): View.OnClickListener {
    return View.OnClickListener { view ->
        try {
            val name = view.context.resources.getResourceEntryName(view.id)
            Log.d("TAG_PRO", "onClick: $name")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        action(view)
    }
}