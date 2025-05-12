package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bd.ConcursoBD;
import entidade.Aposta;
import entidade.Concurso;
import entidade.Concurso.EstagioConcurso;
import util.GerarResultado;
import java.awt.Color;


public class TelaGerarSorteio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<Concurso> concursoDropdown;
    private JButton btnGerarSorteio;
    private GerarResultado resultado;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaGerarSorteio frame = new TelaGerarSorteio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private List<Integer> abrirDialogoManualSimplificado() {
        String entrada = JOptionPane.showInputDialog(
                null,
                "Digite 15 números distintos de 1 a 25 separados por vírgulas:",
                "Sorteio Manual",
                JOptionPane.PLAIN_MESSAGE);

        if (entrada == null) return null;

        String[] numerosStr = entrada.split(",");
        Set<Integer> numeros = new HashSet<>();

        try {
            for (String num : numerosStr) {
                int numero = Integer.parseInt(num.trim());
                if (numero < 1 || numero > 25) {
                    JOptionPane.showMessageDialog(null, "Os números devem estar entre 1 e 25!");
                    return abrirDialogoManualSimplificado();
                }
                if (!numeros.add(numero)) {
                    JOptionPane.showMessageDialog(null, "Os números não podem se repetir!");
                    return abrirDialogoManualSimplificado();
                }
            }

            if (numeros.size() != 15) {
                JOptionPane.showMessageDialog(null, "Você deve informar exatamente 15 números!");
                return abrirDialogoManualSimplificado();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira apenas números válidos!");
            return abrirDialogoManualSimplificado();
        }

        return new ArrayList<>(numeros);
    }

    public TelaGerarSorteio() {
        resultado = new GerarResultado();

        setTitle("GERAR SORTEIO GOLDENLOTO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 130);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnVoltarMenu = new JButton("Voltar");
        btnVoltarMenu.setBounds(10, 10, 89, 23);
        btnVoltarMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaMenuAdmin form = new TelaMenuAdmin();
                form.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVoltarMenu);

        JLabel lblConcurso = new JLabel("Selecione o Concurso:");
        lblConcurso.setBackground(new Color(0, 0, 0));
        lblConcurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConcurso.setBounds(10, 40, 200, 30);
        contentPane.add(lblConcurso);

        concursoDropdown = new JComboBox<>();
        carregarConcursos();
        concursoDropdown.setBounds(164, 10, 240, 60);
        contentPane.add(concursoDropdown);

        btnGerarSorteio = new JButton("Gerar Sorteio");
        btnGerarSorteio.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnGerarSorteio.setBounds(490, 10, 240, 60);
        btnGerarSorteio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
        contentPane.add(btnGerarSorteio);
    }


    private void carregarConcursos() {
        try {
            List<Concurso> concursos = ConcursoBD.consultar();
            Concurso concursoSelecionado = null;

            for (Concurso concurso : concursos) {
                if (concurso.getEstagio() == EstagioConcurso.ABERTO) {
                    if (concursoSelecionado == null || concurso.getId() < concursoSelecionado.getId()) {
                        concursoSelecionado = concurso;
                    }
                }
            }

            concursoDropdown.removeAllItems();
            for (Concurso concurso : concursos) {
                concursoDropdown.addItem(concurso);
            }

            if (concursoSelecionado != null) {
                concursoDropdown.setSelectedItem(concursoSelecionado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar concursos: " + e.getMessage());
        }
    }

    private void gerarRelatorio() {
        Concurso concursoSelecionado = (Concurso) concursoDropdown.getSelectedItem();
        if (concursoSelecionado != null) {
            try {
                if (concursoSelecionado.getEstagio() == EstagioConcurso.FINALIZADO) {
                    JOptionPane.showMessageDialog(this, "Este concurso já foi sorteado!");
                    return;
                }

                Object[] options = {"Manual", "Automático"};
                int escolha = JOptionPane.showOptionDialog(
                        this,
                        "Escolha o método de sorteio:",
                        "Método de Sorteio",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (escolha == JOptionPane.CLOSED_OPTION) {
                    return;
                }

                List<Integer> numerosSorteados;

                if (escolha == 0) {
                    numerosSorteados = abrirDialogoManualSimplificado();
                    if (numerosSorteados == null || numerosSorteados.isEmpty()) {
                        return;
                    }
                } else {
                    numerosSorteados = gerarNumerosAleatorios();
                }

                concursoSelecionado.setNumerosSorteados(numerosSorteados);
                concursoSelecionado.fecharConcurso();
                resultado.calcularResultado(concursoSelecionado);
                salvarResultado(concursoSelecionado);
                concursoSelecionado.finalizarConcurso();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar resultado: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum concurso selecionado.");
        }
    }


    private List<Integer> gerarNumerosAleatorios() {
        List<Integer> numeros = new ArrayList<>();
        while (numeros.size() < 15) {
            int numero = (int) (Math.random() * 25) + 1;
            if (!numeros.contains(numero)) {
                numeros.add(numero);
            }
        }
        Collections.sort(numeros);
        return numeros;
    }


    private void salvarResultado(Concurso concurso) throws ClassNotFoundException {
        concurso.finalizarConcurso();
		ConcursoBD concursoBD = new ConcursoBD();
		concursoBD.alterar(concurso.getId(), concurso);
		JOptionPane.showMessageDialog(this, "Sorteio salvo com sucesso.");
    }
}
