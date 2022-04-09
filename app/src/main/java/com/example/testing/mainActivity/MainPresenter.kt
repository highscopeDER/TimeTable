package com.example.testing.mainActivity

import com.example.testing.model.Grades
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainPresenterInterface>() {

    private var grade: String = "11-1"

    fun setGrade(value: String){
        grade = when(value){
            "11 Информационно-Математический" -> Grades.inf_math_11.value
            "11 Физико-Математический" -> Grades.phys_math_11.value
            "11 Физико-Химический" -> Grades.phys_chem_11.value
            else -> grade
        }
    }

}