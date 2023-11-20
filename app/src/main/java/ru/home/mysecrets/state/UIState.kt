package ru.home.mysecrets.state

import ru.home.domain.domain.core.NetworkError


sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Error<T>(val error: NetworkError) : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
}