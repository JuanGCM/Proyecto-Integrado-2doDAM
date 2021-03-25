package com.salesianostriana.dam.virtualgaming.configuration


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


//@Configuration
//@EnableSwagger2
class SwaggerConfiguration {
/*
    private fun apiKey(): ApiKey? {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    private fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return Arrays.asList(SecurityReference("JWT", authorizationScopes))
    }

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfo(
                "Mi proyecto API",
                "Api de videojuegos",
                "1.0",
                "Terminos ....",
                Contact("Juan Guillermo", "www.baeldung.com", "chavezmarengo@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList())
    }

 */
}