package com.erbe.nowinandroid.feature.article.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.erbe.nowinandroid.feature.article.dispatcher.MainDispatcherRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
}