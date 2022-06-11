package br.com.controle.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.controle.domain.utils.Mask;
import lombok.Data;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ClientResponseV1Dto extends BaseResponseDto {

	private long id;
	private String name;
	private String document;
	private String contact;
	private String cep;
	private String district;
	private String city;
	private String street;
	private String complement;
	private String proximity;
	private String observation;
	private String number;

	public String getDocument() {
		return Mask.formatMaskIntelligent(document);
	}
}
