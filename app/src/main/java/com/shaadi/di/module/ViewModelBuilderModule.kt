package com.shaadi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shaadi.di.ShaadiViewModelFactory
import com.shaadi.di.ViewModelKey
import com.shaadi.ui.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindRegisterOneViewModel(viewModel: HomeViewModel): ViewModel



    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ShaadiViewModelFactory): ViewModelProvider.Factory


}