package io.github.areguig.sauron.component

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/component")
class ComponentEndpoint (val componentRepository: ComponentRepository ){

    @GetMapping(value = ["", "/"], produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)])
    fun components() =
            componentRepository.findAll()

    @GetMapping(value = ["/{id}", "/{id}/"], produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)])
    fun component(@PathVariable id: Int) = "component $id"
}

