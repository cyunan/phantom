package com.cyn.common.utils

import java.util.ServiceLoader

object IServiceLoader {
    fun <S> load(service: Class<S>?): S?{
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception){
            null
        }
    }
}