package com.example.testing.daysFragment

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface DaysPresenterInterface : MvpView {
    fun updateFragmentUI(day: DaysEnum, newLessonsList: List<String>?)
    fun showError(errorMessage: String)
    fun showProgressBarView()
}