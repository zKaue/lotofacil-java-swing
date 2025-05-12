package table.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidade.Aposta;
import entidade.Concurso;

public class ApostasFeitasTableModel extends AbstractTableModel {

    private String[] colunas = {"Apostador", "Valor Pago", "Valor Ganho", "Quantidade de Acertos"};
    private List<Aposta> apostas;

    public ApostasFeitasTableModel(Concurso concurso) {
        this.apostas = concurso.getApostas();
        ordenarApostas();
    }

    private void ordenarApostas() {
        Collections.sort(apostas, (a1, a2) -> a2.getQuantidadeAcertos() - a1.getQuantidadeAcertos());
    }

    @Override
    public int getRowCount() {
        return apostas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aposta aposta = apostas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aposta.getApostador().getNome();
            case 1:
            	return "R$ " + String.format("%.2f", aposta.getValorPago());
            case 2:
            	return "R$ " + String.format("%.2f", aposta.getValorGanho());
            case 3:
                return aposta.getQuantidadeAcertos();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void atualizarDados(List<Aposta> novasApostas) {
        this.apostas = novasApostas;
        ordenarApostas();
        fireTableDataChanged();
    }
}
