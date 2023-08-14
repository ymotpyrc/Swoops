package com.j12a6.swoops.data

actual fun getEnvVariable(name: String): String? {
    return System.getenv(name)
}