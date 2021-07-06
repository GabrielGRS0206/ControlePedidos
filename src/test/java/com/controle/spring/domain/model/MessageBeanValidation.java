package com.controle.spring.domain.model;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class MessageBeanValidation<T> {

	public boolean verifyMessage(Set<ConstraintViolation<T>> violacoes, String message) {
		boolean retorno = false;
		for (ConstraintViolation<T> violacao : violacoes) {
			if (messageViolation(violacao).equals(message)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	private String messageViolation(ConstraintViolation<T> violacao) {
		System.out.println(violacao.getMessage().replace("{0}", violacao.getPropertyPath().toString()));
		return violacao.getMessage().replace("{0}", violacao.getPropertyPath().toString());
	}

}
