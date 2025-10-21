package com.example.tp_grupol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class PrimerFragmento : Fragment() {

    var listener: PrimerFragmentoInterfaz? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        val view = inflater.inflate(R.layout.primer_fragmento, container, false)
        val boton = view.findViewById<Button>(R.id.btn_mostrar)
        boton.setOnClickListener {
            // Llama a la función de la interfaz (que la MainActivity implementa)
            listener?.mostrarContenido()
        }

        return view
    }
}
