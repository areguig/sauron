package io.github.areguig.sauron.component

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.joda.time.DateTime
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate

object Components : IntIdTable() {
    val name = varchar("name", 63)
    val description = varchar("description", 255).nullable()
    val link = varchar("link", 255)
    val status = integer("status").default(0)
    val creation = datetime("creation").default(DateTime.now())
    val update = datetime("update").default(DateTime.now())
    val enabled = bool("enabled").default(true)
}


data class Component(val id: Int, val name: String, val description: String?, val link: String,
                     val status: Int, val creation: DateTime, val update: DateTime, val enabled: Boolean)

@Repository
@Transactional
class ComponentRepository(private val tt: TransactionTemplate) : InitializingBean {

    override fun afterPropertiesSet() {
        tt.execute {
            SchemaUtils.create(Components)
        }
    }

    fun findAll(): List<Component> {
        return Components.selectAll().map {
            Component(id = it[Components.id].value,
                    name = it[Components.name],
                    description = it[Components.description],
                    link = it[Components.link],
                    status = it[Components.status],
                    creation = it[Components.creation],
                    update = it[Components.update],
                    enabled = it[Components.enabled])
        }
    }
}