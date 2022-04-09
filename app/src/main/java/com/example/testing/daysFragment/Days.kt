package com.example.testing.daysFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.mainActivity.OnSwipeTouchListener
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class Days : MvpAppCompatFragment(), DaysPresenterInterface {
    @InjectPresenter
    lateinit var presenter: DaysPresenter
    private lateinit var previousDayTextView: TextView
    private lateinit var currentDayTextView: TextView
    private lateinit var nextDayTextView: TextView
    lateinit var progressBarView: ProgressBar
    private lateinit var lessonsListRecyclerView: RecyclerView
    private val lessonsListRecyclerViewAdapter = LessonsListRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_days, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousDayTextView = view.findViewById(R.id.previosDay)
        currentDayTextView = view.findViewById(R.id.currentDay)
        nextDayTextView = view.findViewById(R.id.nextDay)
        progressBarView = view.findViewById(R.id.progressBar)
        lessonsListRecyclerView = view.findViewById(R.id.lessonsListRecyclerView)
        lessonsListRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        lessonsListRecyclerView.adapter = lessonsListRecyclerViewAdapter
        previousDayTextView.setOnClickListener {  presenter.changeCurrentDay(false) }
        nextDayTextView.setOnClickListener {  presenter.changeCurrentDay(true) }

        lessonsListRecyclerView.setOnTouchListener(object: OnSwipeTouchListener() {
            override fun onSwipeRight() {
                super.onSwipeRight()
                previousDayTextView.callOnClick()
            }
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                nextDayTextView.callOnClick()
            }
        })
    }

    override fun showProgressBarView() {
        progressBarView.visibility = when (progressBarView.visibility) {
            View.VISIBLE -> View.GONE
            View.GONE -> View.VISIBLE
            else -> View.GONE
        }
    }

    override fun updateFragmentUI(
        day: DaysEnum,
        newLessonsList: List<String>?
    ) {
        currentDayTextView.text = day.current
        previousDayTextView.text = day.previous
        nextDayTextView.text = day.next
        if (newLessonsList != null) {
            lessonsListRecyclerViewAdapter.changeLessons(newLessonsList)
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}