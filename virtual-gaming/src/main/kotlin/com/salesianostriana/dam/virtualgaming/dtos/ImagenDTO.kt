package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.ImagenVideojuego
import com.salesianostriana.dam.virtualgaming.models.Videojuego

data class NuevaImagenDTO(
        var videojuego: Videojuego,
        var texto: String
)

fun NuevaImagenDTO.toImagen() = ImagenVideojuego(videojuego)

data class ImagenDTO(

        val imageId: String?,
        val id: Long?
)

fun ImagenVideojuego.toDto() = ImagenDTO(img?.id, id)