package com.example.todolist

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter {

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
}