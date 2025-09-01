package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.englishwordsappn.databinding.ActivityStatisticsBinding
import com.example.englishwordsappn.domain.model.WordDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatisticsActivity : AppCompatActivity() {
    private var _binding: ActivityStatisticsBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateStats()
        binding.ibTurnOff.setOnClickListener {
            startActivity(Intent(this, StartScreenActivity::class.java))
            finish()
        }
    }

    override fun onStart() { super.onStart(); updateStats() }

    override fun onDestroy() { super.onDestroy(); _binding = null }

    private fun updateStats() {
        val dao = WordDatabase.getDatabase(this).wordDao()
        lifecycleScope.launch {
            val (learned, notLearned) = withContext(Dispatchers.IO) {
                dao.learnedCount() to dao.notLearnedCount()
            }
            binding.tvStatisticLearned.text = learned.toString()
            binding.tvStatisticAnLearned.text = notLearned.toString()
        }
    }
}
