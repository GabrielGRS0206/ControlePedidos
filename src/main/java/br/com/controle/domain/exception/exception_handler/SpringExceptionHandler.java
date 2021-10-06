package br.com.controle.domain.exception.exception_handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.DtoInvalidException;
import br.com.controle.domain.exception.business.EntityInUseException;
import br.com.controle.domain.exception.business.EntityNotFoundException;
import br.com.controle.domain.utils.SpringUtils;

@ControllerAdvice
public class SpringExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String INVALID = "Campos inválidos";
	private static final String RESOURCE_NOT_FOUND = "Recurso não encontrado";

	@Autowired
	private MessageSource messageSource;

	public static final String MSG_ERRO_PATTERN = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";

	public static final String MSG_ERRO_ENTITY_IN_USE = "Não foi possível processar " + "essa solicitação.";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorType problemType = ErrorType.ERRO_DE_SISTEMA;
		String detail = ExceptionUtils.getMessage(ex);

		Erro problem = problem(status, problemType, detail);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ArrayList<Field> fields = new ArrayList<>();
		Field field = null;

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			field = new Field(name, message);
			fields.add(field);
		}

		Erro problema = new Erro();
		problema.setStatus(status.value());
		problema.setMessage(INVALID);
		problema.setDataHora(LocalDateTime.now());
		problema.setFields(fields);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler({ DtoInvalidException.class })
	public ResponseEntity<Object> handleEntidadeValidaException(DtoInvalidException ex, WebRequest request) {
		String messageUser = ex.getMessage(); // Exception usada quando utiliza dto
		String messageDev = ExceptionUtils.getRootCauseMessage(ex);

		Erro error = new Erro();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(INVALID);
		error.setDataHora(LocalDateTime.now());

		List<Field> fields = Arrays.asList(new Field(messageUser, messageDev));
		error.setFields(fields);

		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EntityInUseException.class })
	public ResponseEntity<Object> handleEntidadeEmUsoException(EntityInUseException ex, WebRequest request) {
		Erro erro = problem(HttpStatus.CONFLICT, ErrorType.ENTIDADE_EM_USO, MSG_ERRO_ENTITY_IN_USE,
				ExceptionUtils.getMessage(ex));

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntityNotFoundException ex, WebRequest request) {

		Erro problema = new Erro();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setMessage(RESOURCE_NOT_FOUND);
		problema.setDataHora(LocalDateTime.now());
		problema.setMensageError(ExceptionUtils.getRootCauseMessage(ex));

		return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private Erro problem(HttpStatus status, ErrorType problemType, String detail) {
		return problem(status, problemType, detail, null);
	}

	private Erro problem(HttpStatus status, ErrorType problemType, String detail, String messageError) {

		Erro erro = null;

		if (!SpringUtils.isEmpty(messageError)) {
			erro = new Erro(status.value(), LocalDateTime.now(), detail, null);
		} else {
			erro = new Erro(status.value(), LocalDateTime.now(), detail, messageError, null);
		}

		return erro;
	}

}
