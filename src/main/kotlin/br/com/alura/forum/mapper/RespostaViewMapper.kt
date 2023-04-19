package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper: // Implements Mapper<Resposta>
    Mapper<Resposta, RespostaView> {

    override fun map(r: Resposta): RespostaView {
        return RespostaView(
            id = r.id,
            mensagem = r.mensagem,
            dataCriacao = r.dataCriacao,
            autor = r.autor,
            topico = r.topico,
            solucao = r.solucao,
        )
    }

}