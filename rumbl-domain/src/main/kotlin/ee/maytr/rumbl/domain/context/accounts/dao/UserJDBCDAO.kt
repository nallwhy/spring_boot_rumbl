package ee.maytr.rumbl.domain.context.accounts.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserJDBCDAO(
    private val jdbcTemplate: JdbcTemplate
) {
    companion object {
        private const val GET_COUNT_QUERY = "SELECT COUNT(*) FROM users"
    }

    fun getCount(): Int {
        return jdbcTemplate.queryForObject(GET_COUNT_QUERY, Int::class.java)!!
    }
}
