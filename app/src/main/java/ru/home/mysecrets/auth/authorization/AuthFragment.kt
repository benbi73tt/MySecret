package ru.home.mysecrets.auth.authorization

import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.crypto.tink.CleartextKeysetHandle
import com.google.crypto.tink.JsonKeysetWriter
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.hybrid.HybridConfig
import com.google.crypto.tink.hybrid.HybridKeyTemplates
import dagger.hilt.android.AndroidEntryPoint
import ru.home.domain.models.request.AuthRequest
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseScreenFragment
import ru.home.mysecrets.databinding.AuthorizationBinding
import ru.home.mysecrets.utils.activityNavController
import ru.home.mysecrets.utils.navigateSafely
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class AuthFragment : BaseScreenFragment<AuthViewModel,AuthorizationBinding>(R.layout.authorization) {
    override val binding by viewBinding(AuthorizationBinding::bind)
    override val viewModel: AuthViewModel by viewModels()

    override fun setupSubscribers()   {
        HybridConfig.register()
        binding.authorization.setOnClickListener {
            if (checkInputData()) {
                login().also {
                    val file = File(requireContext().filesDir, "keyset.json")
                    CleartextKeysetHandle.write(
                        KeysetHandle.generateNew(HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256),
                        JsonKeysetWriter.withOutputStream(FileOutputStream(file))
                    )
                }
            }
        }
        viewModel.signInState.collectUIState(
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
                activityNavController().navigateSafely(R.id.action_to_firstScreen)
            }
        )
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
        viewModel.signIn(
            AuthRequest(
                binding.parentLogin.text.toString(),
                binding.parentPassword.text.toString()
            )
        )
    }
}