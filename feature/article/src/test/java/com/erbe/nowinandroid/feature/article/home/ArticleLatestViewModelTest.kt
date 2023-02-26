package com.erbe.nowinandroid.feature.article.home

import com.erbe.nowinandroid.core.common.extension.DataState
import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.data.article.data.repository.ArticleRepository
import com.erbe.nowinandroid.feature.article.expected.listArticleContentTopic
import com.erbe.nowinandroid.feature.article.util.ViewModelTest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ArticleLatestViewModelTest : ViewModelTest() {

    private lateinit var articleRepository: ArticleRepository
    private lateinit var articleViewModel: ArticleViewModel

    @Before
    fun initViewModel() {
        articleRepository = mock()
        articleViewModel = ArticleViewModel(articleRepository)
    }

    @Test
    fun test() = runTest {
        whenever(articleRepository.getArticleLatest()).thenReturn(listArticleContentTopic)
        val values = mutableListOf<DataState<List<ArticleContentTopic>>>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            articleViewModel.articleState.collect(values::add)
        }
        advanceUntilIdle()

        Assert.assertEquals(
            listOf(DataState.Loading, DataState.Success(listArticleContentTopic)),
            values
        )
    }
}