package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bd.ConcursoBD;
import entidade.Concurso;
import util.DateUtil;
import util.ValidadorDeData;

public class TelaGerenciarConcurso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCadastrarConcurso = new JButton("Cadastrar Concurso");
    private JButton btnExcluirConcurso = new JButton("Excluir Concurso");

    
    DefaultListModel<String> modelConcurso = new DefaultListModel<>();
    JList<String> listaConcursos = new JList<>(modelConcurso);
    JScrollPane scrollPane = new JScrollPane(listaConcursos);

    private boolean concursoSelecionado = false;
    

    public TelaGerenciarConcurso() {
        setTitle("CADASTRAR CONCURSO GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        setBounds(435, -37, 1300, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);   
        lblColocarFoco.requestFocusInWindow();

        JComboBox<String> diaComboBox = new JComboBox<>(new String[]{"Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
        diaComboBox.setBounds(10, 113, 80, 30);
        getContentPane().add(diaComboBox);

        JComboBox<String> mesComboBox = new JComboBox<>(new String[]{"Mês", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});
        mesComboBox.setBounds(100, 113, 80, 30);
        getContentPane().add(mesComboBox);

        JComboBox<String> anoComboBox = new JComboBox<>();
        anoComboBox.setBounds(190, 113, 80, 30);
        anoComboBox.addItem("Ano");
        for (int ano = 2024; ano <= 2030; ano++) {
            anoComboBox.addItem(String.valueOf(ano));
        }
        getContentPane().add(anoComboBox);

        carregarConcursos();
        
        

        btnCadastrarConcurso.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String diaSelecionado = (String) diaComboBox.getSelectedItem();
		        String mesSelecionado = (String) mesComboBox.getSelectedItem();
		        String anoSelecionado = (String) anoComboBox.getSelectedItem();

		        if (diaSelecionado.equals("Dia") || mesSelecionado.equals("Mês") || anoSelecionado.equals("Ano")) {
		            JOptionPane.showMessageDialog(null, "Por favor, informe a data do sorteio!");
		            return;
		        }

		        String dataFormatada = diaSelecionado + "/" + DateUtil.getMesNumerico(mesSelecionado) + "/" + anoSelecionado;
		        Date dataSorteio = util.DateUtil.converterStringParaData(dataFormatada);

		        boolean dataValida = ValidadorDeData.verificarDataFutura(diaSelecionado, mesSelecionado, anoSelecionado);

		        if (!dataValida) {
		            JOptionPane.showMessageDialog(null, "O concurso deve ter ao menos um dia de antecedência!");
		            return;
		        }
		        		        
		        
		        List<Concurso> concursosExistentes = ConcursoBD.getTodosConcursos();
		        for (int i = 0; i < concursosExistentes.size() - 1; i++) {
		            Date dataAtual = concursosExistentes.get(i).getDataSorteio();
		            Date proximaData = concursosExistentes.get(i + 1).getDataSorteio();
		            if (dataAtual.before(dataSorteio) && dataSorteio.before(proximaData)) {
		                JOptionPane.showMessageDialog(null, "Não é possível cadastrar um concurso nesta data, pois ela está entre dois concursos já existentes!");
		                return;
		            }
		        }
		        
		        List<Concurso> concursos = ConcursoBD.getTodosConcursos();
		        if (!concursos.isEmpty()) {
		            Concurso ultimoConcurso = concursos.get(concursos.size() - 1);
		            if (dataSorteio.before(ultimoConcurso.getDataSorteio())) {
		                JOptionPane.showMessageDialog(null, 
		                    "A data do novo concurso não pode ser anterior à data do último concurso cadastrado (" 
		                    + DateUtil.converterDateParaData(ultimoConcurso.getDataSorteio()) + ")");
		                return;
		            }
		        }

		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(dataSorteio);
		        calendar.set(Calendar.HOUR_OF_DAY, 20);
		        calendar.set(Calendar.MINUTE, 0);
		        calendar.set(Calendar.SECOND, 0);
		        calendar.set(Calendar.MILLISECOND, 0);
		        dataSorteio = calendar.getTime();

		        if (!ConcursoBD.dataValidaConcurso(dataSorteio)) {
		            JOptionPane.showMessageDialog(null, "Já existe um concurso marcado para essa data!");
		            return;
		        }
		        
		       
		        
		        Concurso concurso = new Concurso();
		        concurso.setDataSorteio(dataSorteio);
		        ConcursoBD concursoBD = new ConcursoBD();    
		        concursoBD.adicionarConcurso(concurso);
		        carregarConcursos();
		        JOptionPane.showMessageDialog(null, "Concurso cadastrado com sucesso!");

		    }
		});

        
        
        scrollPane.setBounds(10, 220, 760, 300);
        getContentPane().add(scrollPane);

        listaConcursos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedConcurso = listaConcursos.getSelectedValue();
                    if (selectedConcurso != null) {
                        concursoSelecionado = true;
                        btnExcluirConcurso.setEnabled(true);
                    } else {
                        concursoSelecionado = false;
                        btnExcluirConcurso.setEnabled(false);
                    }
                }
            }
        });

        listaConcursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedConcurso = listaConcursos.getSelectedValue();
                    if (selectedConcurso != null) {
                        String idConcurso = selectedConcurso.split("  |  ")[0].replace("Concurso ID ", "");
                        Concurso concurso = ConcursoBD.getConcursoById(Integer.parseInt(idConcurso));
                        TelaResultadoConcurso form = new TelaResultadoConcurso(concurso);
                        form.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        btnExcluirConcurso.addActionListener(e -> {
            if (concursoSelecionado) {
                String selectedConcurso = listaConcursos.getSelectedValue();
                if (selectedConcurso != null) {
                    String idConcurso = selectedConcurso.split("  |  ")[0].replace("Concurso ID ", "");
                    int id = Integer.parseInt(idConcurso);

                    Concurso concurso = ConcursoBD.getConcursoById(id);
                    
                    if (concurso != null) {
                        if (concurso.getApostas().size() > 0) {
                            JOptionPane.showMessageDialog(getContentPane(), "Não é possível excluir o concurso, pois existem apostas associadas a ele.");
                            return;
                        }
                        
                        Object[] options = {"Sim", "Não"};
                        int resposta = JOptionPane.showOptionDialog(getContentPane(),
                                "Tem certeza de que deseja excluir este concurso?", 
                                "Confirmação", 
                                JOptionPane.DEFAULT_OPTION, 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, options, options[1]);
                        
                        if (resposta == JOptionPane.YES_OPTION) {
                            try {
                                ConcursoBD.excluirConcurso(id);
                                carregarConcursos();
                                JOptionPane.showMessageDialog(getContentPane(), "Concurso excluído com sucesso!");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(getContentPane(), "Erro ao excluir concurso: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });


        btnCadastrarConcurso.setBounds(10, 154, 260, 50);
        contentPane.add(btnCadastrarConcurso);
        btnCadastrarConcurso.setEnabled(true);

        btnExcluirConcurso.setBounds(280, 154, 260, 50);
        btnExcluirConcurso.setEnabled(false);
        contentPane.add(btnExcluirConcurso);

        JLabel lblMsgDiaSorteio = new JLabel("Selecione a data do sorteio");
        lblMsgDiaSorteio.setBounds(10, 90, 282, 14);
        contentPane.add(lblMsgDiaSorteio);

        JButton btnVoltarMenu = new JButton("Voltar");
        btnVoltarMenu.addActionListener(e -> {
            TelaMenuAdmin form = new TelaMenuAdmin();
            form.setVisible(true);
            dispose();
        });
        btnVoltarMenu.setBounds(10, 10, 89, 23);
        getContentPane().add(btnVoltarMenu);
    }

    private void carregarConcursos() {
        modelConcurso.clear();
        
        List<Concurso> concursos = ConcursoBD.getTodosConcursos();
        
        for (Concurso concurso : concursos) {
            modelConcurso.addElement("Concurso ID " + concurso.getId() + "  |  " + "Data Sorteio: " + 
        DateUtil.converterDateParaData(concurso.getDataSorteio()) + "  |  " + "Valor Arrecadado: R$" +concurso.calcularArrecadado());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaGerenciarConcurso frame = new TelaGerenciarConcurso();
                frame.setSize(800, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}