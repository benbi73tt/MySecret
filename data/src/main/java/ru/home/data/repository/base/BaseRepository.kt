package ru.home.data.repository.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import retrofit2.Response
import ru.home.data.repository.storage.model.ErrorResponse
import ru.home.data.utils.DataMapper
import ru.home.data.utils.fromJson
import ru.home.domain.domain.core.Either
import ru.home.domain.domain.core.NetworkError
import ru.home.mysecrets.data.BuildConfig

private const val ERROR_RARE_CASE = "Произошла ошибка сервера"

abstract class BaseRepository {

    /**
     * Do network request with [DataMapper.mapToDomain]
     *
     * @receiver [doNetworkRequest]
     */
    protected fun <T : DataMapper<S>, S> doNetworkRequestWithMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, S>> = doNetworkRequest(request) { body ->
        Either.Right(body.mapToDomain())
    }

    /**
     * Do network request without mapping for primitive types
     *
     * @receiver [doNetworkRequest]
     */
    protected fun <T> doNetworkRequestWithoutMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, T>> = doNetworkRequest(request) { body ->
        Either.Right(body)
    }

    /**
     * Do network request for [Response] with [List]
     *
     * @receiver [doNetworkRequest]
     */
    protected fun <T : DataMapper<S>, S> doNetworkRequestForList(
        request: suspend () -> Response<List<T>>
    ): Flow<Either<NetworkError, List<S>>> = doNetworkRequest(request) { body ->
        Either.Right(body.map { it.mapToDomain() })
    }

    /**
     * Do network request for and return [Unit]
     *
     * @receiver [doNetworkRequest]
     */
    protected fun <T> doNetworkRequestUnit(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, Unit>> = doNetworkRequest(request) {
        Either.Right(Unit)
    }

    /**
     * Base function for do network requests
     *
     * @param T data layer model (DTO)
     * @param S domain layer model
     * @param request http request function from api service
     * @param successful handle response body with custom mapping
     *
     * @return [NetworkError] or [Response.body] in [Flow] with [Either]
     *
     * @see [Response]
     * @see [Flow]
     * @see [Either]
     * @see [NetworkError]
     */
    private fun <T, S> doNetworkRequest(
        request: suspend () -> Response<T>,
        successful: (T) -> Either.Right<S>
    ) = flow<Either<NetworkError, S>> {
        request().let { response ->
            when {
                response.isSuccessful && response.body() != null -> {
                    emit(successful.invoke(response.body()!!))
                }

                else -> {
                    val message = try {
                        val error = response.errorBody().toApiError<ErrorResponse>()
                        error?.message ?: ERROR_RARE_CASE
                    } catch (e: Throwable) {
                        if (!response.message().isNullOrEmpty()) {
                            response.message()
                        } else {
                            ERROR_RARE_CASE
                        }
                    }
                    emit(Either.Left(NetworkError.Api(message)))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
        .catch { exception ->
            val message = exception.localizedMessage ?: "Error Occurred!"
            if (BuildConfig.DEBUG) {
                Log.d(this@BaseRepository.javaClass.simpleName, message)
            }
            emit(Either.Left(NetworkError.Unexpected(message)))
        }

    /**
     * Convert network error from server side
     *
     * Изменить без Moshi
     *
     * @receiver [ResponseBody]
     * @see Response.errorBody
     * @see fromJson
     */
    private inline fun <reified T> ResponseBody?.toApiError(): T? {
        return this?.string()?.let { fromJson<T>(it) }
    }

    /**
     * Get non-nullable body from network request
     *
     * &nbsp
     *
     * ## How to use:
     * ```
     * override fun getData() = doNetworkRequestWithMapping {
     *     serviceApi.getData().onSuccess { data ->
     *         make something with data
     *     }
     * }
     * ```
     *
     * @see Response.body
     * @see let
     */
    protected inline fun <T : Response<S>, S> T.onSuccess(block: (S) -> Unit): T {
        this.body()?.let(block)
        return this
    }

    /**
     * Do request to local database with [DataMapper.mapToDomain]
     *
     * @param request high-order function for request to database
     */
    protected fun <T : DataMapper<S>, S> doLocalRequest(
        request: () -> Flow<T>
    ): Flow<S> = request().map { data -> data.mapToDomain() }

    /**
     * Do request to local database with [DataMapper.mapToDomain] for [List]
     *
     * @param request high-order function for request to database
     */
    protected fun <T : DataMapper<S>, S> doLocalRequestForList(
        request: () -> Flow<List<T>>
    ): Flow<List<S>> = request().map { list -> list.map { data -> data.mapToDomain() } }
}
