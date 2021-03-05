package com.shaadi.di.component

import android.app.Application
import com.shaadi.MyApplication
import com.shaadi.di.module.*
import conference.yuktan.app.di.module.ApiServiceModule
import conference.yuktan.app.di.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
	modules = [
		AndroidSupportInjectionModule::class,
		ActivityBuilderModule::class,
		ApiServiceModule::class,
		FragmentBuilderModule::class,
		NetworkModule::class,
		DatabaseModule::class,
		ViewModelBuilderModule::class,
		SharedPreferencesModule::class
	]
)
interface AppComponent : AndroidInjector<MyApplication> {
	@Component.Builder
	interface Builder {

		@BindsInstance
		fun create(application: Application): AppComponent.Builder

		fun build(): AppComponent
	}
}