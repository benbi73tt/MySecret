package ru.home.mysecrets.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Класс для отслеживания состояние Интернета
 */
class Network(context: Context) {
    private val _networkState = MutableStateFlow(false)
    val networkState: StateFlow<Boolean> = _networkState
    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder().build()
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _networkState.value = true
            }

            override fun onLost(network: Network) {
                _networkState.value = false
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}
