package ma.sample.usertasks.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ma.sample.usertasks.data.api.UserTaskApi
import ma.sample.usertasks.data.api.UserTaskDataSource
import ma.sample.usertasks.data.db.AppDatabase
import ma.sample.usertasks.data.db.dao.TaskDao
import ma.sample.usertasks.data.db.dao.UserDao
import ma.sample.usertasks.data.repository.UserTasksRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Elmehdi Mellouk on 10/15/20.
 * elmehdi.mellouk@xpi.com
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserTaskApi(retrofit: Retrofit): UserTaskApi = retrofit.create(UserTaskApi::class.java)

    @Singleton
    @Provides
    fun provideUserTaskDataSource(userTaskApi: UserTaskApi) = UserTaskDataSource(userTaskApi)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideTaskDao(db: AppDatabase) = db.taskDao()

    @Singleton
    @Provides
    fun provideRepository(userTaskDataSource: UserTaskDataSource,
                          userDao: UserDao,
                          taskDao: TaskDao
    ) = UserTasksRepository(userTaskDataSource, userDao,taskDao)
}