package me.sartajroshan.myapp.data.repository

import me.sartajroshan.myapp.data.remote.ApiService
import me.sartajroshan.myapp.data.remote.responses.ResponseData
import me.sartajroshan.myapp.util.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun loadData(): Resource<ResponseData> {
        return try {
            val response = apiService.loadData()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error()
        }
    }

}