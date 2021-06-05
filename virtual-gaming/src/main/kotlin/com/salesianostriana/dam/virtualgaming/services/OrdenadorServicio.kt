package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.MiOrdenador
import com.salesianostriana.dam.virtualgaming.dtos.MisOrdenadoresDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Ordenador
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.*
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrdenadorServicio: BaseService<Ordenador, Long, OrdenadorRepository>() {

    @Autowired
    lateinit var ordenadorRepo:OrdenadorRepository

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var juegoRepo:VideojuegoRepository

    @Autowired
    lateinit var proceRepo:ProcesadorRepository

    @Autowired
    lateinit var memoriaRepo:MemoriaRAMRepository

    @Autowired
    lateinit var graficaRepo:TarjetaGraficaRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun getMisOrdenadores(token:String): List<Ordenador>{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        return usuario.ordenadores
    }

    fun createMiOrdenador(ordenador: MiOrdenador, token:String): Ordenador{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }

        var ordena = Ordenador(ordenador.titulo,ordenador.procesador,ordenador.ram,ordenador.grafica,usuario)
        this.save(ordena)

        usuario.ordenadores.add(ordenadorRepo.findByUsuario(usuario))
        usuRepo.save(usuario)

        return ordena
    }

    fun createPC(ordenador:String, token: String):Ordenador{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var titulo = ordenador.split(",")[0]
        var procesador = proceRepo.findByCode(ordenador.split(",")[1].toInt())
        var ram = memoriaRepo.findByCode(ordenador.split(",")[2].toInt())
        var grafica = graficaRepo.findByCode(ordenador.split(",")[3].toInt())

        var ordena = Ordenador(titulo,procesador,ram,grafica,usuario)
        this.save(ordena)

        usuario.ordenadores.add(ordenadorRepo.findByTitulo(titulo))
        usuRepo.save(usuario)

        return ordena
    }


    fun modifyOrdenador(ordenadorNuevo: String): ResponseEntity<MisOrdenadoresDTO> = ordenadorRepo.findById(ordenadorNuevo.split(",")[0].toLong())
            .map { ordenadorAModificar ->
                ordenadorAModificar.titulo = ordenadorNuevo.split(",")[1]
                ordenadorAModificar.procesador = proceRepo.findByCode(ordenadorNuevo.split(",")[2].toInt())
                ordenadorAModificar.ram = memoriaRepo.findByCode(ordenadorNuevo.split(",")[3].toInt())
                ordenadorAModificar.grafica = graficaRepo.findByCode(ordenadorNuevo.split(",")[4].toInt())
                ResponseEntity.status(HttpStatus.OK).body(ordenadorRepo.save(ordenadorAModificar).toDto())
            }.orElseThrow {
                SingleEntityNotFoundException(ordenadorNuevo.split(",")[0],Ordenador::class.java)
   }


    fun deleteOrdenador(id:Long):ResponseEntity<Any>{
        if(ordenadorRepo.existsById(id)){

            ordenadorRepo.deleteById(id)

        }
        return ResponseEntity.noContent().build()
    }

}