package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsappn.databinding.ActivityStartScreenBinding

class StartScreenActivity : AppCompatActivity() {
    private var _binding: ActivityStartScreenBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStartLearn.setOnClickListener {
            startActivity(Intent(this, LearnWordActivity::class.java))
        }
        binding.btProgressLearn.setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
        }
        binding.btSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onDestroy() { super.onDestroy(); _binding = null }
}
