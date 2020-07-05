package com.mikeni.diccionario.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikeni.diccionario.R
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

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        Log.d("palabra", "Entra")

        arguments?.let {
            Log.d("palabra", DefinitionFragmentArgs.fromBundle(it).definition.toString())
            textview_second.text = DefinitionFragmentArgs.fromBundle(it).definition.toString()
        }
    }


}