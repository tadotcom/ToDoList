package com.example.todolist

import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class AddTodoViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val detail = MutableLiveData<String>()

    //タスクのタイトル、タスクの詳細のバリデーション
    //EditTextが空であれば0を返す、空でなければ1を返す
    fun validateEditText(edit: String):Int {
        if (edit.isEmpty() || edit.isBlank()) {
            return 0
        } else {
            return 1
        }
    }
}