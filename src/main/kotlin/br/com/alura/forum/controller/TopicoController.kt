package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController                                   //Recebemos FORM para cadastrar, esse FORM e convertido em model pra salvar, e esse MODEL e convertido em view para retornar ao client. Essa conversoes ficam dentro do MAPPER.
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
            @RequestBody @Valid form: NovoTopicoForm,       //Como boa pretica, estamos utilizando ResponseEntity para apresentarmos uma resposta ao Cliente
            uriBuilder: UriComponentsBuilder //Monta URI ex: localhost:8080             // neste caso estamos retornando 201 Created com body de topicoView
    ): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping            //Utilizado principalmente pra atualizar uma entidade inteira  //PATCH atualiza de maneira parcial
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {   //O form e diferente pois queremos limitar as atualizacoes para o cliente
        val topicoView = service.atualizar(form)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional

//    fun deletar(@PathVariable id: Long):ResponseEntity<String> {             //Comentados sao outra maneira de fazer ou retornar Unit = Vazio ou String
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
//        return ResponseEntity.noContent().build()
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Registro removido")
    }

}