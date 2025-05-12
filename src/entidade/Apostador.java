package entidade;

import java.io.Serializable;
import java.util.Date;
import util.DateUtil;

public class Apostador implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataNascimento;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private String senha;
	private String sexo;
	private boolean admin;
	
	public Apostador(Long id, Date dataNascimento, String nome, String cpf, String telefone, String email, String senha, String sexo, boolean admin) {
	    this.id = id;
	    this.dataNascimento = dataNascimento;
	    this.nome = nome;
	    this.cpf = cpf;
	    this.telefone = telefone;
	    this.email = email;
	    this.senha = senha;
	    this.sexo = sexo;
	    this.setAdmin(false);
	}

	public Apostador() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void imprimirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Data de Nascimento: " + DateUtil.converterDateParaData(dataNascimento));
        System.out.println("Sexo: " + sexo);
        System.out.println("Admin: " +admin);
    }


}
