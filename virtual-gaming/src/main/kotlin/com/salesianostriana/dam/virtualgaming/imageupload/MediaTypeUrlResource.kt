package com.salesianostriana.dam.virtualgaming.imageupload

import org.springframework.core.io.UrlResource
import java.net.URI

class MediaTypeUrlResource (
        val mediaType: String, var uri: URI) : UrlResource(uri)