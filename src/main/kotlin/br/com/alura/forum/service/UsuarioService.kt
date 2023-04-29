package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService (private val repository: UsuarioRepository ) {
// Sem o banco de dados utilizavamos como class UsuarioService  (var usuarios: List<Usuario>){}


    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    //Antes de implementarmos persistencia em banco de dados:
//    init {
//        val usuario = Usuario(
//                id = 1,
//                nome = "Ana da Silva",
//                email = "ana@email.com.br"
//        )
//        usuarios = Arrays.asList(usuario)
//    }

//    fun buscarPorId(id: Long): Usuario {
//        return usuarios.stream().filter({
//            c -> c.id == id
//        }).findFirst().get()
//    }

}
