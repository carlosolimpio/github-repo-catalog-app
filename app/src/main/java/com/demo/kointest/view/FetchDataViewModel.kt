package com.demo.kointest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.testdomain.domain.FetchDataUseCase
import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FetchDataViewModel(private val useCase: FetchDataUseCase) : ViewModel() {

    private val _repoList: MutableLiveData<List<GithubRepository>> by lazy {
        MutableLiveData<List<GithubRepository>>(emptyList())
    }

    val repoList: LiveData<List<GithubRepository>> get() = _repoList

    fun fetchData() {
        viewModelScope.launch {
            useCase.invoke().collect {
                _repoList.value = it
            }
        }
    }
}
