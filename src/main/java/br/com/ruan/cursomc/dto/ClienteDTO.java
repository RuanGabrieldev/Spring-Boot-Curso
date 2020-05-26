package br.com.ruan.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.ruan.cursomc.model.ClienteModel;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchiemento obrigatório")
	@Length(min = 3, max = 150, message = "O nome deve ter um tamnho de 3 a 150 letras")
	private String nome;
	
	@NotEmpty(message = "Preenchiemento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ClienteDTO(ClienteModel obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
