package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice            //Indicando que a classe ira tratar os erros
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)             //Anotacao para indicar que esse metodo trata a exceptio indicada
    @ResponseStatus(HttpStatus.NOT_FOUND)                   // 404 - 4**
    fun handleNotFound(
            exception: NotFoundException,
            request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.name,
                message = exception.message,
                path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)              //Utilizado principalmente para validar o BeanValidation e pegando mensagem do BeanValidation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
            exception: MethodArgumentNotValidException,
            request: HttpServletRequest
    ): ErrorView {
        val errorMessage = HashMap<String, String?>()                 //Mapa para pegar nome do campo com erro + mensagem
        exception.bindingResult.fieldErrors.forEach{
            e -> errorMessage.put(e.field, e.defaultMessage)
        }
        return ErrorView(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                message = errorMessage.toString(),
                path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)            //Exception geral para erro 500 por exemplo para nao devovler o trace ao cliente e ser mais legivel
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
            exception: Exception,
            request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = exception.message,
                path = request.servletPath
        )
    }

}