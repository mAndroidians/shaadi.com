package conference.yuktan.app.di.module

import android.app.Application
import androidx.room.Room
import com.shaadi.data.UserDatabase
import com.shaadi.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

	@Singleton
	@Provides
	fun provideSmartCareDatabase(application: Application): UserDatabase =
		Room.databaseBuilder(application, UserDatabase::class.java, "user_database.db")
			.fallbackToDestructiveMigration()
			.build()

	@Singleton
	@Provides
	fun provideUserProfileDao(database: UserDatabase): UserDao =
		database.janMantraTvNewsDao()




}
