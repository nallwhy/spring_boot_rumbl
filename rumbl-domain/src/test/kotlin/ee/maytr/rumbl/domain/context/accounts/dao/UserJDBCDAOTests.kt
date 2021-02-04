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
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DataSourceConfig::class)
@ContextConfiguration(classes = [UserJDBCDAO::class])
class UserJDBCDAOTests {
    @Autowired
    private lateinit var userJDBCDAO: UserJDBCDAO

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
