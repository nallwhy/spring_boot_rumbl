package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.context.accounts.model.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.sql.Connection

@Repository
class UserJDBCDAO(
    private val jdbcTemplate: JdbcTemplate
) {
    companion object {
        private const val GET_QUERY = "SELECT * FROM users WHERE id = ?"
        private const val LIST_QUERY = "SELECT * FROM users"
        private const val GET_COUNT_QUERY = "SELECT COUNT(*) FROM users"
        private const val INSERT_QUERY = "INSERT INTO users (email) VALUES (?)"
    }

    fun get(id: Long): User? {
        return jdbcTemplate.queryForObject(GET_QUERY, { rs, _ ->
            User(rs.getLong("id"), rs.getString("email"))
        }, id)
    }

    fun list(): List<User> {
        return jdbcTemplate.query(LIST_QUERY) { rs, _ ->
            User(rs.getLong("id"), rs.getString("email"))
        }
    }

    fun getCount(): Int {
        return jdbcTemplate.queryForObject(GET_COUNT_QUERY, Int::class.java)!!
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    fun create(email: String): User? {
        val keyHolder: KeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ connection: Connection ->
            val preparedStatement = connection.prepareStatement(INSERT_QUERY, arrayOf("id"))
            preparedStatement.setString(1, email)

            preparedStatement
        }, keyHolder)

        val id = keyHolder.key

        return if (id == null) {
            null;
        } else {
            get(id.toLong())
        }
    }
}
