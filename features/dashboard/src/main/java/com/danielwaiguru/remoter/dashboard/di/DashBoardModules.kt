package com.danielwaiguru.remoter.dashboard.di

import com.danielwaiguru.remoter.core.di.coreLibModules
import com.danielwaiguru.remoter.dashboard.presentation.views.DashBoardViewModel
import org.koin.dsl.module

val viewModelModules = module {
    single { DashBoardViewModel(get()) }
}
val appModules = listOf(viewModelModules) + coreLibModules