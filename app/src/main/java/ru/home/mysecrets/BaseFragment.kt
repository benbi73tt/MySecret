package ru.home.mysecrets

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment (
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

    /**
     * Инициализация navController
     */
    protected open fun setupNavigation(navController: NavController) { }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackPressed) {
                    activity?.finishAffinity()
                }
                doubleBackPressed = true
                Toast.makeText(requireActivity(), getString(R.string.double_tap), Toast.LENGTH_LONG)
                    .show()
                Looper.myLooper()?.let {
                    Handler(it).postDelayed(
                         { doubleBackPressed = false },
                        2000
                    )
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}