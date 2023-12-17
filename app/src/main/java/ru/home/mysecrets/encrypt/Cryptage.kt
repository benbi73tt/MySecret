//package ru.home.mysecrets.encrypt
//
//import android.util.Log
//import com.google.crypto.tink.KeysetHandle
//import com.google.crypto.tink.config.TinkConfig
//import com.google.crypto.tink.hybrid.HybridDecryptFactory
//import com.google.crypto.tink.hybrid.HybridEncryptFactory
//import com.google.crypto.tink.hybrid.HybridKeyTemplates
//
//
//class Cryptage {
//
//    fun main() {
//        TinkConfig.register()
//
//        val privateKeysetHandle = KeysetHandle.generateNew(
//            HybridKeyTemplates.ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256
//        )
//        val publicKeysetHandle = privateKeysetHandle.publicKeysetHandle
//
//        val plaintext = "baeldung"
//        val contextInfo = "Tink"
//
//        val hybridEncrypt = HybridEncryptFactory.getPrimitive(publicKeysetHandle)
//        val hybridDecrypt = HybridDecryptFactory.getPrimitive(privateKeysetHandle)
//
//        val ciphertext = hybridEncrypt.encrypt(plaintext.toByteArray(), contextInfo.toByteArray())
//        Log.d("!@#", ciphertext.toString())
//        val plaintextDecrypted = hybridDecrypt.decrypt(ciphertext, contextInfo.toByteArray())
//        Log.d("!@#", plaintextDecrypted.toString())
//
//    }
//}