package com.example.testing.mainActivity

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.testing.R
import com.example.testing.model.Grades
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheet(context: Context) : BottomSheetDialog(context) {

    private lateinit var spinner: Spinner
    private lateinit var saveButton: Button
    private lateinit var content: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setCancelable(false)
        content = View.inflate(context, R.layout.bottom_sheet_menu, null)
        super.setContentView(content, ViewGroup.LayoutParams(MATCH_PARENT, 600))
        spinner = content.findViewById(R.id.gradePicker)
        saveButton = content.findViewById(R.id.saveButton)
        ArrayAdapter.createFromResource(context, R.array.grades, android.R.layout.simple_spinner_item).also {
                arrayAdapter ->  spinner.adapter = arrayAdapter
        }
        saveButton.setOnClickListener {
            val grade: Grades = when(spinner.selectedItem){
                "11 Информационно-Математический" -> Grades.inf_math_11
                "11 Физико-Математический" -> Grades.phys_math_11
                "11 Физико-Химический" -> Grades.phys_chem_11
                else -> Grades.inf_math_11
            }
            context.getSharedPreferences("prefs", MODE_PRIVATE)
                .edit()
                .putString("grade", grade.value)
                .apply()

            GRADE = grade.value

            dismiss()
        }
    }
    companion object {
        val TAG = "BOTTOMSHEETDIALOG"
    }
}
