package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AddRowdataListener {

    lateinit var mAdapter: CustomAdapter
    lateinit var mRowdate: ArrayList<RowData>

    private var arrayListTitle: ArrayList<String> = arrayListOf()
    private var arrayListDetail: ArrayList<String> = arrayListOf()

    var addData = RowData("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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