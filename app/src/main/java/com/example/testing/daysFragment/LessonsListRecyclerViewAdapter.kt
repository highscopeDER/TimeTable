package com.example.testing.daysFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.model.TimeList

class LessonsListRecyclerViewAdapter : RecyclerView.Adapter<LessonsListRecyclerViewAdapter.ViewHolder>() {

    var lessonsList: List<String> = listOf()

    private var timeList: List<Pair<String, String>> = listOf()

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

    fun changeLessons(newList: List<String>, day: DaysEnum){
        lessonsList = newList
        timeList = when(day){
            DaysEnum.SATURDAY -> TimeList.SATURDAY.values
            else -> TimeList.MAIN.values
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lessonsList.size

}