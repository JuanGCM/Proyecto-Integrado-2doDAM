package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.ListadoVideojuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.dtos.toSpecificDto
import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.repositories.*
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

    @Autowired
    lateinit var generoRepo: GeneroJuegoRepository

    @Autowired
    lateinit var procesadorRepo:ProcesadorRepository

    @Autowired
    lateinit var graficaRepo: TarjetaGraficaRepository

    @Autowired
    lateinit var ramRepo:MemoriaRAMRepository

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

    fun createVideojuego(videojuegoNuevo: Videojuego, token:String): Videojuego{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
                SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }

        return juegoRepo.save(videojuegoNuevo)
    }

    fun searchVideojuego(plataforma: String): List<Videojuego> {
        var resultado: List<Videojuego>
        if (plataforma == "todos") {
            resultado = findAll()
        } else if (plataforma == "Steam") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        } else if (plataforma == "Origin") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        } else if (plataforma == "Battle.net") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        } else if (plataforma == "Uplay") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        } else if (plataforma == "Epic") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        } else if (plataforma == "Rockstar") {
            resultado = juegoRepo.findByPlataforma(plataforma)
        }else {
            resultado = findAll()
        }

        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(Videojuego::class.java)
        }
    }

    fun getVideojuegoById(id:Long): Videojuego {
        var videojuego = findById(id)
        var imagens = imagenRepo.findImagenesByVideojuego(videojuego)
        videojuego.imagenes = imagens
        juegoRepo.save(videojuego)

        return videojuego
    }

    fun modifyVideojuego(id: Long, videojuegoNuevo: Videojuego): ResponseEntity<ListadoVideojuegoDTO> =
            juegoRepo.findById(id)
                    .map { videojuegoAModificar ->
                        videojuegoAModificar.nombre = videojuegoNuevo.nombre
                        videojuegoAModificar.descripcion = videojuegoNuevo.descripcion
                        videojuegoAModificar.precio = videojuegoNuevo.precio
                        videojuegoAModificar.plataforma = videojuegoNuevo.plataforma
                        ResponseEntity.status(HttpStatus.OK).body(juegoRepo.save(videojuegoAModificar).toDto())
                    }.orElseThrow {
                        SingleEntityNotFoundException(id.toString(),Videojuego::class.java)
    }

    fun findAllFavs(token:String) = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
            .toTypedArray()[1])).map {
        usu -> juegoRepo.findByLikesContains(usu).map { it.toSpecificDto() }
    }.orElseThrow {
        SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1]).toString(),Usuario::class.java)
    }



    fun addVideojuegoToFav(id: Long, token:String): ResponseEntity<ListadoVideojuegoDTO> {
        var idUsuario = jwt.getUserIdFromJWT(token.split(" ").toTypedArray()[1])
        var usuario = usuRepo.findById(idUsuario).orElseThrow {
            SingleEntityNotFoundException(idUsuario.toString(),Usuario::class.java)
        }
        var juego = juegoRepo.findById(id).orElseThrow {
            SingleEntityNotFoundException(id.toString(),Videojuego::class.java)
        }
        var meGusta = juegoRepo.findByLikesContains(usuario)
        var likes = usuRepo.findByDeseadosContains(juego)
        juego.likes = likes
        usuario.deseados = meGusta
        usuario.deseados.add(juego)
        juego.likes!!.add(usuario)
        usuRepo.save(usuario)
        juegoRepo.save(juego)
        return ResponseEntity.status(HttpStatus.OK).body(juego.toDto())
    }

    fun deleteFavById(id:Long, token:String){
        var juego: Videojuego = findById(id)
        var usu: Usuario = usuServicio.findById(jwt.getUserIdFromJWT(token.split(" ").toTypedArray()[1]))
        usu.deseados = juegoRepo.findByLikesContains(usu)

        var likesJuego = usuRepo.findByDeseadosContains(juego)
        juego.likes = likesJuego
        usu.deseados.remove(juego)
        juego.likes!!.remove(usu)
        usuRepo.save(usu)
        juegoRepo.save(juego)
    }

    fun deleteVideojuego(id: Long): ResponseEntity<Any>{
        if(juegoRepo.existsById(id)){
            juegoRepo.deleteById(id)
        }
        return ResponseEntity.noContent().build()
    }
}