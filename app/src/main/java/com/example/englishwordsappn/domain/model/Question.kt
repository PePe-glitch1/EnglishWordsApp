package com.example.englishwordsappn.domain.model

import com.example.englishwordsappn.data.Word

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
    )