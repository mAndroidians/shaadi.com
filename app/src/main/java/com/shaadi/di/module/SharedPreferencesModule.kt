package com.shaadi.di.module

import android.app.Application
import com.shaadi.util.SharedPreferencesUtility
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
	@Provides
	@Singleton
	fun provideSharedPreferencesModule(application: Application): SharedPreferencesUtility {
		return SharedPreferencesUtility(application)
	}
}