package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Service

@Service
class RespostaService (
    private var respostas: List<Resposta> = ArrayList(),
    private val respostaViewMapper: RespostaViewMapper,
)
{
fun atualizar(form: AtualizacaoRespostaForm): RespostaView {
    val resposta = respostas.stream().filter { r ->
        r.id == form.id
    }.findFirst().get()
    val respostaAtualizada = Resposta(
        id = form.id,
        mensagem = form.mensagem,
        autor = resposta.autor,
        topico = resposta.topico,
        dataCriacao = resposta.dataCriacao,
        solucao = resposta.solucao
    )
    respostas = respostas.minus(resposta).plus(respostaAtualizada)
    return respostaViewMapper.map(respostaAtualizada)
}

fun deletar(id: Long) {
    val resposta = respostas.stream().filter { r ->
        r.id == id
    }.findFirst().get()
    respostas = respostas.minus(resposta)
}
}