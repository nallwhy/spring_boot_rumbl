package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.context.accounts.model.User
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow

object UsersTable: LongIdTable() {
    val email = varchar("email", 255)

    fun toUser(row: ResultRow): User {
        return User(
            id = row[id].value,
            email = row[email]
        )
    }
}
