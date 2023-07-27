package me.sartajroshan.myapp.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.sartajroshan.myapp.data.remote.responses.ResponseData
import me.sartajroshan.myapp.data.repository.MainRepository
import me.sartajroshan.myapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<ResponseData>()
    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
        loading.postValue(true)
            val result = repository.loadData()
            loading.postValue(false)
            when(result) {
                is Resource.Success -> {
                   if(result.data != null) {
                       data.postValue(result.data!!)
                   }
                }
                is Resource.Error -> {
                    errorMessage.postValue(result.message)
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}