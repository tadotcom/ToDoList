package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailTodo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_todo)

        val titleTextView: TextView = findViewById(R.id.title)
        val detailTextView: TextView = findViewById(R.id.detail)

        //キャンセルボタン押下
        val cancelButton: Button = findViewById(R.id.cancelBtn)
        cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }

        //削除ボタン押下
        val deleteButton: Button = findViewById(R.id.deleteBtn)
        deleteButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }
}