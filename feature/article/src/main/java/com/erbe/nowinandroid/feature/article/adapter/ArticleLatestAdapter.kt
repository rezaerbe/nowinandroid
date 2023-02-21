package com.erbe.nowinandroid.feature.article.adapter

import android.view.View
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.common.base.click
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.feature.article.databinding.ItemArticleLatestBinding

class ArticleLatestAdapter(
    private val onItemClick: (ArticleContentTopic, View) -> Unit
) :
    BaseAdapter<ArticleContentTopic, ItemArticleLatestBinding>(ItemArticleLatestBinding::inflate) {

    override fun onItemBind(): (ArticleContentTopic, ItemArticleLatestBinding, View) -> Unit =
        { item, binding, view ->

            binding.articleTopicName.text = item.topic.name
            binding.articleTitle.text = item.title
            binding.articleDate.text = item.date
            binding.articleTime.text = item.time

            view.setOnClickListener(click { v ->
                onItemClick(item, v)
            })
        }
}