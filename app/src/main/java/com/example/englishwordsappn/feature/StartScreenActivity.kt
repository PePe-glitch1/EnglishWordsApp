package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsappn.databinding.ActivityStartScreenBinding

class StartScreenActivity : AppCompatActivity() {

    private var _binding: ActivityStartScreenBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToStartLearnActivity()
        goToStatisticActivity()
        goToSettingsActivity()
    }


    private fun goToStartLearnActivity() {
        binding.btStartLearn.setOnClickListener {
            val intent = Intent(this, LearnWordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToStatisticActivity() {
        binding.btProgressLearn.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToSettingsActivity() {
        binding.btSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}