package com.example.testing.daysFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R

class LessonsListRecyclerViewAdapter : RecyclerView.Adapter<LessonsListRecyclerViewAdapter.ViewHolder>() {

    var lessonsList: List<String> = listOf()

    private val timeList: List<Pair<String, String>> = listOf(
        "8:30" to "9:10",
        "9:20" to "10:00",
        "10:10" to "10:50",
        "11:05" to "11:45",
        "12:05" to "12:45",
        "13:25" to "14:05",
        "14:15" to "14:55",
        "15:00" to "15:40",
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val timeStartTextView: TextView = view.findViewById(R.id.timeStart)
        val timeEndTextView: TextView = view.findViewById(R.id.timeEnd)
        val lessonTextView: TextView = view.findViewById(R.id.lesson)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timeStartTextView.text = timeList[position].first
        holder.timeEndTextView.text = timeList[position].second
        holder.lessonTextView.text = lessonsList[position]
    }

    fun changeLessons(newList: List<String>){
        lessonsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lessonsList.size

}