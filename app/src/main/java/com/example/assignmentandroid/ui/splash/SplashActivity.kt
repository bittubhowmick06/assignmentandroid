package com.example.assignmentandroid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentandroid.R
import com.example.assignmentandroid.ui.mainlist.MainListActivity
import java.util.*
import kotlin.concurrent.timerTask
import androidx.activity.*
import com.example.assignmentandroid.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeIntent()
    }

    private fun changeIntent(){
        val timeTasks = timerTask {
            val intent = Intent(this@SplashActivity, MainListActivity::class.java)
            startActivity(intent)
            finish()
        }
        val timer = Timer()
        timer.schedule(timeTasks,   5000)
    }
}