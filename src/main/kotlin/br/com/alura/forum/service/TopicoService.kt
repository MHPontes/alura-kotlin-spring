package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
//        private var topicos: List<Topico> = ArrayList(),          //Pode considerar o Construtor
        private val repository: TopicoRepository,
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico nao encontrado!"     //Declarando Exception
) {

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable        //Paginacao
    ): Page<TopicoView> {                    //Atualizado para que possamos fazer requisicao listando pelo nome do curso
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

//    fun buscarPorId(id: Long): TopicoView {
//        val topico = topicos.stream().filter { t ->
//            t.id == id
//        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
//        return topicoViewMapper.map(topico)
//    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

//    fun cadastrar(form: NovoTopicoForm): TopicoView {
//        val topico = topicoFormMapper.map(form)
//        topico.id = topicos.size.toLong() + 1             //Simulando increment do banco de dados, adicionando sempre um id incremental
//        topicos = topicos.plus(topico)
//        return topicoViewMapper.map(topico)
//    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

//    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
//        val topico = topicos.stream().filter { t ->
//            t.id == form.id                                    //Buscando ID a ser atualizado o ID sera passado no BOdy
//        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
//        val topicoAtualizado = Topico(   //Por estar instanciando novo Topico e necessario passar as informacoes de Topico
//                id = form.id,            // Form somente as informacoes atualizadas, as demais nao pois nao modificamos
//                titulo = form.titulo,
//                mensagem = form.mensagem,
//                autor = topico.autor,
//                curso = topico.curso,
//                respostas = topico.respostas,
//                status = topico.status,
//                dataCriacao = topico.dataCriacao
//        )
//        topicos = topicos.minus(topico).plus(topicoAtualizado)       //removendo com "minus" e adicionando com "plus" novo toico
//        return topicoViewMapper.map(topicoAtualizado)
//    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

//    fun deletar(id: Long) {
//        val topico = topicos.stream().filter { t ->
//            t.id == id
//        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)} //Lançando Exception caso nao encontre o ID
//        topicos = topicos.minus(topico)
//    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }

}