package com.ashish.composesample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.usecase.GetNewsDetailUseCase
import com.ashish.composesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetNewsDetailUseCase
) : BaseViewModel() {

    private val _showSmartMode = MutableLiveData(true)
    val showSmartMode: LiveData<Boolean> = _showSmartMode

    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    fun getNewsById(newsId: Int) {
        call({
            useCase.getNewsDetail(newsId)
        }, onSuccess = {
            _news.postValue(it)
        })
    }

    fun toggleMode() {
        _showSmartMode.value = !_showSmartMode.value!!
    }
}