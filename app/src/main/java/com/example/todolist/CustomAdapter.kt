package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface AddRowdataListener {
    fun buttonTapped(animal: RowData)
}

class CustomAdapter (private val rowDataList: ArrayList<RowData>, private val listener: AddRowdataListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val detail: TextView
        val transferDetail: Button


        init {
            title = view.findViewById(R.id.title)
            detail = view.findViewById(R.id.detail)
            transferDetail = view.findViewById(R.id.transferDetail)
        }
    }

    // レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val mRowData = rowDataList[position]

        viewHolder.title.text = mRowData.title
        viewHolder.detail.text = mRowData.detail

        viewHolder.title.setOnClickListener {
            listener.buttonTapped(mRowData)
        }
    }

    // 表示数を返す
    override fun getItemCount() = rowDataList.size
}