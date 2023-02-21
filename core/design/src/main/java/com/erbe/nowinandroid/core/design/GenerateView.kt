package com.erbe.nowinandroid.core.design

import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams

fun generateLinearView(parent: LinearLayout, view: View) {
    view.id = View.generateViewId()
    parent.addView(view)

    view.updateLayoutParams<LinearLayout.LayoutParams> {
        width = LayoutParams.MATCH_PARENT
        height = LayoutParams.WRAP_CONTENT
    }
}

/*
fun generateConstraintView(parent: ConstraintLayout, view: View) {
    val count = parent.childCount

    view.id = View.generateViewId()
    parent.addView(view)

    view.updateLayoutParams<ConstraintLayout.LayoutParams> {
        width = LayoutParams.MATCH_PARENT
        height = LayoutParams.WRAP_CONTENT
    }

    val constraintSet = ConstraintSet()
    constraintSet.clone(parent)

    when (count) {
        0 -> {
            constraintSet.connect(
                view.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP
            )
        }
        else -> {
            val lastView = parent.children.toList()[count - 1]
            constraintSet.connect(
                view.id,
                ConstraintSet.TOP,
                lastView.id,
                ConstraintSet.BOTTOM
            )
        }
    }

    constraintSet.applyTo(parent)
}*/
