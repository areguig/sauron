package io.github.areguig.sauron

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.support.beans

@SpringBootApplication
class SauronApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder()
            .sources(SauronApplication::class.java)
            .initializers( beans{})
            .run(* args)
}
