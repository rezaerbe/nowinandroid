package com.erbe.nowinandroid.feature.article.list.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erbe.nowinandroid.core.common.extension.DataState
import com.erbe.nowinandroid.core.common.extension.asDataState
import com.erbe.nowinandroid.data.article.data.model.ArticleTopicContent
import com.erbe.nowinandroid.data.article.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articleCategoryState =
        MutableStateFlow<DataState<ArticleTopicContent>>(DataState.Loading)
    val articleCategoryState = _articleCategoryState.asStateFlow()

    init {
        getArticleByCategory()
    }

    private fun getArticleByCategory() {
        viewModelScope.launch {
            _articleCategoryState.asDataState {
                val category = savedStateHandle.get<String>("category")
                articleRepository.getArticleByCategory(category)
            }
        }
    }
}