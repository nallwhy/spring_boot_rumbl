package ee.maytr.rumbl.web.controller

import ee.maytr.rumbl.domain.context.accounts.dao.UserDAO
import ee.maytr.rumbl.web.dto.user.request.UserCreateRequest
import ee.maytr.rumbl.web.dto.user.response.UserResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    @Qualifier("userExposedDAO") private val userDAO: UserDAO
) {
    @GetMapping("/users/{id}")
    fun get(@PathVariable("id") id: Long): UserResponse? {
        return UserResponse.of(userDAO.get(id))
    }

    @GetMapping("/users")
    fun list(): List<UserResponse?> {
        return userDAO.list().map { user -> UserResponse.of(user) }
    }

    @GetMapping("/users/count")
    fun getCount(): Long {
        return userDAO.getCount()
    }

    @PostMapping("/users")
    fun create(@RequestBody request: UserCreateRequest): UserResponse? {
        return UserResponse.of(userDAO.create(request.email))
    }
}
