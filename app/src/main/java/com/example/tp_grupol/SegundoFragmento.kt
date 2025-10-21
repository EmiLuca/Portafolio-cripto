package com.example.tp_grupol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SegundoFragmento : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Simplemente infla el layout que contiene la información
        return inflater.inflate(R.layout.segundo_fragmento, container, false)
    }
}
