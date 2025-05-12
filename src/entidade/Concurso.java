package entidade;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import bd.ConcursoBD;

public class Concurso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
    private Date dataCadastro;
    private Date dataSorteio;
    private List<Aposta> apostas;
    private double valorConcurso;
    private List<Integer> numerosSorteados;
    private boolean acumulado;
    private EstagioConcurso estagio;
    

    public Concurso() {
    	this.id = ConcursoBD.gerarNovoId();
        this.estagio = EstagioConcurso.ABERTO;
        this.apostas = new ArrayList<>();
        this.dataCadastro = new Date();
    }
    
    public void adicionarAposta(Aposta aposta) {
        if (apostas == null) {
            apostas = new ArrayList<>();
        }
        apostas.add(aposta);
    }
    
    public String toString() {
        return "Concurso ID: " + id;
    }
    
    public double calcularArrecadado() {
        double arrecadado = 0.0;
        for (Aposta aposta : apostas) {
            arrecadado += aposta.getValorPago();
        }
        this.valorConcurso = arrecadado;
        return arrecadado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public List<Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(List<Aposta> apostas) {
        this.apostas = apostas;
    }

    public double getValorConcurso() {
        return valorConcurso;
    }

    public void setValorConcurso(double valorConcurso) {
        this.valorConcurso = valorConcurso;
    }

    public List<Integer> getNumerosSorteados() {
        return numerosSorteados;
    }

    public void setNumerosSorteados(List<Integer> numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }

    public boolean isAcumulado() {
        return acumulado;
    }
    
    public static List<Integer> gerarNumerosSorteados(int quantidade) {
        Random random = new Random();
        List<Integer> numeros = new ArrayList<>();

        while (numeros.size() < quantidade) {
            int numero = random.nextInt(25) + 1;
            if (!numeros.contains(numero)) {
                numeros.add(numero);
            }
        }
        return numeros;
    }
    
    public void verificarPremios(Apostador apostador) {
        for (Aposta aposta : apostas) {
            if (aposta.getApostador().equals(apostador)) {
                if (aposta.getValorGanho() > 0) {
                        JOptionPane.showMessageDialog(null, "Parabéns " + aposta.getApostador().getNome() + " você ganhou: " + aposta.getValorGanho() + " no concurso de ID: " +aposta.getIdConcurso() + " Você tem até 90 dias para resgatar o prêmio!");
                }
            }
        }
    }

    public void setAcumulado(boolean acumulado) {
        this.acumulado = acumulado;
    }

    public EstagioConcurso getEstagio() {
        return estagio;
    }

    public void setEstagio(EstagioConcurso estagio) {
        this.estagio = estagio;
    }

    public void fecharConcurso() {
        if (estagio == EstagioConcurso.ABERTO) {
            estagio = EstagioConcurso.FECHADO;
        }
    }

    public void finalizarConcurso() {
        if (estagio == EstagioConcurso.FECHADO) {
            estagio = EstagioConcurso.FINALIZADO;
            
        }
    }

	public enum EstagioConcurso {
        ABERTO("Aberto para apostas"),
        FECHADO("Fechado para apostas"),
        FINALIZADO("Sorteio concluído");

        private final String descricao;

        EstagioConcurso(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}
