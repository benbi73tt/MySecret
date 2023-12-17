package ru.home.mysecrets.utils

import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.hybrid.HybridKeyTemplates


const val ITEM_ENTRY_TITLE = "item_entry_title"
const val ITEM_ENTRY_DESC = "item_entry_desc"
const val ITEM_ENTRY_ID = "item_entry_id"


val PRIVATE_KEY: KeysetHandle = KeysetHandle.generateNew(
    HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256
)
