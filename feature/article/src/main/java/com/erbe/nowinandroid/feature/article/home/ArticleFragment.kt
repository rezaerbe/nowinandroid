package com.erbe.nowinandroid.feature.article.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.extension.process
import com.erbe.nowinandroid.core.design.generateLinearView
import com.erbe.nowinandroid.feature.article.component.ArticleCategoryLatestView
import com.erbe.nowinandroid.feature.article.component.ArticleLatestView
import com.erbe.nowinandroid.feature.article.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate) {

    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionArticle()
        // sectionCategoryArticle()
    }

    private fun sectionArticle() {
        val articleLatestView = ArticleLatestView(requireContext())
        generateLinearView(binding.articleContainer, articleLatestView)
        articleLatestView.setView(
            "Latest",
            onItemClick = { _, _ -> },
            onSeeAllClick = { }
        )

        articleViewModel.articleState.launchAndCollectIn(viewLifecycleOwner) { articleState ->
            articleLatestView.setState(articleState)
        }
    }

    private fun sectionCategoryArticle() {
        val articleCategoryLatestView = ArticleCategoryLatestView(requireContext())
        generateLinearView(binding.articleContainer, articleCategoryLatestView)

        articleViewModel.articleCategoryState.launchAndCollectIn(viewLifecycleOwner) { articleCategoryState ->
            articleCategoryState.process { }
        }
    }
}