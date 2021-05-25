package com.example.todolist

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AddTodo : AppCompatActivity() {

    private val dbName: String = "ToDoListDB"
    private val tableName: String = "ToDoListTable"
    private val dbVersion: Int = 1

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
            insertData(titleEdit.text.toString(), detailEdit.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }

    private fun insertData(title: String, detail: String) {
        try {
            val dbHelper = ToDoDBHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.writableDatabase

            val randam = Random(5).toString()

            val values = ContentValues()
            //values.put("id", randam)
            values.put("title", title)
            values.put("detail", detail)

            database.insertOrThrow(tableName, null, values)

        }catch(exception: Exception) {
            Log.e("exception", exception.toString())
        }
    }
}