package com.erbe.nowinandroid.data.article.data.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.erbe.nowinandroid.data.article.dispatcher.MainDispatcherRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class DataTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
}