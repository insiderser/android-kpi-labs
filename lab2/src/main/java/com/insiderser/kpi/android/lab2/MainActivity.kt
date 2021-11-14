package com.insiderser.kpi.android.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.insiderser.kpi.android.lab2.databinding.MainActivityBinding

class MainActivity : AppCompatActivity(), NavController {

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFirstScreen()
        }
    }

    private fun openFirstScreen() {
        supportFragmentManager.commit {
            add(binding.fragmentContainer.id, MainFragment::class.java, null)
        }
    }

    override fun openSuccess(text: String, color: Int) {
        val args = SuccessFragment.createArgs(text, color)
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, SuccessFragment::class.java, args)
            addToBackStack(null)
        }
    }

    override fun navigateBack() {
        onBackPressed()
    }
}
