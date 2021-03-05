package com.shaadi.di.module


import com.shaadi.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

	/**
	 * Common flow fragment list
	 */


	@ContributesAndroidInjector
	abstract fun contributeLoginFragment(): HomeFragment

}