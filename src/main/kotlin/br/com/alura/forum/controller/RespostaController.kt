package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.service.RespostaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
class RespostaController(private val service: RespostaService) {

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoRespostaForm): ResponseEntity<RespostaView> {
        val respostaView = service.atualizar(form)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping("/{idResposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable idResposta: Long) {
        service.deletar(idResposta)
    }
}