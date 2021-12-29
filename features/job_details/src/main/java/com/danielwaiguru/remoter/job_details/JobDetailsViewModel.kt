package com.danielwaiguru.remoter.job_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danielwaiguru.remoter.shared.utils.SingleLiveEvent

class JobDetailsViewModel: ViewModel() {
    private val _navigateBack = SingleLiveEvent<Unit>()
    val navigateBack: LiveData<Unit> get() = _navigateBack

    fun onNavBackPressed() {
        _navigateBack.postValue(Unit)
    }
}