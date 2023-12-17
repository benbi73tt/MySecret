package ru.home.mysecrets.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.home.domain.domain.core.NetworkError
import ru.home.mysecrets.data.BuildConfig
import ru.home.mysecrets.extensions.launchMain
import ru.home.mysecrets.state.UIState
import ru.home.mysecrets.utils.ERROR_NETWORK_DIALOG_TAG
import ru.home.mysecrets.utils.ErrorNetworkDialog
import ru.home.mysecrets.utils.Network
import ru.home.mysecrets.extensions.showToastLong

abstract class BaseScreenFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding
    protected var isNetworkAvailable: Boolean = true


    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        subscribeNetworkState()
        initialize()
        setupListeners()
        setupRequests()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribers() {
    }

    private fun subscribeNetworkState() {
        launchRepeatOnLifecycle(Lifecycle.State.STARTED) {
            Network(requireContext()).networkState.collect { isConnected ->
                if (!isConnected) {
                    showNoInternetConnectionDialog()
                }
                isNetworkAvailable = isConnected
            }
        }
    }

    /**
     * Функция расширения setOnClickListener с проверкой Интернет соединения перед вызовом
     */
    protected fun View.safelyNetworkClickListener(listener: () -> Unit) {
        this.setOnClickListener {
            if (isNetworkAvailable) {
                listener.invoke()
            } else {
                showNoInternetConnectionDialog()
            }
        }
    }

    /**
     * Collect [UIState] with [launchRepeatOnLifecycle]
     *
     * @receiver [StateFlow] with [UIState]
     *
     * @param state optional, for working with all states
     * @param onError for error handling
     * @param onSuccess for working with data
     */
    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: NetworkError) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        launchRepeatOnLifecycle(lifecycleState) {
            this@collectUIState.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> onError.invoke(it.error)
                    is UIState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    /**
     * Setup views visibility depending on [UIState] states.
     *
     * @receiver [UIState]
     *
     * @param willShowViewIfSuccess whether to show views if the request is successful
     */
    protected fun <T> UIState<T>.setupViewVisibility(
        group: Group, loader: CircularProgressIndicator, willShowViewIfSuccess: Boolean = true
    ) {
        fun showLoader(isVisible: Boolean) {
            group.isVisible = !isVisible
            loader.isVisible = isVisible
        }

        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> showLoader(true)
            is UIState.Error -> showLoader(false)
            is UIState.Success -> showLoader(!willShowViewIfSuccess)
        }
    }

    /**
     * Extension function for setup errors from server side
     *
     * @receiver [NetworkError]
     */
    protected fun NetworkError.setupApiErrors() = when (this) {
        is NetworkError.Unexpected -> {
            showToastLong(this.error)
        }
        is NetworkError.Api -> {
            showToastLong(this.error)
        }
    }

    /**
     * Collect flow safely with [launchRepeatOnLifecycle]
     */
    protected fun <T> Flow<T>.collectSafely(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        collector: (T) -> Unit
    ) {
        launchRepeatOnLifecycle(state) {
            this@collectSafely.collect {
                collector(it)
            }
        }
    }

    /**
     * Launch coroutine with [repeatOnLifecycle] API
     *
     * @param state [Lifecycle.State][androidx.lifecycle.Lifecycle.State] in which `block` runs in a new coroutine. That coroutine
     * will cancel if the lifecycle falls below that state, and will restart if it's in that state
     * again.
     * @param block The block to run when the lifecycle is at least in [state] state.
     */
    private fun launchRepeatOnLifecycle(
        state: Lifecycle.State,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launchMain(
            safeAction = {
                viewLifecycleOwner.repeatOnLifecycle(state) {
                    block()
                }
            },
            onError = {
                if (BuildConfig.DEBUG) {
                    showToastLong("${this@BaseScreenFragment.javaClass.simpleName} ${it.message}")
                }
            }
        )
    }

    protected fun showNoInternetConnectionDialog() {
        ErrorNetworkDialog().show(
            requireActivity().supportFragmentManager,
            ERROR_NETWORK_DIALOG_TAG
        )
    }
}