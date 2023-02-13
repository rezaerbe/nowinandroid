package com.erbe.nowinandroid.feature.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erbe.nowinandroid.core.common.base.BaseFragment
import com.erbe.nowinandroid.core.common.extension.launchAndCollectIn
import com.erbe.nowinandroid.core.common.extension.process
import com.erbe.nowinandroid.feature.article.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate) {

    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.articleState.launchAndCollectIn(viewLifecycleOwner) { articleState ->
            articleState.process { }
        }
    }
}