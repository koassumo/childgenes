package com.igo.childgenes

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.igo.childgenes.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    companion object {
        const val PARENT_PERSON = "com.igo.childgenes.ui.PARENT_PERSON"
        const val MOTHER = "Mother"
        const val FATHER = "Father"
        const val MOTHER_GRANNY = "MotherGranny"
        const val MOTHER_GRANDPA = "MotherGrandpa"
        const val FATHER_GRANNY = "FatherGranny"
        const val FATHER_GRANDPA = "FatherGrandpa"

        const val RESPOND = "com.igo.childgenes.ui.RESPOND"
        const val EYES_COLOR = "com.igo.childgenes.ui.EYES_COLOR"

        const val BROWN = "com.igo.childgenes.ui.BROWN"
        const val GREY = "com.igo.childgenes.ui.GREY"
        const val GREEN = "com.igo.childgenes.ui.GREEN"
        const val NOT_SELECTED = "com.igo.childgenes.ui.NOT_SELECTED"

        var eyesMotherColor = NOT_SELECTED
        var eyesFatherColor = NOT_SELECTED
        var eyesMotherGrannyColor = NOT_SELECTED
        var eyesMotherGrandpaColor = NOT_SELECTED
        var eyesFatherGrannyColor = NOT_SELECTED
        var eyesFatherGrandpaColor = NOT_SELECTED
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LocaleManager.setLocale(resources, "ru")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_rh
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()

        // Устанавливаем тему без ActionBar
        //setTheme(com.google.android.material.R.style.Theme_AppCompat_DayNight_NoActionBar)

        // После этой строки вызовется setContentView
        // setContentView(R.layout.activity_main)



    }
    object LocaleManager {

        fun setLocale(resources: Resources, languageCode: String) {
            val configuration = resources.configuration
            val locale = Locale(languageCode)
            Locale.setDefault(locale)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale)
            } else {
                configuration.locale = locale
            }

            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
    }
}