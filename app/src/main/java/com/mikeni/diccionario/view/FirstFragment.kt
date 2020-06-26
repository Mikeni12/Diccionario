package com.mikeni.diccionario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mikeni.diccionario.R
import com.mikeni.diccionario.viewmodel.DictionaryViewModel
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]

        button_first.setOnClickListener {
            viewModel.getDefinition("Hola")
        }


        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.definition.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.meanings.first().definitions.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}