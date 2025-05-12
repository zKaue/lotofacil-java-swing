package telas;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import table.model.ApostasFeitasTableModel;
import entidade.Concurso; // Importando a classe Concurso

public class TelaListarApostas extends JFrame {

    public TelaListarApostas(Concurso concurso) {
    	
        setTitle("APOSTAS DO CONCURSO DE ID " + concurso.getId());
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        ApostasFeitasTableModel tableModel = new ApostasFeitasTableModel(concurso);

        JTable tabelaApostas = new JTable(tableModel);

        JTableHeader tableHeader = tabelaApostas.getTableHeader();
        tableHeader.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(tabelaApostas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnFechar.addActionListener(e -> dispose());
        getContentPane().add(btnFechar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
