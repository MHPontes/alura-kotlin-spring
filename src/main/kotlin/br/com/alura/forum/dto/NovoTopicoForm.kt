package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm(                              //Classe responsavel por representar as informacoes que chegam do arquivo JSON/Cliente
        @field:NotEmpty(message = "Titulo nao pode ser em branco")
        @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres")      //Messages só serao entregues devido a tratativas do ExceptioHandler
        val titulo: String,
        @field:NotEmpty(message = "Mensagem nao pode ser em branco")
        val mensagem: String,
        @field:NotNull
        val idCurso: Long,
        @field:NotNull
        val idAutor: Long
)
