package com.example.todolist

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailTodo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_todo)

        val titleTextView: TextView = findViewById(R.id.title)
        val detailTextView: TextView = findViewById(R.id.detail)
    }
}