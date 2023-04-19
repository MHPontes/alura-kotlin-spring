package br.com.alura.forum.dto

import java.time.LocalDateTime

data class ErrorView(              //DTO para tratamento de erro, devolvendo somente valores necessarios (Campos vieram do retorno do Postman)
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String,
        val message: String?,
        val path: String
)
