package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.config.DataSourceConfig
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Nested
    inner class Test_get {
        @Test
        fun with_exist_user_id() {
            // not a good test
            val user = userJDBCDAO.create("test@exmaple.com")!!

            val get_user = userJDBCDAO.get(user.id)
            assertEquals(user, get_user)
        }
    }
}
