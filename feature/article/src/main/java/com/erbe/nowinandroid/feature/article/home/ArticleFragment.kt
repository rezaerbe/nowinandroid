package com.erbe.nowinandroid.feature.article.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.extension.process
import com.erbe.nowinandroid.core.design.generateConstraintView
import com.erbe.nowinandroid.feature.article.databinding.FragmentArticleBinding
import com.erbe.nowinandroid.feature.article.home.view.ArticleCategoryLatestView
import com.erbe.nowinandroid.feature.article.home.view.ArticleLatestView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate) {

    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionArticle()
        sectionCategoryArticle()
    }

    private fun sectionArticle() {
        val articleLatestView = ArticleLatestView(requireContext())
        generateConstraintView(binding.articleContainer, articleLatestView)

        articleViewModel.articleState.launchAndCollectIn(viewLifecycleOwner) { articleState ->
            articleState.process { }
        }
    }

    private fun sectionCategoryArticle() {
        val articleCategoryLatestView = ArticleCategoryLatestView(requireContext())
        generateConstraintView(binding.articleContainer, articleCategoryLatestView)

        articleViewModel.articleCategoryState.launchAndCollectIn(viewLifecycleOwner) { articleCategoryState ->
            articleCategoryState.process { }
        }
    }
}