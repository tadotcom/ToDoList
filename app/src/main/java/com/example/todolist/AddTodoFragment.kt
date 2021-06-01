package com.example.todolist

import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentAddTodoBinding
import kotlinx.android.synthetic.main.fragment_add_todo.*
import kotlin.concurrent.thread
import kotlin.random.Random

class AddTodoFragment : Fragment() {

    private val dbName: String = "ToDoListDB"
    private val tableName: String = "ToDoListTable"
    private val dbVersion: Int = 1
    var mContext: Context? = null
    var titleEdit: EditText? = null
    var titleValidate: Int? = null
    var detailValidate: Int? = null

    //ViewModelのインスタンスを生成
    private val viewModel: AddTodoViewModel by viewModels()

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

        var titleEdit = view.findViewById<EditText>(R.id.titleEdit)
        var detailEdit = view.findViewById<EditText>(R.id.detailEdit)

        //タイトルのEditTextに”必須項目です”と表示されているときにフォーカスを当てると削除する
        titleEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (titleEdit.text.toString() == "必須項目です") {
                    titleEdit.setText("")
                    Log.d("DEBUG", titleEdit.text.toString())
                }
            }
        }

        //詳細のEditTextに”必須項目です”と表示されているときにフォーカスを当てると削除する
        detailEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (detailEdit.text.toString() == "必須項目です") {
                    detailEdit.setText("")
                    Log.d("DEBUG", detailEdit.text.toString())
                }
            }
        }

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

            titleValidate = viewModel.validateEditText(titleEdit.text.toString())
            detailValidate = viewModel.validateEditText(detailEdit.text.toString())

            //タスクのタイトルが空のとき
            if (titleValidate == 0) {
                titleEdit.setText("必須項目です")
                titleEdit.setTextColor(Color.parseColor("red"))
                return@setOnClickListener
            }

            //タスクの詳細が空のとき
            if (detailValidate == 0) {
                detailEdit.setText("必須項目です")
                detailEdit.setTextColor(Color.parseColor("red"))
                return@setOnClickListener
            }

            //データベースに登録
            try {
                val dbHelper = ToDoDBHelper(view.context, dbName, null, dbVersion)
                val database = dbHelper.writableDatabase
                val values = ContentValues()
                values.put("title", titleEdit.text.toString())
                values.put("detail", detailEdit.text.toString())

                database.insertOrThrow(tableName, null, values)

            } catch (exception: Exception) {
                Log.e("exception", exception.toString())
            }
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("VALUE", "")
            startActivity(intent)
        }
    }
}