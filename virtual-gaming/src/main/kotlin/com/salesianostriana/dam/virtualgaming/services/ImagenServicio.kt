package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.imageupload.ImgurImageAttribute
import com.salesianostriana.dam.virtualgaming.imageupload.ImgurStorageService
import com.salesianostriana.dam.virtualgaming.models.ImagenVideojuego
import com.salesianostriana.dam.virtualgaming.repositories.ImagenRepository
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.persistence.EntityManager

@Service
class ImagenServicio (
        private val imageStorageService: ImgurStorageService,
        private val imagenRepo: ImagenRepository,
        private val entityManager: EntityManager
    ) : BaseService<ImagenVideojuego, Long, ImagenRepository>() {

        val logger: Logger = LoggerFactory.getLogger(ImagenServicio::class.java)



        fun save(e: ImagenVideojuego, file: MultipartFile) : ImagenVideojuego {
            var imageAttribute : Optional<ImgurImageAttribute> = Optional.empty()
            if (!file.isEmpty) {
                imageAttribute = imageStorageService.store(file)
            }
            if(imageAttribute != null){
                e.img!!.id = imageStorageService.loadAsResource(imageAttribute.get().id!!).get().uri.toString()
                e.img!!.deletehash = imageAttribute.get().deletehash
            }
            return save(e)
        }

        override fun delete(e : ImagenVideojuego) {
            logger.debug("Eliminando la entidad $e")
            e.img?.let { it.deletehash?.let { it1 -> imageStorageService.delete(it1) } }
            super.delete(e)
        }

    }