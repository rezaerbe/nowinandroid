package com.erbe.nowinandroid.feature.article.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbe.nowinandroid.core.common.base.click
import com.erbe.nowinandroid.feature.article.databinding.ViewSeeAllBinding

class SeeAllView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewSeeAllBinding.inflate(LayoutInflater.from(context), this)

    fun setTitle(title: String) {
        binding.textTitle.text = title
    }

    fun onSeeAllClick(
        onClick: (View) -> Unit
    ) {
        binding.textSeeAll.setOnClickListener(click { v ->
            onClick(v)
        })
    }
}