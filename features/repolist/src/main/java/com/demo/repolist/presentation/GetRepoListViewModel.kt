package com.demo.repolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.repolist.domain.GetRepoListUseCase
import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.launch

class GetRepoListViewModel(private val useCase: GetRepoListUseCase) : ViewModel() {
    private val _repoList: MutableLiveData<List<GithubRepository>> by lazy {
        MutableLiveData<List<GithubRepository>>(emptyList())
    }

    val repoList: LiveData<List<GithubRepository>> get() = _repoList

    init {
        getRepoListForAndroidQuery()
    }

    fun getRepoListForQuery(query: String, itemCount: Int) {
        viewModelScope.launch {
            useCase.invoke(query, itemCount).collect {
                _repoList.value = it
            }
        }
    }

    fun getRepoListForAndroidQuery() {
        getRepoListForQuery("Android", 10)
    }
}
