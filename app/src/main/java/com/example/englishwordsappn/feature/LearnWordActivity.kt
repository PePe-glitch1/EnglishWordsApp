package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.englishwordsappn.R
import com.example.englishwordsappn.data.Word
import com.example.englishwordsappn.databinding.ActivityLearnWordBinding
import com.example.englishwordsappn.domain.model.Question
import com.example.englishwordsappn.domain.model.WordDatabase
import com.example.englishwordsappn.domain.trainer.LearningWords
import com.example.englishwordsappn.domain.trainer.NUMBER_OF_ANSWERS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LearnWordActivity : AppCompatActivity() {
    private var _binding: ActivityLearnWordBinding? = null
    val binding get() = _binding ?: error("Binding is not initialized")

    private lateinit var trainer: LearningWords

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = WordDatabase.getDatabase(this).wordDao()
        trainer = LearningWords(dao)

        binding.ibTurnOff.setOnClickListener {
            startActivity(Intent(this, StartScreenActivity::class.java))
            finish()
        }

        with(binding) {
            btFinalContinue.setOnClickListener {
                clFinalScoreboard.isVisible = false
                btSlip.isVisible = true
                markAnswerNeutral(llAnswer1, tvVariantValue1, tvVariantNumb1, clFinalScoreboard, btSlip)
                markAnswerNeutral(llAnswer2, tvVariantValue2, tvVariantNumb2, clFinalScoreboard, btSlip)
                markAnswerNeutral(llAnswer3, tvVariantValue3, tvVariantNumb3, clFinalScoreboard, btSlip)
                markAnswerNeutral(llAnswer4, tvVariantValue4, tvVariantNumb4, clFinalScoreboard, btSlip)
                showNextQuestion()
            }
            btSlip.setOnClickListener { showNextQuestion() }
        }

        showNextQuestion()
    }

    override fun onDestroy() { super.onDestroy(); _binding = null }

    private fun setOnClickListenerOff() = with(binding) {
        llAnswer1.setOnClickListener(null)
        llAnswer2.setOnClickListener(null)
        llAnswer3.setOnClickListener(null)
        llAnswer4.setOnClickListener(null)
    }

    private fun showNextQuestion() {
        lifecycleScope.launch {
            val q: Question? = withContext(Dispatchers.IO) { trainer.getNextQuestion() }

            with(binding) {
                if (q == null || q.variants.size < NUMBER_OF_ANSWERS) {
                    tvQuestionWord.isVisible = false
                    layoutVariants.isVisible = false
                    btSlip.text = getString(R.string.text_correct)
                    return@with
                }

                btSlip.isVisible = true
                tvQuestionWord.isVisible = true

                tvQuestionWord.text = q.correctAnswer.english
                tvVariantValue1.text = q.variants[0].translation
                tvVariantValue2.text = q.variants[1].translation
                tvVariantValue3.text = q.variants[2].translation
                tvVariantValue4.text = q.variants[3].translation


                llAnswer1.setOnClickListener { onAnswerClick(0) }
                llAnswer2.setOnClickListener { onAnswerClick(1) }
                llAnswer3.setOnClickListener { onAnswerClick(2) }
                llAnswer4.setOnClickListener { onAnswerClick(3) }
            }
        }
    }

    private fun onAnswerClick(userIndex: Int) {
        lifecycleScope.launch {
            val ok = withContext(Dispatchers.IO) { trainer.checkAnswer(userIndex) }

            when (userIndex) {
                0 -> if (ok) markAsCorrect(binding.llAnswer1, binding.tvVariantValue1, binding.tvVariantNumb1)
                else    markAsWrong  (binding.llAnswer1, binding.tvVariantValue1, binding.tvVariantNumb1)
                1 -> if (ok) markAsCorrect(binding.llAnswer2, binding.tvVariantValue2, binding.tvVariantNumb2)
                else    markAsWrong  (binding.llAnswer2, binding.tvVariantValue2, binding.tvVariantNumb2)
                2 -> if (ok) markAsCorrect(binding.llAnswer3, binding.tvVariantValue3, binding.tvVariantNumb3)
                else    markAsWrong  (binding.llAnswer3, binding.tvVariantValue3, binding.tvVariantNumb3)
                3 -> if (ok) markAsCorrect(binding.llAnswer4, binding.tvVariantValue4, binding.tvVariantNumb4)
                else    markAsWrong  (binding.llAnswer4, binding.tvVariantValue4, binding.tvVariantNumb4)
            }

            setOnClickListenerOff()
            showResultMessage(trainer, ok)
        }
    }
}

/* ===== helpers ===== */

private fun LearnWordActivity.markAsWrong(llAnswer: LinearLayout, tvVariantValue: TextView, tvVariantNumb: TextView) {
    llAnswer.background = ContextCompat.getDrawable(llAnswer.context, R.drawable.shape_rounded_containers_incorrect)
    tvVariantValue.setTextColor(ContextCompat.getColor(tvVariantValue.context, R.color.falseColor))
    tvVariantNumb.apply {
        background = ContextCompat.getDrawable(context, R.drawable.shape_rounded_fals)
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}

private fun LearnWordActivity.markAsCorrect(llAnswer: LinearLayout, tvVariantValue: TextView, tvVariantNumb: TextView) {
    llAnswer.background = ContextCompat.getDrawable(llAnswer.context, R.drawable.shape_rounded_containers_correct)
    tvVariantValue.setTextColor(ContextCompat.getColor(tvVariantValue.context, R.color.trueColor))
    tvVariantNumb.apply {
        background = ContextCompat.getDrawable(context, R.drawable.shape_rounded_true)
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}

private fun LearnWordActivity.markAnswerNeutral(
    llAnswer: LinearLayout, tvVariantValue: TextView, tvVariantNumb: TextView,
    clFinalScoreboard: ConstraintLayout, btSlip: Button
) {
    llAnswer.background = ContextCompat.getDrawable(llAnswer.context, R.drawable.shape_rounded_containers_neutral)
    tvVariantValue.setTextColor(ContextCompat.getColor(tvVariantValue.context, R.color.textVariantsColor))
    tvVariantNumb.apply {
        background = ContextCompat.getDrawable(context, R.drawable.shape_rounded_contains)
        setTextColor(ContextCompat.getColor(context, R.color.textVariantsColor))
    }
    clFinalScoreboard.isVisible = false
    btSlip.isVisible = true
}

private fun LearnWordActivity.showResultMessage(trainer: LearningWords, isCorrect: Boolean) {
    val color: Int
    val message: String
    val transient: String
    val resultIcon: Int

    if (isCorrect) {
        color = ContextCompat.getColor(this, R.color.trueColor)
        message = getString(R.string.text_correct)
        transient = ""
        resultIcon = R.drawable.ic_correct_answer
    } else {
        color = ContextCompat.getColor(this, R.color.falseColor)
        val correctTranslation = trainer.currentQuestion?.correctAnswer?.translation.orEmpty() // ← translat
        message = getString(R.string.incorrect_correct_translation)
        transient = correctTranslation
        resultIcon = R.drawable.ic_wrong_answer
    }

    with(binding) {
        btSlip.isVisible = false
        clFinalScoreboard.isVisible = true
        btFinalContinue.setTextColor(color)
        clFinalScoreboard.setBackgroundColor(color)
        tvFinalScoreboard.text = message
        tvFinalTranslation.text = transient
        ivFinalImage.setImageResource(resultIcon)
    }
}
