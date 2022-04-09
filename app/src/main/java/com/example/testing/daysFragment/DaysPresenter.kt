package com.example.testing.daysFragment

import com.example.testing.model.Model
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*

@InjectViewState
class DaysPresenter : MvpPresenter<DaysPresenterInterface>() {

    private lateinit var currentDay: DaysEnum
    private lateinit var modelsList: List<Model>


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCurrentDayOfWeek()
        viewState.updateFragmentUI(currentDay, null)
        viewState.showProgressBarView()
        val subjects: MutableList<String> = mutableListOf()

            CoroutineScope(Dispatchers.IO).launch {
                modelsList = withContext(coroutineContext){
                    Model.getLessons("11-3")
                }
                val newList = modelsList.find { model -> model.date == currentDay.current }
                if (newList != null){
                    if (newList.subjects != null) {
                        for (item in newList.subjects) {
                            subjects += item.name
                        }
                    }
                }
                withContext(Dispatchers.Main){
                    viewState.updateFragmentUI(currentDay, subjects)
                    viewState.showProgressBarView()
                }
            }
    }

    private fun getCurrentDayOfWeek(){
        currentDay = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> DaysEnum.MONDAY
            Calendar.TUESDAY -> DaysEnum.TUESDAY
            Calendar.WEDNESDAY -> DaysEnum.WEDNESDAY
            Calendar.THURSDAY -> DaysEnum.THURSDAY
            Calendar.FRIDAY -> DaysEnum.FRIDAY
            Calendar.SATURDAY -> DaysEnum.SATURDAY
            else -> {
                DaysEnum.MONDAY
            }
        }
    }

    fun changeCurrentDay(forward: Boolean) {
        currentDay = when (forward){
            true -> currentDay.moveForward(currentDay)
            else -> currentDay.moveBack(currentDay)
        }
        val newList = modelsList.find { model -> model.date == currentDay.current }
        if (newList != null){
            if (newList.subjects != null) {
                val subjects = mutableListOf<String>()
                for (item in newList.subjects) {
                    subjects += item.name
                }
                viewState.updateFragmentUI(currentDay, subjects)
            }
        }
    }
}