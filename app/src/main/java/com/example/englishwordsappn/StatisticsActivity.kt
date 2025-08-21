package com.example.englishwordsappn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishwordsappn.databinding.ActivityStartScreenBinding
import com.example.englishwordsappn.databinding.ActivityStatisticsBinding
import java.lang.Character.toString

class StatisticsActivity : AppCompatActivity() {
        private var _binding: ActivityStatisticsBinding? = null
        val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            _binding = ActivityStatisticsBinding.inflate(layoutInflater)
            setContentView(binding.root)

            updateStats()
            returnToStartScreen()
    }

    override fun onStart() {
        super.onStart()
        updateStats()
    }

    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            var intent = android.content.Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateStats() {
        binding.tvStatisticLearned.text = LearningWords.learnedCount().toString()
        binding.tvStatisticAnLearned.text = LearningWords.notLearnedCount().toString()
    }
}