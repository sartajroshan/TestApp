package me.sartajroshan.myapp.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import me.sartajroshan.myapp.data.remote.ApiService
import me.sartajroshan.myapp.data.remote.requests.RequestLogin
import me.sartajroshan.myapp.data.remote.requests.RequestVerfiyOtp
import me.sartajroshan.myapp.util.Resource
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService,
private val preferences: SharedPreferences
                                         ) {

    suspend fun login(phoneNumber: String): Resource<Boolean> {
        return try {
            val response = apiService.login(RequestLogin(phoneNumber))
            Resource.Success(response.status)
        } catch (e: Exception) {
            Resource.Error()
        }
    }

    suspend fun verifyOtp(phoneNumber: String, otp: String): Resource<String> {
        return try {
            val response = apiService.verifyOtp(RequestVerfiyOtp(phoneNumber, otp))
            if (response.token != null) {
                Resource.Success(response.token)
            } else {
                Resource.Error(message = "Wrong Otp")
            }
        } catch (e: Exception) {
            Resource.Error()
        }
    }

    fun saveToken(token:String) {
        preferences.edit {
            putString("token", token)
        }
    }

}