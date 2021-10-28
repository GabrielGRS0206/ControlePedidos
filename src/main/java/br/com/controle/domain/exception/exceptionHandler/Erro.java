package br.com.controle.domain.exception.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Erro {

	private Integer status;
	private LocalDateTime dataHora;
	private String message;
	private String mensageError;
	private List<Field> fields;
	
	public Erro() {
		super();
	}
	
	public Erro(Integer status, LocalDateTime dataHora, String message, List<Field> fields) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.message = message;
		this.fields = fields;
	}
	
	public Erro(Integer status, LocalDateTime dataHora, String message,String messageErro, List<Field> fields) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.message = message;
		this.fields = fields;
		this.mensageError = messageErro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMensageError() {
		return mensageError;
	}

	public void setMensageError(String mensageError) {
		this.mensageError = mensageError;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	

}
