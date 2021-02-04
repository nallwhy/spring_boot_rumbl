package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.context.accounts.model.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.Connection

@Repository
class UserJDBCDAO(
    private val jdbcTemplate: JdbcTemplate
) : UserDAO {
    companion object {
        private const val GET_QUERY = "SELECT * FROM users WHERE id = ?"
        private const val LIST_QUERY = "SELECT * FROM users"
        private const val GET_COUNT_QUERY = "SELECT COUNT(*) FROM users"
        private const val INSERT_QUERY = "INSERT INTO users (email) VALUES (?)"
    }

    override fun get(id: Long): User? {
        return try {
            jdbcTemplate.queryForObject(GET_QUERY, { rs, _ ->
                User(rs.getLong("id"), rs.getString("email"))
            }, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override fun list(): List<User> {
        return jdbcTemplate.query(LIST_QUERY) { rs, _ ->
            User(rs.getLong("id"), rs.getString("email"))
        }
    }

    override fun getCount(): Long {
        return jdbcTemplate.queryForObject(GET_COUNT_QUERY, Long::class.java)!!
    }

    override fun create(email: String): User? {
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
