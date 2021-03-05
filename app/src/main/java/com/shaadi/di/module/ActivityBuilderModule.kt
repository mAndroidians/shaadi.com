package com.shaadi.di.module


import com.shaadi.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

	@ContributesAndroidInjector
	internal abstract fun contributeMainActivity(): MainActivity

}