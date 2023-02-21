package com.erbe.nowinandroid.feature.article.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.erbe.nowinandroid.core.common.extension.DataState
import com.erbe.nowinandroid.core.common.extension.process
import com.erbe.nowinandroid.core.common.extension.state
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.feature.article.adapter.ArticleLatestAdapter
import com.erbe.nowinandroid.feature.article.databinding.ViewArticleLatestBinding

class ArticleLatestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewArticleLatestBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var articleLatestAdapter: ArticleLatestAdapter

    fun setView(
        title: String,
        onItemClick: (ArticleContentTopic, View) -> Unit,
        onSeeAllClick: (View) -> Unit
    ) {
        articleLatestAdapter = ArticleLatestAdapter(onItemClick)
        binding.recyclerViewArticleLatest.adapter = articleLatestAdapter

        binding.seeAllArticleLatest.setTitle(title)
        binding.seeAllArticleLatest.onSeeAllClick(onSeeAllClick)
    }

    fun setState(articleState: DataState<List<ArticleContentTopic>>) {
        articleState.state(
            binding.progressBar,
            binding.textError,
            listOf(
                binding.seeAllArticleLatest,
                binding.recyclerViewArticleLatest
            )
        )

        articleState.process(
            onError = { error ->
                binding.textError.text = error?.message ?: "Error"
            },
            onSuccess = { data ->
                setData(data)
            }
        )
    }

    private fun setData(data: List<ArticleContentTopic>) {
        articleLatestAdapter.submitList(data)
    }
}