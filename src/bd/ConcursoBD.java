package bd;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entidade.Concurso;
import util.Serializacao;

public class ConcursoBD implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final File arquivo = new File("concursos.txt");
	private List<Concurso> concursos;
	
	public static List<Concurso> consultar() {
		List<Concurso> concursos = (List<Concurso>) Serializacao.recuperarObjeto(arquivo);
		if (concursos == null) concursos = new ArrayList<Concurso>();
		return concursos;
	}
	
	public void adicionarConcurso(Concurso concurso) {
        List<Concurso> concursos = consultar();
        concursos.add(concurso);
        
        Serializacao.gravarObjeto(arquivo, concursos);
		
		System.out.println("Concurso feito com sucesso!");
		
    }
	
	public Concurso getConcursoAbertoComMenorId() {
        Concurso concursoComMenorId = null;

        List<Concurso> concursos = ConcursoBD.consultar();
        
        for (Concurso concurso : concursos) {
            if (concurso.getEstagio() == Concurso.EstagioConcurso.ABERTO) {
                if (concursoComMenorId == null || concurso.getId() < concursoComMenorId.getId()) {
                    concursoComMenorId = concurso;
                }
            }
        }

        return concursoComMenorId;
    }
	
	public static void alterar(int idSorteio, Concurso concursoAlterado) {
	    List<Concurso> concursos = consultar();
	    
	    boolean alterado = false;
	    for (int i = 0; i < concursos.size(); i++) {
	        if (concursos.get(i).getId() == idSorteio) {
	            concursos.set(i, concursoAlterado);
	            alterado = true;
	            break;
	        }
	    }
	    
	    if (alterado) {
	        Serializacao.gravarObjeto(arquivo, concursos);
	    } else {
	        System.out.println("Concurso com ID " + idSorteio + " não encontrado para alteração.");
	    }
	}
	
	public static void excluirConcurso(int idSorteio) throws IOException, ClassNotFoundException {
	    List<Concurso> concursos = consultar();

	    for (int i = 0; i < concursos.size(); i++) {
	        Concurso concurso = concursos.get(i);
	        if (concurso.getId() == idSorteio) {
	            concursos.remove(i);
	            break;
	        }
	    }
	    Serializacao.gravarObjeto(arquivo, concursos);
	}
	
	
	
	public static Concurso getConcursoById(int id) {
		List<Concurso> concursos = consultar();
        for (Concurso concurso : concursos) {
            if (concurso.getId() == id) {
                return concurso;
            }
        }
        return null;
    }
	
	public static List<Concurso> getTodosConcursos() {
		List<Concurso> concursos = consultar();
        return concursos;
    }
	
	
	
	public static int gerarNovoId() {
		int id = 0;
	    List<Concurso> concursos = ConcursoBD.consultar();
	    for (Concurso concursoCadastrado : concursos) {
	        if (id < concursoCadastrado.getId()) {
	            id = concursoCadastrado.getId();
	        }
	    }
	    return ++id;
	}
	
	public static boolean dataValidaConcurso(Date dataSorteio) {
		List<Concurso> concursos = consultar();
		
		for (Concurso concurso : concursos) {
			if(concurso.getDataSorteio().equals(dataSorteio)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void imprimirConcursos() {
	    List<Concurso> concursos = consultar();
	    if (concursos.isEmpty()) {
	        System.out.println("Nenhum concurso encontrado.");
	    } else {
	        for (Concurso concurso : concursos) {
	            System.out.println("ID: " + concurso.getId());
	            System.out.println("Data de Cadastro: " + concurso.getDataCadastro());
	            System.out.println("Data de Sorteio: " + concurso.getDataSorteio());
	            System.out.println("Valor do Concurso: " + concurso.getValorConcurso());
	            System.out.println("Acumulado: " + concurso.isAcumulado());
	            System.out.println("Estágio: " + concurso.getEstagio().getDescricao());
	            System.out.println("--------------------------------------------------");
	        }
	    }
	}
	
	public static void atualizarConcurso(Concurso concursoAtualizado) {
	    List<Concurso> concursos = ConcursoBD.consultar();
	    for (int i = 0; i < concursos.size(); i++) {
	        Concurso concurso = concursos.get(i);
	        if (concurso.getId() == concursoAtualizado.getId()) {
	            concursos.set(i, concursoAtualizado);
	            Serializacao.gravarObjeto(arquivo, concursos);
	            System.out.println("Concurso com ID " + concursoAtualizado.getId() + " foi atualizado e salvo.");
	            break;
	        }
	    }
	}

}
