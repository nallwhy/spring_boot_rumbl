package ee.maytr.rumbl.web.controller

import ee.maytr.rumbl.domain.context.accounts.dao.UserJDBCDAO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userDAO: UserJDBCDAO
) {
    @GetMapping("/users/count")
    fun getCount(): Int {
        return userDAO.getCount()
    }
}
