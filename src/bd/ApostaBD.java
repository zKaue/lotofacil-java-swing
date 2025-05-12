package bd;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entidade.Aposta;
import util.Serializacao;

public class ApostaBD implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private static final File arquivo = new File("apostas.txt");
    
    
    
    public static int gerarNovoId() {
		int id = 0;
		List<Aposta> apostas = consultar();
		for (Aposta novaAposta : apostas) {
			if (id < novaAposta.getId()) {
				id = novaAposta.getId();
			}
		}
		return ++id;
	}
    
    public boolean temApostaDuplicada(Aposta apostaNova, int idConcurso) {
        List<Aposta> apostas = consultar();

        for (Aposta apostaExistente : apostas) {
        	
            List<Integer> numerosApostadosExistente = apostaExistente.getNumerosApostados();
            List<Integer> numerosApostadosNova = apostaNova.getNumerosApostados();
            
            Collections.sort(numerosApostadosExistente);
            Collections.sort(numerosApostadosNova);
            
            boolean numerosIguais = numerosApostadosExistente.equals(numerosApostadosNova);
            boolean apostadorIgual = apostaExistente.getApostador().getCpf().equals(apostaNova.getApostador().getCpf());
            boolean concursoIgual = apostaExistente.getIdConcurso() == idConcurso;
            
            if (numerosIguais && apostadorIgual && concursoIgual) {
                return true;
            }
        }
        return false;
    }
    
    public static List<Aposta> consultar() {
		List<Aposta> apostas = (List<Aposta>) Serializacao.recuperarObjeto(arquivo);
		if (apostas == null) apostas = new ArrayList<Aposta>();
		return apostas;
	}
    
	public void inserir(Aposta aposta) {
			
		List<Aposta> apostas = consultar();
		apostas.add(aposta);
		Serializacao.gravarObjeto(arquivo, apostas);
		
		System.out.println("Aposta feita com sucesso!");
	}
	
	public void imprimirApostas() throws ClassNotFoundException, IOException {
		List<Aposta> apostas = consultar();
        if (apostas.isEmpty()) {
            System.out.println("Nenhum concurso cadastrado.");
            return;
        }		
        for (Aposta aposta : apostas) {
			System.out.println("------------------------------");
			System.out.println("ID Aposta: " + aposta.getId());
			System.out.println("Apostador: " + (aposta.getApostador() != null ? aposta.getApostador().getNome() : "Desconhecido"));
			System.out.println("Data da Aposta: " + (aposta.getDataAposta() != null ? aposta.getDataAposta() : "Sem data"));
			System.out.println("Valor da Aposta: " + aposta.getValorPago());
			System.out.println("Números Apostados: " + (aposta.getNumerosApostados() != null ? aposta.getNumerosApostados() : "Nenhum número apostado"));
			System.out.println("Notificado?" + aposta.isFoiNotificado());
			System.out.println("------------------------------");
			}
		}
	
	public static File getArquivo() {
	    return arquivo;
	}
    
	public static void atualizarAposta(Aposta apostaAtualizada) {
	    List<Aposta> apostas = ApostaBD.consultar();
	    for (int i = 0; i < apostas.size(); i++) {
	        Aposta aposta = apostas.get(i);
	        if (aposta.getId() == apostaAtualizada.getId()) {
	            apostas.set(i, apostaAtualizada);
	            Serializacao.gravarObjeto(arquivo, apostas);
	            System.out.println("Aposta com ID " + apostaAtualizada.getId() + " foi atualizada e salva.");
	            break;
	        }
	    }
	}
    
}

