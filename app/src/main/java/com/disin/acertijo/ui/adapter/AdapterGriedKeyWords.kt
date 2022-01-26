package com.disin.keyword.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.disin.acertijo.Phrase
import com.disin.acertijo.R
import com.disin.acertijo.data.model.Word


class AdapterGriedKeyWords(val context: Context) :
    RecyclerView.Adapter<AdapterGriedKeyWords.MyViewHolder>() {
    val letters = ArrayList<Word>()
    lateinit var listenerWinsis: ListenerWins


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val letter = view.findViewById<TextView>(R.id.letter)
        val container = view.findViewById<CardView>(R.id.cardview)

        fun bind(letters: ArrayList<Word>, holder: MyViewHolder, context: Context) {
            if (letters[position].containLetterExactly) {
                holder.container.setBackgroundColor(context.resources.getColor(R.color.exactly))
                holder.letter.setTextColor(context.resources.getColor(R.color.white))

            } else if (letters[position].containLetter) {
                holder.container.setBackgroundColor(context.resources.getColor(R.color.contain))
                holder.letter.setTextColor(context.resources.getColor(R.color.white))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_keywords, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.letter.text = letters[position].letter
        holder.bind(letters, holder, context)

    }

    override fun getItemCount() = letters.size

    fun initLetters(lettersList: ArrayList<Word>) {
        letters.clear()
        letters.addAll(lettersList)
        notifyDataSetChanged()
    }

    fun insertNewLetter(newLetter: String) {
        for (i in 0 until letters.size) {

            if (!letters[i].status) {
                Phrase.phraseIncoming = Phrase.phraseIncoming + newLetter
                println("posicion: ${letters[i].position} palabra es ${Phrase.phraseIncoming}")
               // validatePosition(letters[i], i)


                letters[i].let {
                    it.letter = newLetter.uppercase()
                    it.status = true

                    //valido si esta letrá es exacta en el index
                    if (validateIndexPhrase(it, newLetter)) {
                        it.containLetterExactly = true

                        //verifico la frase
                        if (it.position == 4 && verifyPhraseWin()) {
                            clearPhrase()
                            listenerWinsis.isWin(true)

                        } else if (it.position==4 && !verifyPhraseWin()) {
                            if (i ==29){
                                listenerWinsis.isLoserFinish(true)
                            }else {
                                clearPhrase()
                                listenerWinsis.isLoser(true)
                            }
                        }

                        // si no coincide en el index
                    } else {

                        if (it.position == 4) {
                            if (i ==29){
                                listenerWinsis.isLoserFinish(true)
                            }else {
                                clearPhrase()
                                listenerWinsis.isLoser(true)
                            }
                        }

                        //si contiene la letra pero no esta en el index
                        if (validateContainLeeter(letter = newLetter)) {
                            it.containLetter = true
                        }

                    }
                }
                notifyDataSetChanged()
                break
            }
        }
    }


    fun validateContainLeeter(letter: String): Boolean {
        return (Phrase.PHRASE.contains(letter))
    }

    fun validateIndexPhrase(word: Word, letter: String): Boolean {
        val indexToPhrase = Phrase.PHRASE.indexOf(letter)
        return (word.position == indexToPhrase)
    }

    fun setListenerWinIs(newListenerWinIs: ListenerWins) {
        this.listenerWinsis = newListenerWinIs
    }

    fun findIndexlettersByElement(letter: Word): Int {
        return letters.indexOf(letter)
    }

    fun verifyPhraseWin(): Boolean {
        return Phrase.PHRASE == Phrase.phraseIncoming
    }

    fun clearPhrase() {
        Phrase.phraseIncoming = ""
    }

    fun validatePosition(letter: Word, index: Int) {
        if (letter.position == 4) {
            if (verifyPhraseWin()) {
                clearPhrase()
                listenerWinsis.isWin(true)
            } else {
                if (index == 29) {
                    clearPhrase()
                    listenerWinsis.isLoserFinish(true)

                } else {
                    listenerWinsis.isLoser(true)
                    clearPhrase()
                }
            }
        }
    }


}