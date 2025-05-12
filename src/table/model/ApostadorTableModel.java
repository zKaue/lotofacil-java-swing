package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidade.Apostador;
import util.DateUtil;

public class ApostadorTableModel extends AbstractTableModel {

	// Nome das colunas
	private String[] colunas = {"CPF", "Nome", "Data Nasc"};
	
	// Conteúdo que será exibido na coluna
	private List<Apostador> apostadores;
	
	public List<Apostador> getApostadores() {
		return apostadores;
	}

	public void setApostadores (List<Apostador> apostadores) {
		this.apostadores = apostadores;
	}

	public ApostadorTableModel() {
		this.apostadores = new ArrayList<>();
	}
	
	// Método que retorna o número de linhas da tabela
	@Override
	public int getRowCount() {
		return apostadores.size();
	}

	// Método que retorna o número de colunas da tabela
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	// Método que retorna o nome da coluna de um determinado index
    @Override
    public String getColumnName(int columnIndex){
      return colunas[columnIndex];
    }

	// Método que permite ou não a edição de uma célula
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void limpar() {
        apostadores.clear();
        fireTableDataChanged();
    }
    
    public void adicionar(Apostador apostador) {
        apostadores.add(apostador);
        fireTableDataChanged();
    }

    public void remover(Apostador apostador) {
    	apostadores.remove(apostador);
    	fireTableDataChanged();
    }
    
    public void remover(int indice) {
    	apostadores.remove(indice);
    	fireTableDataChanged();
    }
    
	// Método que retorna o valor de uma célula da tabela
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        Apostador apostador = apostadores.get(rowIndex);
        String conteudoCelula = null;
        switch(columnIndex){
            case 0:
            	conteudoCelula = apostador.getCpf();
            	break;
            case 1:
            	conteudoCelula = apostador.getNome();
            	break;
            case 2 :
            	conteudoCelula = DateUtil.converterDateParaData(apostador.getDataNascimento());
            	break;
            default:
            	System.err.println("Índice inválido");
        }
        return conteudoCelula;
	}



}
