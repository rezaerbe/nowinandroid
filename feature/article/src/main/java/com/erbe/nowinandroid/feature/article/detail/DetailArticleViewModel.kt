package com.erbe.nowinandroid.feature.article.detail

import androidx.lifecycle.SavedStateHandle
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
class DetailArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articleDetailState = MutableStateFlow<DataState<Article>>(DataState.Loading)
    val articleDetailState = _articleDetailState.asStateFlow()

    init {
        getArticleDetail()
    }

    private fun getArticleDetail() {
        viewModelScope.launch {
            _articleDetailState.asDataState {
                val id = savedStateHandle.get<String>("id")
                articleRepository.getArticleDetail(id)
            }
        }
    }
}