package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.config.DataSourceConfig
import ee.maytr.rumbl.domain.context.accounts.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import javax.sql.DataSource

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DataSourceConfig::class])
class UserJDBCDAOTests(
    @Autowired val dataSource: DataSource
) {
    private val jdbcTemplate = JdbcTemplate(dataSource)
    private val userJDBCDAO = UserJDBCDAO(jdbcTemplate)

    private val attrs = mapOf("email" to "test@example.com")

    @Nested
    inner class Test_get {
        @Test
        fun with_exist_user_id() {
            // TODO: need another way to insert test data
            val user = userJDBCDAO.create(email = attrs.getValue("email"))!!

            val gottenUser = userJDBCDAO.get(user.id)
            assertEquals(user, gottenUser)
        }

        @Test
        fun with_not_exist_user_id() {
            val getUser = userJDBCDAO.get(0)

            assertNull(getUser)
        }
    }

    @Nested
    inner class Test_create {
        @Test
        fun with_valid_attrs() {
            val user = userJDBCDAO.create(email = attrs.getValue("email"))

            assertNotNull(user)

            val notNullUser = user as User

            assertNotNull(notNullUser.id)
            assertEquals(notNullUser.email, attrs.getValue("email"))
        }
    }
}
