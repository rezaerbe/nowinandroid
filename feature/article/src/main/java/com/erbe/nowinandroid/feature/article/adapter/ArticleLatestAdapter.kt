package com.erbe.nowinandroid.feature.article.adapter

import android.view.View
import com.erbe.nowinandroid.core.common.base.BaseAdapter
import com.erbe.nowinandroid.core.common.base.click
import com.erbe.nowinandroid.core.design.loadImage
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.feature.article.databinding.ItemArticleLatestBinding

class ArticleLatestAdapter(
    private val onItemClick: (ArticleContentTopic, View) -> Unit
) :
    BaseAdapter<ArticleContentTopic, ItemArticleLatestBinding>(ItemArticleLatestBinding::inflate) {

    override fun onItemBind(): (ArticleContentTopic, ItemArticleLatestBinding, View) -> Unit =
        { item, binding, itemView ->

            binding.articleImage.loadImage(item.image)
            binding.articleTitle.text = item.title
            binding.articleDate.text = item.date
            binding.articleTime.text = item.time

            binding.articleTopicImage.loadImage(item.topic.image)
            binding.articleTopicName.text = item.topic.name

            itemView.setOnClickListener(click { view ->
                onItemClick(item, view)
            })
        }
}