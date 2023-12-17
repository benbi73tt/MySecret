package ru.home.mysecrets.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.databinding.PreAuthBinding
import ru.home.mysecrets.utils.navigateSafely

@AndroidEntryPoint
class PreAuthFragment: Fragment(R.layout.pre_auth) {
    private val binding by viewBinding(PreAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authorization.setOnClickListener {
            findNavController().navigateSafely(R.id.to_auth)
        }
        binding.registration.setOnClickListener {
            findNavController().navigateSafely(R.id.to_register)
        }
    }
}
