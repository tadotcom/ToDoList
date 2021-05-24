package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AddRowdataListener {

    //リサイクルビュー
    lateinit var mAdapter: CustomAdapter
    lateinit var mRowdate: ArrayList<RowData>

    //データベース
    private val dbName: String = "ToDoListDB"
    private val dbVersion: Int = 1

    //行データ
    private var arrayListTitle: ArrayList<String> = arrayListOf()
    private var arrayListDetail: ArrayList<String> = arrayListOf()

    var addData = RowData("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DBにアクセス
        selectData()

        //データの有無のチェックを行う
        if (arrayListTitle.isEmpty()) {

        } else {
            // RecyclerViewの取得
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

            // LayoutManagerの設定
            recyclerView.layoutManager = LinearLayoutManager(this)

            mRowdate = arrayListOf(addData)
            mRowdate.clear()
            for (i in 0..arrayListTitle.size - 1) {
                addData = RowData(arrayListTitle.get(i), arrayListDetail.get(i))
                mRowdate.add(addData)

            }

            // CustomAdapterの生成と設定
            mAdapter = CustomAdapter(mRowdate, this)

            recyclerView.adapter = mAdapter

        }
    }

    private fun selectData() {
        try {
            arrayListTitle.clear();arrayListDetail.clear();

            val dbHelper = ToDoDBHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select name, type from SampleTable"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    //arrayListId.add(cursor.getString(0))
                    arrayListTitle.add(cursor.getString(0))
                    arrayListDetail.add(cursor.getString(1))
                    cursor.moveToNext()
                }
            }
        }catch(exception: Exception) {
        }
    }

    /**
     * ボタンタップイベント
     */
    override fun buttonTapped(row: RowData) {
        val intent = Intent(this, DetailTodo::class.java)
        intent.putExtra("VALUE", row.title + "-" + row.detail)
        startActivity(intent)
    }

    /**
     * ToolBarのメニューを初期化するためのメソッド
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.toolbar_option_buttons, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * ToolBarのメニューのクリックイベントを初期化するためのメソッド
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, AddTodo::class.java)
                intent.putExtra("VALUE", "")
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}