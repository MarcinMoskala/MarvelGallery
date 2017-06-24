package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Builds the required API "hash" parameter (timeStamp + privateKey + publicKey)
 * @param timeStamp Current timeStamp
 * @return MD5 hash string
 */
fun calculatedMd5AuthParameter(timeStamp: Long, privateKey: String, publicKey: String): String {
    val messageDigest = getMd5Digest(timeStamp.toString() + privateKey + publicKey)
    val md5 = BigInteger(1, messageDigest).toString(16)
    return "0" * (32 - md5.length) + md5
}

private fun getMd5Digest(str: String): ByteArray = try {
    MessageDigest.getInstance("MD5").digest(str.toByteArray())
} catch (e: NoSuchAlgorithmException) {
    Log.e("DataManager", "Error hashing required parameters: " + e.message)
    byteArrayOf()
}

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }