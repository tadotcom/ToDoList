package com.example.todolist

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.random.Random

class AddTodoFragment : Fragment() {

    private val dbName: String = "ToDoListDB"
    private val tableName: String = "ToDoListTable"
    private val dbVersion: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //AddTodoを生成、DBへのアクセスはAddTodoで行う
        val mAddTodo = AddTodo()

        val titleEdit = view.findViewById<EditText>(R.id.titleEdit)
        val detailEdit = view.findViewById<EditText>(R.id.detailEdit)

        //キャンセルボタン押下
        val cancelButton = view.findViewById<Button>(R.id.cancelBtn)
        cancelButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }

        //リストに登録するボタンを押下
        val registerButton = view.findViewById<Button>(R.id.registerBtn)
        registerButton.setOnClickListener {
            mAddTodo.insertData(titleEdit.text.toString(), detailEdit.text.toString())
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }
}