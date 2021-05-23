package com.example.todolist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoDBHelper(context: Context, databaseName:String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, databaseName, factory, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL("create table if not exists TodoTable (id int primary key autoincrement, title text, detail text)");
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            database?.execSQL("alter table TodoTable add column deleteFlag integer default 0")
        }
    }
}