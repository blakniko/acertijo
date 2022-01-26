package com.disin.acertijo.ui.view.griewords

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.disin.acertijo.R
import com.disin.acertijo.databinding.FragmentGriedLettersBinding
import com.disin.acertijo.ui.viewmodel.ViewModelGriedKeyWords
import com.disin.keyword.ui.adapter.AdapterGriedKeyWords
import com.disin.keyword.ui.adapter.ListenerWins

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.Exception


class GriedLettersFragment : Fragment(), ListenerWins {
    lateinit var binding: FragmentGriedLettersBinding
    lateinit var myAdapter:AdapterGriedKeyWords
    private val viewModelGriedKeyWords = ViewModelGriedKeyWords()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGriedLettersBinding.inflate(inflater, container, false)
        return binding.root

       // return layoutInflater.inflate(R.layout.fragment_gried_letters,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        handleComponents()
        viewModelGriedKeyWords.getDataKeyWordsInit()
        handleEventInputText()

       showAlertWithView(
            layoutInflater.inflate(
                R.layout.layout_alert_instrucciones,
                binding.root,
                false
            )
        )
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu_activity, menu)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.instrucciones -> showAlertWithView(
                layoutInflater.inflate(
                    R.layout.layout_alert_instrucciones,
                    binding.root,
                    false
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupViews() {
        setHasOptionsMenu(true)
        myAdapter = AdapterGriedKeyWords(requireContext())
        binding.recycler.apply {
            myAdapter.setListenerWinIs(this@GriedLettersFragment)
            this.adapter = myAdapter
            this.layoutManager = GridLayoutManager(context, 5)
        }

       /* binding.btn.setOnClickListener {
            if (validateEmpty()) {
                Snackbar.make(binding.root, "No se permiten campos vacios", Snackbar.LENGTH_SHORT)
                    .show()
            } else {

                if (validatenumber(binding.letter.text.toString())) {
                    Snackbar.make(binding.root, "No se permiten numeros", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    myAdapter.insertNewLetter(binding.letter.text.toString())
                    binding.letter.setText("")
                }
            }
        }*/
    }

    private fun handleComponents() {
        viewModelGriedKeyWords.listKeyWords.observe(viewLifecycleOwner, Observer {
            myAdapter.initLetters(it)
        })
    }

    private fun showAlertWithView(view: View) {
        val alert = MaterialAlertDialogBuilder(requireContext())
        alert.setView(view)
        alert.show()
    }

    private fun validatenumber(letter: String): Boolean {
        var control = false

        control = try {
            var number = letter.toInt()
            true
        } catch (e: Exception) {
            false
        }
        return control
    }

    private fun validateEmpty(): Boolean {
        return binding.letter.text.toString().isNullOrEmpty()

    }

    override fun isWin(win: Boolean) {
        if (win) {
            viewModelGriedKeyWords.getDataKeyWordsInit()
            showAlertWithView(layoutInflater.inflate(R.layout.layout_win, binding.root, false))
        }
    }

    override fun isLoser(loser: Boolean) {
        if (loser) {
            showAlertWithView(layoutInflater.inflate(R.layout.error_intent, binding.root, false))
        }
    }

    override fun isLoserFinish(loser: Boolean) {
        if (loser) {
            viewModelGriedKeyWords.getDataKeyWordsInit()
            val view = layoutInflater.inflate(R.layout.error_intent, binding.root, false)
            view.findViewById<TextView>(R.id.title).text = "Haz perdido definitavamente"
            view.findViewById<TextView>(R.id.body).text =
                "Eres una decepción murcielago, no haz logrado salvar a tu ciudad, hasta nunca cabooom!!"
            view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.losserfinish)
            showAlertWithView(view)

        }
    }

    fun handleEventInputText(){
        binding.letter.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("before")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.letter.text.toString().isNotEmpty()) {
                    myAdapter.insertNewLetter(binding.letter.text.toString())
                    binding.letter.setText("")
                }
            }
        })

    }


}