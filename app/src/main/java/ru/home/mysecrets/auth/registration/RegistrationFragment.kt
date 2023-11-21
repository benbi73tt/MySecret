package ru.home.mysecrets.auth.registration

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.domain.models.request.RegistrationRequest
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.RegistrationBinding
import ru.home.mysecrets.utils.navigateSafely

const val REGEX_PASSWORD = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[@\$!%*#?~(&)+=^_-]).{8,16}"

@AndroidEntryPoint
class RegistrationFragment :
    BaseScreenFragment<RegistrationViewModel, RegistrationBinding>(R.layout.registration) {
    override val binding by viewBinding(RegistrationBinding::bind)
    override val viewModel: RegistrationViewModel by viewModels()

    override fun setupSubscribers() {
        binding.createAccountButton.setOnClickListener {
            if (checkInputData()) {
                val regRequest = RegistrationRequest(
                    login = binding.inputEmail.text.toString(),
                    password = binding.inputPassword.text.toString()
                )
                viewModel.signUp(regRequest)
            }
        }
        viewModel.signUpState.collectUIState(
            state = {
                //Логика отображение процесса загрузки
                // it.setupViewVisibility(groupSignIn, loaderSignIn, true)
            },
            onError = {
                it.setupApiErrors()
                viewModel.resetState()
            },
            onSuccess = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                findNavController().navigateSafely(R.id.reg_to_auth)
            }
        )
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
}