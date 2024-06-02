package com.example.kotlinassignment.ui.main.view.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinassignment.R
import com.example.kotlinassignment.ui.main.view.fragments.ListDataViewFragment
import com.example.kotlinassignment.utils.LiveNetworkChecker


class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LiveNetworkChecker.init(application)
        val listDataViewFragment = ListDataViewFragment()
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(android.R.id.content, listDataViewFragment).commitNow()
        }
    }

    override fun onBackPressed() {
        // Back press operations and double press back to exit functionality
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press back again to exit from app.", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        )
    }

}