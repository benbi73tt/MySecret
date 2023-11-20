package ru.home.mysecrets.utils

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.home.mysecrets.R
import ru.home.mysecrets.base.BaseBottomSheet
import ru.home.mysecrets.databinding.FragmentBottomDialogNoInternetBinding

const val  ERROR_NETWORK_DIALOG_TAG = "ERROR_NETWORK_DIALOG_TAG"
class ErrorNetworkDialog :
    BaseBottomSheet<FragmentBottomDialogNoInternetBinding>(R.layout.fragment_bottom_dialog_no_internet) {
    override val binding by viewBinding(FragmentBottomDialogNoInternetBinding::bind)
    override fun setupListeners() {
        super.setupListeners()
        binding.buttonClose.setOnClickListener {
            this.dismiss()
        }
    }
}