package bd;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import entidade.Apostador;
import util.DateUtil;
import util.Serializacao;

public class ApostadorBD implements Serializable{


	private static final long serialVersionUID = 1L;
	private static final File arquivo = new File("apostadores.txt");
	
	public Apostador autenticar(String cpf, String senha) {
		List<Apostador> apostadores = consultar();
		for (Apostador apostadorCadastrado : apostadores) {
			if (apostadorCadastrado.getCpf().equals(cpf) &&
				apostadorCadastrado.getSenha().equals(senha)) {
				return apostadorCadastrado;
			}
		}
		return null;
	}
	
	public boolean verificarLogin(String email, String cpf, String senha) throws ClassNotFoundException, IOException {
		List<Apostador> apostadores = consultar();
	    for (Apostador apostador : apostadores) {
	        if ((apostador.getEmail().equals(email) || apostador.getCpf().equals(cpf)) && apostador.getSenha().equals(senha)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean verificarAdmin(String email, String cpf, String senha) throws ClassNotFoundException, IOException {
		List<Apostador> apostadores = consultar();
	    for (Apostador apostador : apostadores) {
	        if ((apostador.getEmail().equals(email) || apostador.getCpf().equals(cpf)) && apostador.getSenha().equals(senha) && apostador.isAdmin()) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static Apostador retornarApostador(String email, String cpf, String senha) throws ClassNotFoundException, IOException {
		List<Apostador> apostadores = null;
		try {
			apostadores = consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}
        for (Apostador apostador : apostadores) {
        	if ((apostador.getEmail().equals(email) || apostador.getCpf().equals(cpf)) && apostador.getSenha().equals(senha)) {
                return apostador;
            }
        }
		return null;
	}

	public Long gerarNovoId() {
		Long id = 0L;
		List<Apostador> apostadores = consultar();
		for (Apostador apostadorCadastrado : apostadores) {
			if (id < apostadorCadastrado.getId()) {
				id = apostadorCadastrado.getId();
			}
		}
		return ++id;
	}
	
	public Apostador consultarPeloCpf(String cpf) {
		List<Apostador> apostadores = consultar();
		for (Apostador apostadorCadastrado : apostadores) {
			if (apostadorCadastrado.getCpf().equalsIgnoreCase(cpf)) {
				return apostadorCadastrado;
			}
		}
		return null;
	}
	
	public Apostador consultarPeloEmail(String email) {
	    List<Apostador> apostadores = consultar();
	    for (Apostador apostadorCadastrado : apostadores) {
	        if (apostadorCadastrado.getEmail().equalsIgnoreCase(email)) {
	            return apostadorCadastrado;
	        }
	    }
	    return null;
	}
	
	public Apostador consultarPeloTelefone(String telefone) {
	    List<Apostador> apostadores = consultar();
	    for (Apostador apostadorCadastrado : apostadores) {
	        if (apostadorCadastrado.getTelefone().equalsIgnoreCase(telefone)) {
	            return apostadorCadastrado;
	        }
	    }
	    return null;
	}

	public void atualizar(Apostador apostadorAtual) throws Exception {

	    Apostador apostadorCadastradoTelefone = consultarPeloTelefone(apostadorAtual.getTelefone());
	    if (apostadorCadastradoTelefone != null && !apostadorCadastradoTelefone.getId().equals(apostadorAtual.getId())) {
	        throw new Exception("Já existe um apostador cadastrado com o telefone informado.");
	    }
	    
	    Apostador apostadorCadastradoEmail = consultarPeloEmail(apostadorAtual.getEmail());
	    if (apostadorCadastradoEmail != null && !apostadorCadastradoEmail.getId().equals(apostadorAtual.getId())) {
	        throw new Exception("Já existe um apostador cadastrado com o email informado.");
	    }
		
	    List<Apostador> apostadores = consultar();
	    for (int i = 0; i < apostadores.size(); i++) {
	        Apostador apostadorCadastrado = apostadores.get(i);
	        if (apostadorCadastrado.getId().equals(apostadorAtual.getId())) {
	            apostadores.set(i, apostadorAtual);
	            break;
	        }
	    }
	    Serializacao.gravarObjeto(arquivo, apostadores);
	}
	
	
	public void inserir(Apostador apostador) throws Exception {
		
		Apostador apostadorCadastradoCpf = consultarPeloCpf(apostador.getCpf());
	    if (apostadorCadastradoCpf != null) {
	        throw new Exception("Já existe um apostador cadastrado com o CPF informado.");
	    }

	    Apostador apostadorCadastradoTelefone = consultarPeloTelefone(apostador.getTelefone());
	    if (apostadorCadastradoTelefone != null) {
	        throw new Exception("Já existe um apostador cadastrado com o telefone informado.");
	    }
	    
	    Apostador apostadorCadastradoEmail = consultarPeloEmail(apostador.getEmail());
	    if (apostadorCadastradoEmail != null) {
	        throw new Exception("Já existe um apostador cadastrado com o email informado.");
	    }
		
		List<Apostador> apostadores = consultar();
		
//		Long id = gerarNovoId();
//		apostador.setId(id);
		
		apostadores.add(apostador);		
		Serializacao.gravarObjeto(arquivo, apostadores);
	}

	public void remover(Apostador apostador) {
		List<Apostador> apostadores = consultar();
		for (Apostador apostadorCadastrado : apostadores) {
			if (apostadorCadastrado.getId().equals(apostador.getId())) {
				apostadores.remove(apostadorCadastrado);
				break;
			}
		}
		Serializacao.gravarObjeto(arquivo, apostadores);
	}
	
	public static List<Apostador> consultar() {
		List<Apostador> apostadores = (List<Apostador>) Serializacao.recuperarObjeto(arquivo);
		if (apostadores == null) apostadores = new ArrayList<Apostador>();
		return apostadores;
	}
	
	public void imprimirDados() {
		
		List<Apostador> apostadores = consultar();
		
		for (Apostador apostador : apostadores) {
	        System.out.println("ID: " + apostador.getId());
	        System.out.println("Nome: " + apostador.getNome());
	        System.out.println("CPF: " + apostador.getCpf());
	        System.out.println("Telefone: " + apostador.getTelefone());
	        System.out.println("Email: " + apostador.getEmail());
	        System.out.println("Data de Nascimento: " + DateUtil.converterDateParaData(apostador.getDataNascimento()));
	        System.out.println("Sexo: " + apostador.getSexo());
	        System.out.println("Admin: " +apostador.isAdmin());
	        System.out.println("-------------------------------");
		}
		
    }
	
}
