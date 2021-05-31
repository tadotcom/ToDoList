package com.example.todolist

import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.Context
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
    var mApplication: Application ? = null
    val aa = Global()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        //フラグメント
        val addTodoFragment = AddTodoFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_add, addTodoFragment)
        fragmentTransaction.commit()
    }

    fun insertData(title: String, detail: String) {
        try {

            val dbHelper = ToDoDBHelper(aa.applicationContext, dbName, null, dbVersion)
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