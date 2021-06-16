package com.shaadi.peopleinteractive.utils

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

class Constants {

    companion object{
        //https://randomuser.me/api/?results=10

        val CONNECTION_TIME: Long = 20
        val BASE_URL = "https://randomuser.me/"

        @IntDef(DECLINE, ACCEPT, DEFAULT)
        @Retention(RetentionPolicy.SOURCE)
        annotation class STATUS

        const val DECLINE = 0x00000009
        const val ACCEPT = 0x00000018
        const val DEFAULT = 0x00000027
    }
}