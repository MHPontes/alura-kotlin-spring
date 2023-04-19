package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {         //Classe de conversao, recebendo um topico

    override fun map(t: Topico): TopicoView {
        return TopicoView(
                id = t.id,
                titulo = t.titulo,
                mensagem = t.mensagem,
                dataCriacao = t.dataCriacao,
                status = t.status
        )
    }
}