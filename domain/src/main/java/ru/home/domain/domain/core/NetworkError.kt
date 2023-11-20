package ru.home.domain.domain.core

/**
 * Wrapper class for network errors
 *
 * @author Alish
 */
sealed class NetworkError {

    /**
     * State for unexpected exceptions, for example «HTTP code - 500» or exceptions when mapping models
     */
    class Unexpected(val error: String) : NetworkError()

    /**
     * State for default errors from server size
     */
    class Api(val error: String) : NetworkError()
}