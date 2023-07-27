package me.sartajroshan.myapp.data.remote

import me.sartajroshan.myapp.data.remote.requests.RequestLogin
import me.sartajroshan.myapp.data.remote.requests.RequestVerfiyOtp
import me.sartajroshan.myapp.data.remote.responses.ResponseData
import me.sartajroshan.myapp.data.remote.responses.ResponseLogin
import me.sartajroshan.myapp.data.remote.responses.ResponseOtp
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("users/phone_number_login")
    suspend fun login(@Body requestLogin :RequestLogin) : ResponseLogin

    @POST("users/verify_otp")
    suspend fun verifyOtp(@Body requestOtp :RequestVerfiyOtp) : ResponseOtp

    @GET("users/test_profile_list")
    suspend fun loadData() : ResponseData


}