package me.sartajroshan.myapp.data.remote

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request()
        val authRequest = response.newBuilder()
            .addHeader("Authorization", preferences.getString("token", "")!!)
            .build()
        return chain.proceed(authRequest)
    }
}