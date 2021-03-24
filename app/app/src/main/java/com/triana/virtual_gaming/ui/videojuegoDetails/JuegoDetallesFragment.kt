package com.triana.virtual_gaming.ui.videojuegoDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.triana.virtual_gaming.R

class JuegoDetallesFragment : Fragment() {

    companion object {
        fun newInstance() = JuegoDetallesFragment()
    }

    private lateinit var viewModel: JuegoDetallesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.juego_detalles_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JuegoDetallesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}