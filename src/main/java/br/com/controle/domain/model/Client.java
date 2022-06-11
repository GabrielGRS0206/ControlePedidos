package br.com.controle.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "client")
public class Client extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	@Size(max = 120)
	@Column(length = 120,nullable = false)
	private String name;

	@NotBlank
	@NotNull
	@Size(max = 16)
	@Column(length = 16)
	private String document;

	@Size(max = 14)
	@Column(length = 14)
	private String contact;

	@Size(max = 12)
	@Column(length = 12)
	private String cep;

	@Size(max = 10)
	@Column(length = 10)
	private String number;

	@Size(max = 255)
	private String complement;

	@Size(max = 255)
	private String proximity;

	@Size(max = 255)
	private String observation;

	@Column(length = 80)
	private String street;
	
	@Column(length = 12)
	private String district;

	@Column(length = 12)
	private String city;

	public Client(Long idClient) {
		this.id = idClient;
	}

	public Client() {
		super();
	}

}

