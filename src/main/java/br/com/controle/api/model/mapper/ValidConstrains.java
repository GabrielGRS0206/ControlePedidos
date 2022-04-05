package br.com.controle.api.model.mapper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import br.com.controle.domain.exception.business.DtoInvalidException;

@Component
public class ValidConstrains<E> {

	protected void isValid(E entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<E>> violacoes = validator.validate(entity);
		if (!violacoes.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			for (ConstraintViolation<E> violacao : violacoes) {
				builder.append(violacao.getPropertyPath().toString()+"|");
				builder.append(violacao.getMessage()+"|");
			}
			throw new DtoInvalidException(builder);
		}
	}
}
