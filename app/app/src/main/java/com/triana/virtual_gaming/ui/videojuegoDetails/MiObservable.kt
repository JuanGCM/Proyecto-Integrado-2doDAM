package com.triana.virtual_gaming.ui.videojuegoDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triana.virtual_gaming.ui.models.UnVideojuego

open class MiObservable : ViewModel()
{
    val data = MutableLiveData<UnVideojuego>()

    fun data(item: UnVideojuego) {
        data.value = item
    }
}