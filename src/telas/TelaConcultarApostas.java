package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import bd.ApostaBD;
import bd.ConcursoBD;
import entidade.Aposta;
import entidade.Apostador;
import entidade.Concurso;
import util.DateUtil;
import java.awt.Font;
import java.awt.Color;

public class TelaConcultarApostas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<Concurso> concursoListModel;
    private DefaultListModel<Aposta> apostaListModel;
    private JList<Concurso> concursoList;
    private JList<Aposta> apostaList;
    private ApostaBD apostaBD;
    private ConcursoBD concursoBD;
    private static Apostador apostadorAtual;
    private JTextArea detalhesApostaTextArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaConcultarApostas frame = new TelaConcultarApostas(apostadorAtual);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaConcultarApostas(Apostador apostadorAtual) throws ClassNotFoundException, IOException {
        setTitle("HISTÓRICO DE APOSTAS GOLDENLOTO");
        this.apostadorAtual = apostadorAtual;
        apostaBD = new ApostaBD();
        concursoBD = new ConcursoBD();
        initialize();
        loadConcursos();

        JButton btnVoltarMenu = new JButton("Voltar");
        btnVoltarMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaMenu form = new TelaMenu(apostadorAtual);
                form.setVisible(true);
                dispose();
            }
        });
        btnVoltarMenu.setBounds(10, 10, 89, 23);
        getContentPane().add(btnVoltarMenu);

        JLabel lblTexto = new JLabel("Concursos Apostados");
        lblTexto.setBounds(10, 55, 142, 14);
        contentPane.add(lblTexto);

        JLabel lblTexto2 = new JLabel("Apostas Realizadas");
        lblTexto2.setBounds(235, 55, 142, 14);
        contentPane.add(lblTexto2);

        JLabel lblTexto3 = new JLabel("Selecione um Concurso Para Visualizar Suas Apostas Realizadas");
        lblTexto3.setBounds(109, 14, 465, 14);
        contentPane.add(lblTexto3);
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        concursoListModel = new DefaultListModel<>();
        contentPane.setLayout(null);
        JScrollPane concursoScrollPane = new JScrollPane();
        concursoScrollPane.setBounds(10, 70, 200, 346);
        concursoList = new JList<>(concursoListModel);
        concursoList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        concursoList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        concursoScrollPane.setViewportView(concursoList);
        contentPane.add(concursoScrollPane);

        apostaListModel = new DefaultListModel<>();
        JScrollPane apostaScrollPane = new JScrollPane();
        apostaScrollPane.setBounds(235, 70, 295, 346);
        apostaList = new JList<>(apostaListModel);
        apostaList.setFont(new Font("Tahoma", Font.PLAIN, 13));
        apostaList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        apostaScrollPane.setViewportView(apostaList);
        contentPane.add(apostaScrollPane);

        // Criando a área de texto para mostrar os detalhes da aposta
        detalhesApostaTextArea = new JTextArea();
        detalhesApostaTextArea.setForeground(new Color(0, 0, 0));
        detalhesApostaTextArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        detalhesApostaTextArea.setBounds(10, 427, 520, 200);
        detalhesApostaTextArea.setEditable(false);
        detalhesApostaTextArea.setLineWrap(true);
        detalhesApostaTextArea.setBorder(new LineBorder(new Color(128, 128, 128)));  // Borda cinza para a área de detalhes da aposta
        detalhesApostaTextArea.setWrapStyleWord(true);
        contentPane.add(detalhesApostaTextArea);

        concursoList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Concurso concursoSelecionado = concursoList.getSelectedValue();
                if (concursoSelecionado != null) {
                    loadApostas(concursoSelecionado);
                }
            }
        });

        apostaList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Aposta apostaSelecionada = apostaList.getSelectedValue();
                if (apostaSelecionada != null) {
                    exibirDetalhesNaTela(apostaSelecionada, concursoList.getSelectedValue());  // Exibe detalhes da aposta na área de texto
                }
            }
        });
    }

    private void loadConcursos() throws ClassNotFoundException, IOException {
        concursoListModel.clear();
        List<Concurso> concursos;
        concursos = concursoBD.consultar();  // Supondo que esse método traga todos os concursos.
        
        if (concursos == null || concursos.isEmpty()) {
            System.out.println(".");
        } else {
            for (Concurso concurso : concursos) {
                boolean apostou = concurso.getApostas().stream()
                                          .anyMatch(aposta -> aposta.getApostador().getCpf().equals(apostadorAtual.getCpf()));
                
                if (apostou) {
                    concursoListModel.addElement(concurso);
                }
            }
        }
    }

    private void loadApostas(Concurso concursoSelecionado) {
        apostaListModel.clear();
        if (concursoSelecionado == null) {
            return;
        }
        List<Aposta> apostas = concursoSelecionado.getApostas();
        if (apostas != null && !apostas.isEmpty()) {
            for (Aposta aposta : apostas) {
                if (aposta.getApostador().getCpf().equals(apostadorAtual.getCpf())) {
                    apostaListModel.addElement(aposta);
                }
            }
        }
    }

    private void exibirDetalhesNaTela(Aposta aposta, Concurso concurso) {
        if (aposta == null) {
            detalhesApostaTextArea.setText("Detalhes não disponíveis.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String dataApostaFormatada = DateUtil.converterDateParaData(aposta.getDataAposta());

        sb.append("ID Aposta: ").append(aposta.getId()).append("\n");
        sb.append("Data da Aposta: ").append(dataApostaFormatada).append("\n");
        sb.append("Números Apostados: ").append(aposta.getNumerosApostados()).append("\n");
        sb.append("Números Acertados: ").append(aposta.getQuantidadeAcertos()).append("\n");
        Double valorPago = aposta.getValorPago();
        sb.append("Valor da Aposta: R$ ").append(String.format("%.2f", valorPago)).append("\n");
        Double valorGanho = aposta.getValorGanho() != null ? aposta.getValorGanho() : 0.00;
        sb.append("Premiação: R$ ").append(String.format("%.2f", valorGanho)).append("\n");

        if (concurso.getEstagio() == Concurso.EstagioConcurso.FINALIZADO) {
            sb.append("Números Sorteados: ").append(concurso.getNumerosSorteados()).append("\n\n");
            
            Date dataSorteio = concurso.getDataSorteio();
            String dataFormatada = DateUtil.converterDateParaData(dataSorteio);
            
            sb.append("Sorteio Realizado Dia ").append(dataFormatada).append(" às 20:00h\n");
        }

        detalhesApostaTextArea.setText(sb.toString());
    }
}
