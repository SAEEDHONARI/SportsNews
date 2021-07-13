package com.example.baloot_saeedhonari.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baloot_saeedhonari.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        backGroundColor()
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(articleNavHostFragment.findNavController())


    }

    fun backGroundColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           //  window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
           // window.navigationBarColor = ContextCompat.getColor(this, R.color.action_bar_color)

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val background = this.resources.getDrawable(R.drawable.bg_action)
            window.statusBarColor = this.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = this.resources.getColor(android.R.color.black)
            window.setBackgroundDrawable(background)
           //  window.navigationBarColor = ContextCompat.getColor(this, R.color.action_bar_color)



        }
    }

}