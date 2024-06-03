package com.ashish.composesample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashish.composesample.domain.model.News
import com.ashish.composesample.domain.usecase.GetNewsListUseCase
import com.ashish.composesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetNewsListUseCase
) : BaseViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    fun getNewsList() {
        call({
            useCase.getNewsList()
        }, onSuccess = {
            _newsList.postValue(it)
        })
    }
}