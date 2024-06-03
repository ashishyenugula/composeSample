package com.ashish.composesample

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ashish.composesample.data.model.response.NewsResponse
import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.usecase.GetNewsListUseCase
import com.ashish.composesample.ui.list.ListViewModel
import kotlinx.coroutines.runBlocking

import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NewsTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun myComposeUnitTest() {
        composeTestRule
            .onNodeWithContentDescription("LazyColumn")
            .performScrollToIndex(2)
    }
}