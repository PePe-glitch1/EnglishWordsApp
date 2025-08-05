package com.example.englishwordsappn

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.englishwordsappn.databinding.ActivityLearnWordBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llAnswer2.setOnClickListener {
            markAsCorrect(
                binding.llAnswer2,
                binding.tvVariantValue2,
                binding.tvVariantNumb2
            )
            showResultMessage(isCorrect = true)
        }

        binding.llAnswer1.setOnClickListener {
            markAsWrong(
                binding.llAnswer1,
                binding.tvVariantValue1,
                binding.tvVariantNumb1
            )
            showResultMessage(isCorrect = false)
        }

        binding.btFinalContinue.setOnClickListener {
            markAnswerNeutral(
                binding.llAnswer1,
                binding.tvVariantValue1,
                binding.tvVariantNumb1,
                binding.clFinalScoreboard,
                binding.btSlip
            )
            markAnswerNeutral(
                binding.llAnswer2,
                binding.tvVariantValue2,
                binding.tvVariantNumb2,
                binding.clFinalScoreboard,
                binding.btSlip

            )
        }

    }

    private fun MainActivity.markAsWrong(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_incorrect
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.falseColor
            )
        )

        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_fals
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
        }
    }

    private fun MainActivity.markAsCorrect(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.trueColor
            )
        )

        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_true
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.white
                )
            )
        }
    }

    private fun MainActivity.markAnswerNeutral(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
        clFinalScoreboard: ConstraintLayout,
        btSlip: Button
    ) {

        llAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_neutral
        )



        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )


        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_contains
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor
                )
            )
        }
        clFinalScoreboard.isVisible = false
        btSlip.isVisible = true

    }

    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val message: String
        val resultIcon: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.trueColor)
            message = getString(R.string.text_correct)
            resultIcon = R.drawable.ic_correct_answer
        } else {
            color = ContextCompat.getColor(this, R.color.falseColor)
            message = getString(R.string.text_wrong)
            resultIcon = R.drawable.ic_wrong_answer
        }
        with(binding){
            btSlip.isVisible = false
            clFinalScoreboard.isVisible= true
            btFinalContinue.setTextColor(color)
            clFinalScoreboard.setBackgroundColor(color)
            tvFinalScoreboard.text = message
            ivFinalImage.setImageResource(resultIcon)

        }

    }
}