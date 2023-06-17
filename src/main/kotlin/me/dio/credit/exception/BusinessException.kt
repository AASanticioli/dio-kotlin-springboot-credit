package me.dio.credit.exception

import java.lang.RuntimeException

data class BusinessException(override val message: String) : RuntimeException()