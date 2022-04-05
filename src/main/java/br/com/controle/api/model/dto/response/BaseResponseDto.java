package br.com.controle.api.model.dto.response;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class BaseResponseDto {

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime created;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime changed;

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getChanged() {
		return changed;
	}

	public void setChanged(LocalDateTime changed) {
		this.changed = changed;
	}

}
