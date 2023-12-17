package ru.home.mysecrets.encrypt

import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.hybrid.HybridDecryptFactory
import com.google.crypto.tink.hybrid.HybridEncryptFactory
import java.security.GeneralSecurityException


class Cryptage {

    val charset = Charsets.UTF_8
    fun encrypt(publicKeysetHandle: KeysetHandle, text: String, context: String): ByteArray? {
        val hybridEncrypt = HybridEncryptFactory.getPrimitive(publicKeysetHandle)
        val ciphertext = hybridEncrypt.encrypt(
            text.toByteArray(Charsets.UTF_8),
            context.toByteArray(Charsets.UTF_8)
        )
        return ciphertext

    }

    fun decrypt(privateKeysetHandle: KeysetHandle, text: ByteArray, context: String): String {
        val hybridDecrypt = HybridDecryptFactory.getPrimitive(privateKeysetHandle)
        val plaintextDecrypted: ByteArray = try {
            hybridDecrypt.decrypt(text, context.toByteArray())
        } catch (e: GeneralSecurityException) {
            "error".toByteArray()
        }
        return String(plaintextDecrypted, charset)
    }
}
