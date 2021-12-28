package com.danielwaiguru.remoter.dashboard.presentation.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.core.domain.use_cases.GetJobsUseCase
import kotlinx.coroutines.launch

class DashBoardViewModel(private val getJobsUseCase: GetJobsUseCase): ViewModel() {
    private val _allJobs: MutableLiveData<ResultWrapper<List<JobDomain>>> = MutableLiveData()
    val allJobs: LiveData<ResultWrapper<List<JobDomain>>> get() = _allJobs

    init {
        getAllJobs()
    }
    fun getAllJobs() {
        _allJobs.value = ResultWrapper.Loading
        viewModelScope.launch {
            _allJobs.postValue(
                getJobsUseCase.invoke()
            )
        }
    }
}