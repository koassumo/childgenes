package com.igo.childgenes

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.igo.childgenes.databinding.ActivityMainBinding

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

    }
}