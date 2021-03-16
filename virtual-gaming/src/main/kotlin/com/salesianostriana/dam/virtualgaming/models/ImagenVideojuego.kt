package com.salesianostriana.dam.virtualgaming.models

import com.salesianostriana.dam.virtualgaming.imageupload.ImgurImageAttribute
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class ImagenVideojuego (

        @OneToOne(mappedBy = "imagen")
        var videojuego:Videojuego,

        var img: ImgurImageAttribute?= null,

        @Id @GeneratedValue
         var id:Long?= null

){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as ImagenVideojuego
        if (id != that.id) return false
        return true
    }
    override fun hashCode(): Int {
        return if (id != null)
            id.hashCode()
        else 0
    }
}