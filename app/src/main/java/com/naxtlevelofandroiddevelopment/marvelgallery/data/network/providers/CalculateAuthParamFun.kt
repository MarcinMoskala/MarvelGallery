package com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers

import java.math.BigInteger
import java.security.MessageDigest

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

// It is impossible to get NoSuchAlgorithmException, but if it will happen then we want it to
// break application.
private fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray())

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }