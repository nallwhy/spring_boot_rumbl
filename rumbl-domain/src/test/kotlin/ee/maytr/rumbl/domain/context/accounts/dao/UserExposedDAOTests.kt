package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.config.DataSourceConfig
import ee.maytr.rumbl.domain.config.ExposedConfig
import ee.maytr.rumbl.domain.context.accounts.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.test.context.ContextConfiguration

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [DataSourceConfig::class, ExposedConfig::class])
class UserExposedDAOTests {
    private val userExposedDAO = UserExposedDAO()

    private val attrs = mapOf("email" to "test@example.com")

    @Nested
    inner class Test_get {
        @Test
        fun with_exist_user_id() {
            // TODO: need another way to insert test data
            val user = userExposedDAO.create(email = attrs.getValue("email"))!!

            val gottenUser = userExposedDAO.get(user.id)
            assertEquals(user, gottenUser)
        }

        @Test
        fun with_not_exist_user_id() {
            val getUser = userExposedDAO.get(0)

            Assertions.assertNull(getUser)
        }
    }

    @Nested
    inner class Test_create {
        @Test
        fun with_valid_attrs() {
            val user = userExposedDAO.create(email = attrs.getValue("email"))

            Assertions.assertNotNull(user)

            val notNullUser = user as User

            Assertions.assertNotNull(notNullUser.id)
            assertEquals(notNullUser.email, attrs.getValue("email"))
        }
    }
}
