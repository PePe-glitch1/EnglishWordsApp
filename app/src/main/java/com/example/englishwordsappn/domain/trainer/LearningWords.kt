package com.example.englishwordsappn.domain.trainer

import com.example.englishwordsappn.domain.model.Question
import com.example.englishwordsappn.data.Word       // ← якщо entity/dao в пакеті data
import com.example.englishwordsappn.data.WordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val NUMBER_OF_ANSWERS: Int = 4

class LearningWords(private val dao: WordDao) {
    private var _currentQuestion: Question? = null
    val currentQuestion: Question? get() = _currentQuestion

    suspend fun getNextQuestion(): Question? = withContext(Dispatchers.IO) {
        val notLearned = dao.getNotLearned()
        if (notLearned.isEmpty()) { _currentQuestion = null; return@withContext null }

        val variants: List<Word> =
            if (notLearned.size < NUMBER_OF_ANSWERS) {
                val learnedPool = dao.getLearned().shuffled()
                (notLearned + learnedPool.take(NUMBER_OF_ANSWERS - notLearned.size)).shuffled()
            } else {
                notLearned.shuffled().take(NUMBER_OF_ANSWERS)
            }

        val correct = variants.random()
        _currentQuestion = Question(variants = variants, correctAnswer = correct)
        _currentQuestion
    }

    suspend fun checkAnswer(userAnswerIndex: Int): Boolean = withContext(Dispatchers.IO) {
        val q = _currentQuestion ?: return@withContext false
        val correctIndex = q.variants.indexOf(q.correctAnswer)
        val ok = userAnswerIndex == correctIndex
        if (ok) {
            val updated = q.correctAnswer.copy(learned = true)
            dao.update(updated)
            _currentQuestion = q.copy(
                correctAnswer = updated,
                variants = q.variants.map { if (it.id == updated.id) updated else it }
            )
        }
        ok
    }

    fun correctTranslation(): String =
        _currentQuestion?.correctAnswer?.translation.orEmpty()

    fun correctIndex(): Int =
        _currentQuestion?.let { it.variants.indexOf(it.correctAnswer) } ?: -1
}
