package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTodoViewModel : ViewModel() {

    //ViewModelで監視する項目
    var title = MutableLiveData<String>()
    var detail = MutableLiveData<String>()
}