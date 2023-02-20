package com.erbe.nowinandroid.feature.article.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbe.nowinandroid.feature.article.databinding.ViewArticleLatestBinding

class ArticleLatestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewArticleLatestBinding.inflate(LayoutInflater.from(context), this)
}