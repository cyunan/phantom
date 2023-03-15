package com.cyn.base.autoservice

import java.util.ServiceLoader

object BaseServiceLoader {
    fun <S> load(service: Class<S>?): S?{
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception){
            null
        }
    }
}