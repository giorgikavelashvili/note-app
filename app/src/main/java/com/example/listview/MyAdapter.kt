package com.example.listview

import android.content.Context
import android.graphics.Color
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyAdapter(private val mContext: Context, private val dataSource: ArrayList<Note>) : BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(mContext)
        val layout = layoutInflater.inflate(R.layout.main_listview_layout,parent,false)

        val nameTextView = layout.findViewById<TextView>(R.id.listViewNameView)

        nameTextView.text = "${dataSource[position].Title}"

        return layout

    }
}