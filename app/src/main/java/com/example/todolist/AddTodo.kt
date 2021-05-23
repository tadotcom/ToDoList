package com.example.todolist

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTodo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        val titleEdit: EditText = findViewById(R.id.titleEdit)
        val detailEdit: EditText = findViewById(R.id.detailEdit)


    }
}