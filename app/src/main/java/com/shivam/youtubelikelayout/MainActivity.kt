package com.shivam.youtubelikelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.shivam.youtubelikelayout.databinding.ActivityMainBinding
import com.shivam.youtubelikelayout.ui.HomeFragment
import com.shivam.youtubelikelayout.ui.VideoFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navigationController = Navigation.findNavController(this,R.id.frag_container)
        binding.bottomNavigation.setupWithNavController(navigationController)

    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentByTag("Video").also {
            if (it == null){
                super.onBackPressed()
                return
            }
            else{
                it as VideoFragment
                val motionLayout = it._binding.mainVideoMotionLayout
                if (motionLayout.currentState == R.id.end){
                    super.onBackPressed()
                }
                else{
                    motionLayout.transitionToState(R.id.end)
                }
            }
        }
    }

}