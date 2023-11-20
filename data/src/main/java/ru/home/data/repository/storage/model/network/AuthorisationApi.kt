package ru.home.data.repository.storage.model.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.home.data.repository.storage.model.request.RegistrationRequestData
import ru.home.data.repository.storage.model.response.RegistrationResponseData


/**
 * Описание API авторизации
 */
interface AuthorisationApi {

//    @Multipart
//    @POST("api/login")
//    suspend fun signIn(
//        @Part("username") login: RequestBody,
//        @Part("password") password: RequestBody
//    ): Response<AuthResponseData>

    /**
     * Регистрация нового пользователя
     */
    @POST("auth/users/")
    suspend fun signUp(
        @Body user: RegistrationRequestData
    ): Response<RegistrationResponseData>

//    @GET("api/token/refresh")
//    suspend fun refreshToken(
//        @Header("Authorization") token: String
//    ): AuthResponseData

    companion object {
        fun create(retrofit: RetrofitInstance): AuthorisationApi {
            return retrofit.invoke().create(AuthorisationApi::class.java)
        }
    }
}