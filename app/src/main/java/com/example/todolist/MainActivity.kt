package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}