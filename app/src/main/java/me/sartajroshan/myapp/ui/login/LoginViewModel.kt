package me.sartajroshan.myapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.sartajroshan.myapp.data.repository.AuthRepository
import me.sartajroshan.myapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

     val loading = MutableLiveData<Boolean>()
     val errorMessage = MutableLiveData<String>()
     val navigateToNextScreen = MutableLiveData<String>()

    fun login( code:String, mobile:String) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val result = repository.login(code+mobile)
            loading.postValue(false)
            when(result) {
                is  Resource.Success -> {
                    if (result.data == true) {
                        navigateToNextScreen.postValue(code+mobile)
                    } else {
                        errorMessage.postValue("Invalid Mobile")
                    }
                }

                is Resource.Error -> {
                    errorMessage.postValue(result.message)
                }

                else -> {}
            }
        }
    }

    fun verifyOtp(mobile: String, otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val result = repository.verifyOtp(mobile, otp)
            loading.postValue(false)
            when(result) {
                is  Resource.Success -> {
                    if (result.data != null) {
                        repository.saveToken(result.data!!)
                        navigateToNextScreen.postValue("")
                    } else {
                        errorMessage.postValue("Invalid Otp")
                    }
                }

                is Resource.Error -> {
                    errorMessage.postValue(result.message)
                }

                else -> {}
            }
        }
    }
}