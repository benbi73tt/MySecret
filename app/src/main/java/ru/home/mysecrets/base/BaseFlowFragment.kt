package ru.home.mysecrets.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.home.mysecrets.R
import ru.home.mysecrets.extensions.showToastLong


private const val DELAY_BACK_DOUBLE_CLICK = 2000L

abstract class BaseFlowFragment(
    @LayoutRes layoutId: Int,
    @IdRes private val navHostFragmentId: Int
) : Fragment(layoutId) {
    private var doubleBackPressed: Boolean = false

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment
        val navController = navHostFragment.navController

        setupNavigation(navController)
    }

    protected open fun setupNavigation(navController: NavController) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackPressed) {
                    activity?.finishAffinity()
                }
                doubleBackPressed = true
                showToastLong(R.string.double_tap)
                Looper.myLooper()?.let {
                    Handler(it).postDelayed(
                        { doubleBackPressed = false },
                        DELAY_BACK_DOUBLE_CLICK
                    )
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}
