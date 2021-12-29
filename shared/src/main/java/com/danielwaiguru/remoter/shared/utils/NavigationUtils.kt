package com.danielwaiguru.remoter.shared.utils

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()
    data class BackTo(val directionsId: Int): NavigationCommand()
    object ToRoot: NavigationCommand()
}

