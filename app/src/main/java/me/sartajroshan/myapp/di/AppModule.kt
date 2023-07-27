package me.sartajroshan.myapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.SharedPreferencesCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.sartajroshan.myapp.BuildConfig
import me.sartajroshan.myapp.data.remote.ApiService
import me.sartajroshan.myapp.data.remote.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("my_pref", MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideBasicAuth(preferences: SharedPreferences) = AuthInterceptor(preferences)


    @Provides
    @Singleton
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): okhttp3.Call.Factory {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(authInterceptor)
                if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        callFactory: okhttp3.Call.Factory
    ): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(callFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}