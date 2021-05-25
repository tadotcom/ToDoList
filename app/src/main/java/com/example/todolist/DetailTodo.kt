package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailTodo : AppCompatActivity() {

    private val dbName: String = "ToDoListDB"
    private val tableName: String = "ToDoListTable"
    private val dbVersion: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_todo)

        //行データの詳細
        val detailRowData = intent.getStringExtra("VALUE")

        val titleTextView: TextView = findViewById(R.id.title)
        val detailTextView: TextView = findViewById(R.id.detail)

        //タスクのタイトル + タスクの詳細
        //splitTitle + splitDetail
        var split: Int = detailRowData.indexOf("-")
        var splitLength: Int = detailRowData.length

        var splitTitle: String = detailRowData.substring(0, split)
        var splitDetail: String = detailRowData.substring(split + 1, splitLength)

        titleTextView.setText(splitTitle)
        detailTextView.setText(splitDetail)

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
            deleteData(splitDetail)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }

    private fun deleteData(whereId: String) {
        try {
            val dbHelper = ToDoDBHelper(applicationContext, dbName, null, dbVersion);
            val database = dbHelper.writableDatabase

            val whereClauses = "detail = ?"
            val whereArgs = arrayOf(whereId)
            database.delete(tableName, whereClauses, whereArgs)
        }catch(exception: Exception) {
            Log.e("deleteData", exception.toString())
        }
    }
}