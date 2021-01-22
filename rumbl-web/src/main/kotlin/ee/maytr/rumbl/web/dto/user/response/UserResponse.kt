package ee.maytr.rumbl.web.dto.user.response

import ee.maytr.rumbl.domain.context.accounts.model.User

data class UserResponse(
    val email: String
) {
    companion object {
        fun of(user: User?): UserResponse? {
            if(user == null) { return null }

            return UserResponse(user.email)
        }
    }
}
