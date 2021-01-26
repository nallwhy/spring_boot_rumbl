package ee.maytr.rumbl.domain.context.accounts.dao

import ee.maytr.rumbl.domain.context.accounts.model.User

interface UserDAO {
    fun get(id: Long): User?
    fun list(): List<User>
    fun getCount(): Int
    fun create(email: String): User?
}
