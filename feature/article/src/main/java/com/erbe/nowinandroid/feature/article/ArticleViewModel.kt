package com.erbe.nowinandroid.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbe.nowinandroid.core.common.extension.DataState
import com.erbe.nowinandroid.core.common.extension.asDataState
import com.erbe.nowinandroid.data.article.data.model.Article
import com.erbe.nowinandroid.data.article.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articleState = MutableStateFlow<DataState<List<Article>?>>(DataState.Loading)
    val articleState = _articleState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            _articleState.asDataState {
                articleRepository.getArticles()
            }
        }
    }
}