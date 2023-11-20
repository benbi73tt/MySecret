package ru.home.mysecrets.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.databinding.AuthorizationBinding
import ru.home.mysecrets.utils.activityNavController
import ru.home.mysecrets.utils.navigateSafely

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.authorization) {
    val binding by viewBinding(AuthorizationBinding::bind)
//    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.authorization.setOnClickListener {
//            if (checkInputData()) {
////                login()
//                activityNavController().navigateSafely(R.id.action_to_home)
//            }
            activityNavController().navigateSafely(R.id.action_to_firstScreen)

        }
    }

    private fun checkInputData(): Boolean {
        if (binding.parentLogin.text.isNullOrEmpty()
            || binding.parentPassword.text.isNullOrEmpty()
        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.check_field),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        return true
    }

    private fun login() {
//        registrationViewModel.loginParent(
//            AuthRequest(
//                binding.parentLogin.text.toString(),
//                binding.parentPassword.text.toString()
//            )
//        )
    }
}