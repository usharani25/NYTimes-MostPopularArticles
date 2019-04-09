package com.demo.nytimes.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.nytimes.R

class AritcleViewHolder(itemView1: View) : RecyclerView.ViewHolder(itemView1) {

    val iv_rounded: ImageView = itemView1.findViewById(R.id.iv_rounded)
    val tv_title: TextView = itemView1.findViewById(R.id.tv_title)
    val tv_reporter: TextView = itemView1.findViewById(R.id.tv_reporter)
    val iv_calendar: ImageView = itemView1.findViewById(R.id.iv_calendar)
    val iv_next: ImageView = itemView1.findViewById(R.id.iv_next)
    val tv_date: TextView = itemView1.findViewById(R.id.tv_date)
}