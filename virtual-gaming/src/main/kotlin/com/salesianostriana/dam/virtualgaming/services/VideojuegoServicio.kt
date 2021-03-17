package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.repositories.ImagenRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.repositories.VideojuegoRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VideojuegoServicio {

    @Autowired
    lateinit var juegoRepo:VideojuegoRepository

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var usuServicio:UsuarioServicio

    @Autowired
    lateinit var jwt: JwtTokenProvider

    @Autowired
    lateinit var imagenRepo:ImagenRepository

    fun findAll():List<Videojuego>{
        var result = juegoRepo.findAll()
        if(!result.isEmpty()){
            return result
        }else{
            throw ListEntityNotFoundException(Videojuego::class.java)
        }
    }

    fun findById(id:Long):Videojuego =
        juegoRepo.findById(id).orElseThrow {
            SingleEntityNotFoundException(id.toString(),Videojuego::class.java)
        }

    fun createVideojuego(videojuegoNuevo: Videojuego, token:String): ResponseEntity<ListadoVideojuegoDTO>{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
                SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var videojuego = juegoRepo.save(videojuegoNuevo)
        usuRepo.save(usuario)
        juegoRepo.save(videojuego)
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuego.toDto())
    }

    fun addVideojuegoToDeseado(vId: Long, token:String): ResponseEntity<ListadoVideojuegoDTO> {
        var idUsuario = jwt.getUserIdFromJWT(token.split(" ").toTypedArray()[1])
        var usuario = usuRepo.findById(idUsuario).orElseThrow {
            SingleEntityNotFoundException(idUsuario.toString(),Usuario::class.java)
        }
        var juego = juegoRepo.findById(vId).orElseThrow {
            SingleEntityNotFoundException(vId.toString(),Videojuego::class.java)
        }
        var deseado = juegoRepo.findByLikesContains(usuario)
        var likes = usuRepo.findByDeseadoContains(juego)
        var imagen = imagenRepo.findByVideojuego(juego)
        juego.likes = likes
        juego.imagen = imagen
        usuario.deseados = deseado
        usuario.addDeseados(juego)
        usuRepo.save(usuario)
        juegoRepo.save(juego)
        return ResponseEntity.status(HttpStatus.OK).body(juego.toDto())
    }

    fun deleteDeseadoById(idV:Long, token:String){
        var videojuego: Videojuego = findById(idV)
        var usu: Usuario = usuServicio.findById(jwt.getUserIdFromJWT(token.split(" ").toTypedArray()[1]))
        usu.deseados = juegoRepo.findByLikesContains(usu)
        var likesVideojuego = usuRepo.findByDeseadoContains(videojuego)
        videojuego.likes = likesVideojuego
        usu.removeDeseados(videojuego)
        usuRepo.save(usu)
        juegoRepo.save(videojuego)
    }
/*
    fun modifyVivienda(id: Long, videojuegoNuevo: Videojuego): ResponseEntity<ListadoVideojuegoDTO> = juegoRepo.findById(id)
            .map { videojuegoAModificar ->
                videojuegoAModificar.nombre = videojuegoNuevo.nombre
                videojuegoAModificar.descripcion = videojuegoNuevo.descripcion
                videojuegoAModificar.precio = videojuegoNuevo.precio
                videojuegoAModificar.plataforma = videojuegoNuevo.plataforma
                videojuegoAModificar.generoJuego = videojuegoNuevo.generoJuego
                videojuegoAModificar.requisitos = videojuegoNuevo.requisitos
                ResponseEntity.status(HttpStatus.OK).body(juegoRepo.save(videojuegoAModificar).toDto())
            }.orElseThrow {
                SingleEntityNotFoundException(id.toString(),Videojuego::class.java)
    }
 */
    fun deleteVideojuego(id: Long): ResponseEntity<Any>{
        if(juegoRepo.existsById(id)){
            juegoRepo.deleteById(id)
        }
        return ResponseEntity.noContent().build()
    }
}