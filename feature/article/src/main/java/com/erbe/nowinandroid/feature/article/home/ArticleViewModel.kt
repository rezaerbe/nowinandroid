package com.erbe.nowinandroid.feature.article.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbe.nowinandroid.core.common.extension.DataState
import com.erbe.nowinandroid.core.common.extension.asDataState
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopicContent
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

    private val _articleState =
        MutableStateFlow<DataState<List<ArticleContentTopic>>>(DataState.Loading)
    val articleState = _articleState.asStateFlow()

    private val _articleCategoryState =
        MutableStateFlow<DataState<List<ArticleTopicContent>>>(DataState.Loading)
    val articleCategoryState = _articleCategoryState.asStateFlow()

    fun getArticleLatest() {
        viewModelScope.launch {
            _articleState.asDataState {
                articleRepository.getArticleLatest()
            }
        }
    }

    fun getArticleByCategoryLatest() {
        viewModelScope.launch {
            _articleCategoryState.asDataState {
                articleRepository.getArticleByCategoryLatest()
            }
        }
    }
}