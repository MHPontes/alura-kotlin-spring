package br.com.alura.forum.dto

import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import java.time.LocalDateTime

data class RespostaView(
    val id: Long?,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val autor: Usuario,
    val topico: Topico,
    val solucao: Boolean
)