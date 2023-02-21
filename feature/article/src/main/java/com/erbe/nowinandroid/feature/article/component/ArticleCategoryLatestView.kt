package com.erbe.nowinandroid.feature.article.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbe.nowinandroid.feature.article.databinding.ViewArticleCategoryLatestBinding

class ArticleCategoryLatestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewArticleCategoryLatestBinding.inflate(LayoutInflater.from(context), this, true)
}