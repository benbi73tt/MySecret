package ru.home.mysecrets.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.databinding.RegistrationBinding

const val REGEX_PASSWORD = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[@\$!%*#?~(&)+=^_-]).{8,16}"

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.registration) {
    val binding by viewBinding(RegistrationBinding::bind)
//    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.createAccountButton.setOnClickListener {
            if (checkInputData()) {
                binding.inputEmail.text.toString().checkLogin()
            }
        }
    }

    private fun checkInputData(): Boolean {
        if (binding.inputPassword.text.isNullOrEmpty()
            || binding.inputPasswordAgain.text.isNullOrEmpty()
            || binding.inputEmail.text.isNullOrEmpty()
        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.check_field),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        if (!binding.inputPassword.text.toString().contains(Regex(REGEX_PASSWORD))) {
            Toast.makeText(
                requireContext(),
                getString(R.string.passwords_easy),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        if (binding.inputPassword.text.toString() != binding.inputPasswordAgain.text.toString()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.passwords_mismatch),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        if (binding.inputPassword.text.toString() != binding.inputPasswordAgain.text.toString()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.passwords_mismatch),
                Toast.LENGTH_LONG
            )
                .show()
            return false
        }
        return true
    }

    private fun String.checkLogin() {
//        viewModel.checkLogin(this)
    }
}