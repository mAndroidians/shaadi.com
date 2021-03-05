package conference.yuktan.app.di.module

import com.shaadi.data.ApiService
import com.shaadi.di.module.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiServiceModule {

	@Provides
	@Singleton
	fun provideRetrofitApiService(retrofit: Retrofit): ApiService {
		return retrofit.create(ApiService::class.java)
	}


}