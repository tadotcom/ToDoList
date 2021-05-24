package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTodo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        val titleEdit: EditText = findViewById(R.id.titleEdit)
        val detailEdit: EditText = findViewById(R.id.detailEdit)

        //キャンセルボタン押下
        val cancelButton: Button = findViewById(R.id.cancelBtn)
        cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }

        //リストに登録するボタンを押下
        val registerButton: Button = findViewById(R.id.registerBtn)
        registerButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }
}