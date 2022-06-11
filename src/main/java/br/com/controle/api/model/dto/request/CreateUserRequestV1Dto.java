package br.com.controle.api.model.dto.request;

import lombok.Data;

@Data
public class CreateUserRequestV1Dto {

    private String email;
    private String nome;
    private String senha;
}
