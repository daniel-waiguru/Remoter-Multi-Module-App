package com.danielwaiguru.remoter.shared

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.danielwaiguru.remoter.shared.utils.NavigationCommand
import com.danielwaiguru.remoter.shared.utils.SingleLiveEvent

class NavigationViewModel: ViewModel() {
    val navigationCommands = SingleLiveEvent<NavigationCommand>()
    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }
}