package com.ymotpyrc.swoops.data

actual fun getEnvVariable(name: String): String? {
    return System.getenv(name)
}