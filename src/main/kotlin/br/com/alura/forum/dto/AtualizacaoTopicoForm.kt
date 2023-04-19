package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AtualizacaoTopicoForm(      //Criado outro Form de Formulario, significa o que o cliente esta mandando no Front(DTO), pois só será possivel atualizar titulo e mensagem
        @field:NotNull
        val id: Long,
        @field:NotEmpty
        @field:Size(min = 5, max = 100)
        val titulo: String,
        @field:NotEmpty
        val mensagem: String
)
