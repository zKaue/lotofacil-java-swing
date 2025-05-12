package telas;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import entidade.Concurso;
import util.DateUtil;
import util.GerarResultado;

public class TelaResultadoConcurso extends JFrame {
    
    public TelaResultadoConcurso(Concurso concurso) {
    	getContentPane().setBackground(new Color(255, 255, 255));
        
        // Título da janela
        setTitle("RESULTADO CONCURSO GOLDENLOTO");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblIdConcurso = new JLabel("ID do Concurso: " + concurso.getId());
        lblIdConcurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIdConcurso.setBounds(20, 61, 300, 20);
        getContentPane().add(lblIdConcurso);
        
        JLabel lblQtdApostas = new JLabel("Quantidade de Apostas: " + concurso.getApostas().size());
        lblQtdApostas.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQtdApostas.setBounds(20, 92, 300, 20);
        getContentPane().add(lblQtdApostas);
        
        JButton btnListarApostas = new JButton("Listar Apostas");
        btnListarApostas.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnListarApostas.setBounds(20, 261, 300, 67);
        btnListarApostas.addActionListener(e -> {
            TelaListarApostas telaApostas = new TelaListarApostas(concurso);
            telaApostas.setVisible(true);
        });
        getContentPane().add(btnListarApostas);
        
        String textoLucroConcurso;
        if (concurso.getNumerosSorteados() == null || concurso.getNumerosSorteados().isEmpty()) {
            textoLucroConcurso = "Sorteio ainda não realizado";
        } else {
            GerarResultado lucro = new GerarResultado();
            double lucroLoteria = lucro.calcularLucroConcurso(concurso, concurso.getApostas());
            String lucroFormatado = String.format("%.2f", lucroLoteria);
            textoLucroConcurso = "R$ " + lucroFormatado;
        }

        JLabel lblLucroConcurso = new JLabel("Lucro do Concurso: "+textoLucroConcurso);
        lblLucroConcurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLucroConcurso.setBounds(20, 123, 431, 20);
        getContentPane().add(lblLucroConcurso);
        
        String numerosSorteados = (concurso.getNumerosSorteados() == null) 
        	    ? "Sorteio ainda não realizado" 
        	    : concurso.getNumerosSorteados().toString();
        
        Date dataSorteio = concurso.getDataSorteio();
        String dataFormatada = DateUtil.converterDateParaData(dataSorteio);

        JLabel lblDataSorteio = new JLabel("Data do Sorteio: " + dataFormatada + " às 20:00h");
        lblDataSorteio.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDataSorteio.setBounds(20, 154, 368, 20);
        getContentPane().add(lblDataSorteio);
        
        JButton btnVoltarMenu = new JButton("Voltar");
        btnVoltarMenu.setBounds(20, 11, 89, 23);
        btnVoltarMenu.addActionListener(e -> {
            TelaGerenciarConcurso form = new TelaGerenciarConcurso();
            form.setVisible(true);
            form.setSize(800, 700);
            form.setLocationRelativeTo(null);
            dispose();

        });
        getContentPane().add(btnVoltarMenu);
       
        String estagioDescricao = concurso.getEstagio().getDescricao();
        JLabel lblSituacao = new JLabel("Situação: " +estagioDescricao);
        lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSituacao.setBounds(20, 185, 350, 20);
        getContentPane().add(lblSituacao);
        
        JLabel lblNumerosSorteados = new JLabel("Números Sorteados: " + numerosSorteados);
        lblNumerosSorteados.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNumerosSorteados.setBounds(20, 216, 651, 20);
        getContentPane().add(lblNumerosSorteados);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
