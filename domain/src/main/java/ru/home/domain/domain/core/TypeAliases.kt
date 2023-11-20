package ru.home.domain.domain.core

import kotlinx.coroutines.flow.Flow


/**
 * Простая оболочка для удобства сетевых запросов в репозиториях
 *
 * @see Flow
 * @see Either
 * @see NetworkError
 */
typealias RemoteWrapper<T> = Flow<Either<NetworkError, T>>
