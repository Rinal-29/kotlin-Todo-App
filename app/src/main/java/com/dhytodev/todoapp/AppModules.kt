package com.dhytodev.todoapp

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskModule = module {
    single { TaskDatabase.getInstance(androidContext()) }
    viewModel { TaskViewModel(get()) }
}