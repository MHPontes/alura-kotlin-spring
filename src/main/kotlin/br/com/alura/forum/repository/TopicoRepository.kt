package br.com.alura.forum.repository

import br.com.alura.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

//Herdando metodos da interface JpaRepository para o banco de dados (SAVE, DELETE etc)
interface TopicoRepository: JpaRepository<Topico, Long> {
}