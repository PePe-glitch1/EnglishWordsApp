package com.example.englishwordsappn.data

import com.example.englishwordsappn.domain.model.Word

object WordsRepository {
    val words: MutableList<Word> = mutableListOf(
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

    fun learnedCount() = words.count { it.learned }
    fun notLearnedCount() = words.count { !it.learned }
}
