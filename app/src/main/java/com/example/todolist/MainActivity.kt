package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: CustomAdapter
    lateinit var mRowdate: ArrayList<RowData>
    var addData = RowData("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerViewの取得
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

        // データの作成
        val data1 = RowData("1つめのたくす", "あああああ")
//        val data2 = RowData("2つめのたくす", "あああああ")
//        val data3 = RowData("3つめのたくす", "あああああ")
        mRowdate = arrayListOf(data1)

        // CustomAdapterの生成と設定
        mAdapter = CustomAdapter(mRowdate)
        recyclerView.adapter = mAdapter
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