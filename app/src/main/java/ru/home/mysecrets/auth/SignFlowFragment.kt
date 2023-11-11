package ru.home.mysecrets.auth

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.home.mysecrets.BaseFragment
import ru.home.mysecrets.R
import ru.home.mysecrets.databinding.AuthContainerBinding

class SignFlowFragment : BaseFragment(R.layout.auth_container, R.id.nav_host_fragment_sign) {
    private val binding by viewBinding(AuthContainerBinding::bind)

    override fun setupNavigation(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.appBar.topAppBar.title = when (destination.id) {
                R.id.registration -> getString(R.string.registration_title)
                R.id.authorization -> getString(R.string.auth_title)
                else -> {
                    String()
                }
            }
        }
        binding.appBar.topAppBar.setupWithNavController(navController)
    }
}