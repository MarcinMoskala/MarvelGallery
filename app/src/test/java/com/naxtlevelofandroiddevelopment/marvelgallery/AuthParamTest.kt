@file:Suppress("IllegalIdentifier")

package com.naxtlevelofandroiddevelopment.marvelgallery

import com.naxtlevelofandroiddevelopment.marvelgallery.data.network.providers.calculatedMd5AuthParameter
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthParamTest {

    @Test
    fun `Example value is correct`() {
        val authParam = calculatedMd5AuthParameter(
                timeStamp = 10000000,
                privateKey = "PrivateKey",
                publicKey = "PublicKey"
        )
        assertEquals("1746d867a61971a5f4a3f734401a95f8", authParam)
    }

    @Test
    fun `Param size is always 16`() {
        val randomKeys = listOf("", "XX", "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS")
        val randomTimeStamps = listOf(0, 1000, 1000000000000)

        for (key in randomKeys) for (ts in randomTimeStamps)
            assert(calculatedMd5AuthParameter(ts, key, key).length == 16)
    }
}