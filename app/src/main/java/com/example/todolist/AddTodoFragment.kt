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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_add_todo.*
import kotlin.random.Random

class AddTodoFragment : Fragment() {

    private val dbName: String = "ToDoListDB"
    private val tableName: String = "ToDoListTable"
    private val dbVersion: Int = 1
    var mContext: Context? = null
//    var titleEdit: EditText? = null
//    var detailEdit: EditText? = null

    private val viewModel : AddTodoViewModel by viewModels()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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
        var titleEdit = view.findViewById<EditText>(R.id.titleEdit)
        var detailEdit = view.findViewById<EditText>(R.id.detailEdit)
//        var titleEdit :EditText
//        var detailEdit :EditText


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

            //データベースに登録
            try {
                val dbHelper = ToDoDBHelper(view.context, dbName, null, dbVersion)
                val database = dbHelper.writableDatabase
                val values = ContentValues()
                values.put("title", titleEdit.text.toString())
                values.put("detail", detailEdit.text.toString())

                database.insertOrThrow(tableName, null, values)

            }catch(exception: Exception) {
                Log.e("exception", exception.toString())
            }
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }

//        val observer = Observer<String>() {
//            if (titleEdit.text.isBlank()) {
//                titleEdit.setText("タスクのタイトルを入力してください")
//            }
//
//            if (detailEdit.text.isBlank()) {
//                detailEdit.setText("タスクの詳細を入力してください")
//            }
//        }
//
//        viewModel.title.observe(viewLifecycleOwner, observer)
//        viewModel.detail.observe(viewLifecycleOwner, observer)
    }
}