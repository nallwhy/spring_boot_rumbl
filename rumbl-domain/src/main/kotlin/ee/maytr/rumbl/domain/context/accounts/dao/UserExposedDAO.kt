package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.context.accounts.model.User
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserExposedDAO: UserDAO {
    private val table = UsersTable

    override fun get(id: Long): User? {
        return transaction {
            table
                .select { UsersTable.id eq id}
                .limit(1)
                .map { table.toUser(it) }
                .firstOrNull()
        }
    }

    override fun list(): List<User> {
        return transaction {
                table
                    .selectAll()
                    .map { table.toUser(it) }
                    .toList()
        }
    }

    override fun getCount(): Long {
        return transaction {
            table
                .selectAll()
                .count( )
        }
    }

    override fun create(email: String): User? {
        return transaction {
            table
                .insertAndGetId {
                    it[table.email] = email
                }
                .let { get(it.value) }
        }
    }
}
