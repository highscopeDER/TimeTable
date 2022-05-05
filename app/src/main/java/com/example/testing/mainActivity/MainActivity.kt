package com.example.testing.mainActivity

import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testing.R
import com.example.testing.daysFragment.Days
import com.example.testing.daysFragment.DaysPresenterInterface
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

var GRADE = "11-3"

class MainActivity : MvpAppCompatActivity(), MainPresenterInterface {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private  val SAVED_THEME = "darkTheme"
    private val PREFS_NAME = "prefs"
    private val THEME_LIGHT = 0
    private val GRADES_NAME = "grades"
    private val THEME_DARK = 1
    private lateinit var menuButton: ImageButton
    private lateinit var themeSwitcherButton: ToggleButton
    private lateinit var daysFragment: DaysPresenterInterface
    private lateinit var fragmentManager: FragmentManager
    private val prefs by lazy { getSharedPreferences(PREFS_NAME, MODE_PRIVATE) }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        initTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        themeSwitcherButton = findViewById(R.id.themeSwitch)
        menuButton = findViewById(R.id.menuButton)
        fragmentManager = supportFragmentManager
        daysFragment = Days()
        fragmentManager.beginTransaction().replace(R.id.daysFrame, daysFragment as Fragment).commit()
        initThemeListener()
        initMenu()
    }

    private fun initMenu(){
        val bottomSheetMenuDialog = MenuBottomSheet(this)
        bottomSheetMenuDialog.setOnDismissListener {
                daysFragment = Days()
                fragmentManager.beginTransaction().replace(R.id.daysFrame, daysFragment as Fragment).commit()
        }
        menuButton.setOnClickListener{  //bind button that opens menu (bottomSheetDialog)
            bottomSheetMenuDialog.show()
        }

    }

    private fun initThemeListener(){
        when(AppCompatDelegate.getDefaultNightMode()){
            AppCompatDelegate.MODE_NIGHT_NO -> themeSwitcherButton.isChecked = false
            AppCompatDelegate.MODE_NIGHT_YES -> themeSwitcherButton.isChecked = true
        }
        themeSwitcherButton.setOnClickListener {
            when(themeSwitcherButton.isChecked){
                false -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                true -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
            }
        }
    }

    private fun initTheme(){
        when(getSavedTheme()){
            THEME_LIGHT -> {
                setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
            }
            THEME_DARK -> {
                setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
            }
        }
    }

    private fun setTheme(themeMode: Int, prefsMode: Int){
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    private fun getSavedTheme() = prefs.getInt(SAVED_THEME, THEME_LIGHT)

    private fun saveTheme(theme: Int) = prefs.edit().putInt(SAVED_THEME, theme).apply()

    private fun getGrade(): String? = prefs.getString(GRADES_NAME, "11-3")

}