package com.insiderser.kpi.android.lab3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.insiderser.kpi.android.lab2.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
