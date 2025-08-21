package com.example.englishwordsappn

data class Word(
    val word: String,
    val translation: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearningWords {
    private val dictionary = listOf(
        Word("apple", "яблоко"),
        Word("banana", "банан"),
        Word("orange", "апельсин"),
        Word("grape", "виноград"),
        Word("kiwi", "киви"),
        Word("peach", "персик"),
        Word("pear", "груша"),
        Word("strawberry", "клубника"),
        Word("watermelon", "арбуз"),
        Word("pineapple", "ананас")
    )

    private var _currentQuestion: Question? = null
    val currentQuestion: Question?
        get() = _currentQuestion

    fun getNextQuestion(): Question? {
        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords = if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = dictionary.filter { it.learned }.shuffled()
            (notLearnedList + learnedList.take(NUMBER_OF_ANSWERS - notLearnedList.size)).shuffled()
        } else {
            notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
        }

        val correctAnswer: Word = questionWords.random()

        _currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer
        )
        return _currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {
        return currentQuestion?.let {
            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                it.correctAnswer.learned = true
                true
            } else {
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWERS: Int = 4
