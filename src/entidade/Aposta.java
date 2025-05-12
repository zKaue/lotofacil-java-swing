package entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Aposta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Apostador apostador;
	private Date dataAposta;
	private List<Integer> numerosApostados;
	private int quantidadeAcertos;
	private Double valorGanho;
	private Double valorPago;
	private int idConcurso;
	private boolean foiNotificado;
	
	
	
	public Aposta(int id, Apostador apostador, Date dataAposta, List<Integer> numerosApostados, Double valorPago, int idConcurso) {
		this.id = id;
		this.apostador = apostador;
		this.dataAposta = dataAposta;
		this.numerosApostados = numerosApostados;
		this.valorPago = valorPago;
		this.setIdConcurso(idConcurso);
		this.valorGanho = 0.0;
		this.foiNotificado = false;
	}
	
	public String toString() {
	    return "Aposta ID: " + id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Apostador getApostador() {
		return apostador;
	}
	public void setApostador(Apostador apostador) {
		this.apostador = apostador;
	}
	public Date getDataAposta() {
		return dataAposta;
	}
	public void setDataAposta(Date dataAposta) {
		this.dataAposta = dataAposta;
	}
	public List<Integer> getNumerosApostados() {
		return numerosApostados;
	}
	public void setNumerosApostados(List<Integer> numerosApostados) {
		this.numerosApostados = numerosApostados;
	}
	public int getQuantidadeAcertos() {
		return quantidadeAcertos;
	}
	public void setQuantidadeAcertos(int quantidadeAcertos) {
		this.quantidadeAcertos = quantidadeAcertos;
	}
	public Double getValorGanho() {
		return valorGanho;
	}
	public void setValorGanho(Double valorGanho) {
		this.valorGanho = valorGanho;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdConcurso() {
		return idConcurso;
	}

	public void setIdConcurso(int idConcurso) {
		this.idConcurso = idConcurso;
	}

	public boolean isFoiNotificado() {
		return foiNotificado;
	}

	public void setFoiNotificado(boolean foiNotificado) {
		this.foiNotificado = foiNotificado;
	}

	

}
