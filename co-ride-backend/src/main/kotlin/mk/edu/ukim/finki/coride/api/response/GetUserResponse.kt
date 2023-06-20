package mk.edu.ukim.finki.coride.api.response

import mk.edu.ukim.finki.coride.domain.User

sealed interface GetUserResponse

data class GetUserResponseSuccess(val user: User): GetUserResponse
data class GetUserResponseFailed(val error: String): GetUserResponse