package com.disin.acertijo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.disin.acertijo.Phrase
import com.disin.acertijo.data.model.Word
import com.disin.acertijo.data.repository.RepositoryPhrases


class ViewModelGriedKeyWords : ViewModel() {
    val listKeyWords = MutableLiveData<ArrayList<Word>>()

    fun getDataKeyWordsInit() {
        val _listKeyWords = ArrayList<Word>()
        var count = 0
        for (i in 0 until 30) {
            count = resetCount(count)
            _listKeyWords.add(Word(count, "", false, false, false))
            count++
            println(_listKeyWords[i].toString())
        }
        count = 0
        listKeyWords.postValue(_listKeyWords)
        randomPhrase()
    }

    private fun resetCount(count: Int): Int {
        return when (count) {
            5 -> 0
            10 -> 0
            15 -> 0
            20 -> 0
            25 -> 0
            30 -> 0
            else -> count
        }

    }

    private fun randomPhrase() {
        Phrase.PHRASE = RepositoryPhrases().getAllPhrases().random()
        println("frase ${Phrase.PHRASE}")
    }
}