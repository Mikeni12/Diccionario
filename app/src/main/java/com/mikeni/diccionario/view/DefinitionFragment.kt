package com.mikeni.diccionario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikeni.diccionario.R
import com.mikeni.diccionario.model.DefinitionResponse
import kotlinx.android.synthetic.main.fragment_definition.*

class DefinitionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_definition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val response: DefinitionResponse = DefinitionFragmentArgs.fromBundle(it).definition
            tvWord.text = response.word
            tvType.text = response.meanings.first().partOfSpeech
            textview_second.text = response.meanings.joinToString { def ->
                def.definitions.joinToString("\n") { mean ->
                    "${mean.definition} ${mean.example}"
                }
            }
        }
    }
}