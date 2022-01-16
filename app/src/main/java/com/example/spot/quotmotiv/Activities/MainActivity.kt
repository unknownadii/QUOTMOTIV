package com.example.spot.quotmotiv.Activities

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spot.quotmotiv.DataBase.QuotesFavDataBase
import com.example.spot.quotmotiv.R

class MainActivity : AppCompatActivity() {

    lateinit var dataBase: QuotesFavDataBase

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
      //  val navHome = findViewById<BottomNavigationItemView>(R.id.navigation_home)
       // val navFavorite = findViewById<BottomNavigationItemView>(R.id.navigation_favorite)
       // val navAdd = findViewById<BottomNavigationItemView>(R.id.navigation_add)

        val splahScreen = findViewById<LinearLayout>(R.id.ll_splash)
        val homeScreen = findViewById<FrameLayout>(R.id.fl_main_home)
        Handler().postDelayed({
            splahScreen.visibility=View.GONE
            homeScreen.visibility=View.VISIBLE
        }, 2500)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

    }
}