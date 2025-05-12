package telas;

import javax.swing.*;

import bd.ApostadorBD;
import entidade.Apostador;

import java.awt.*;
import java.awt.event.*;
import util.*;

public class TelaAlterarSenha extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaAlterarSenha(Apostador apostadorAtual) {
        setTitle("ALTERAR SENHA GOLDENLOTO");
        setSize(500, 290);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Título
        JLabel lblTitulo = new JLabel("Alterar Senha");
        lblTitulo.setBounds(194, 21, 172, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(lblTitulo);
        
        // Campo Senha Atual
        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setBounds(39, 74, 100, 30);
        getContentPane().add(lblSenhaAtual);

        ArredondarCaixaTexto txtSenhaAtualPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtSenhaAtualPlaceholder.setBounds(139, 74, 245, 30);
        util.Placeholder.setPlaceholder(txtSenhaAtualPlaceholder, "Digite sua senha atual");
        getContentPane().add(txtSenhaAtualPlaceholder);

        ArredondarCaixaSenha txtSenhaAtual = new ArredondarCaixaSenha(10, 10);
        txtSenhaAtual.setBounds(139, 74, 245, 30);
        txtSenhaAtual.setEchoChar('\u2022');
        txtSenhaAtual.setVisible(false);
        getContentPane().add(txtSenhaAtual);

        txtSenhaAtualPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSenhaAtualPlaceholder.setVisible(false);
                txtSenhaAtual.setVisible(true);
                txtSenhaAtual.requestFocus();
            }
        });

        txtSenhaAtual.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSenhaAtual.getPassword().length == 0) {
                    txtSenhaAtual.setVisible(false);
                    txtSenhaAtualPlaceholder.setVisible(true);
                }
            }
        });

        ArredondarBotao verSenhaAtual = new ArredondarBotao("👁", 10, 10);
        verSenhaAtual.setBounds(389, 74, 50, 30);
        getContentPane().add(verSenhaAtual);

        verSenhaAtual.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtSenhaAtual.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtSenhaAtual.setEchoChar('\u2022');
            }
        });

        // Campo Senha
        JLabel lblSenha = new JLabel("Nova Senha");
        lblSenha.setBounds(39, 114, 100, 30);
        getContentPane().add(lblSenha);

        ArredondarCaixaTexto txtSenhaPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtSenhaPlaceholder.setBounds(139, 114, 245, 30);
        util.Placeholder.setPlaceholder(txtSenhaPlaceholder, "Mínimo 8 caracteres");
        getContentPane().add(txtSenhaPlaceholder);

        ArredondarCaixaSenha txtSenha = new ArredondarCaixaSenha(10, 10);
        txtSenha.setBounds(139, 114, 245, 30);
        txtSenha.setEchoChar('\u2022');
        txtSenha.setVisible(false);
        getContentPane().add(txtSenha);

        txtSenhaPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSenhaPlaceholder.setVisible(false);
                txtSenha.setVisible(true);
                txtSenha.requestFocus();
            }
        });

        txtSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSenha.getPassword().length == 0) {
                    txtSenha.setVisible(false);
                    txtSenhaPlaceholder.setVisible(true);
                }
            }
        });

        ArredondarBotao verSenha = new ArredondarBotao("👁", 10, 10);
        verSenha.setBounds(389, 114, 50, 30);
        getContentPane().add(verSenha);

        verSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtSenha.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtSenha.setEchoChar('\u2022');
            }
        });

        // Campo Confirmar Senha
        JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
        lblConfirmarSenha.setBounds(39, 154, 100, 30);
        getContentPane().add(lblConfirmarSenha);

        ArredondarCaixaTexto txtConfirmarSenhaPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtConfirmarSenhaPlaceholder.setBounds(139, 154, 245, 30);
        util.Placeholder.setPlaceholder(txtConfirmarSenhaPlaceholder, "Digite sua senha novamente");
        getContentPane().add(txtConfirmarSenhaPlaceholder);

        ArredondarCaixaSenha txtConfirmarSenha = new ArredondarCaixaSenha(10, 10);
        txtConfirmarSenha.setBounds(139, 154, 245, 30);
        txtConfirmarSenha.setEchoChar('\u2022');
        txtConfirmarSenha.setVisible(false);
        getContentPane().add(txtConfirmarSenha);

        txtConfirmarSenhaPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtConfirmarSenhaPlaceholder.setVisible(false);
                txtConfirmarSenha.setVisible(true);
                txtConfirmarSenha.requestFocus();
            }
        });

        txtConfirmarSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtConfirmarSenha.getPassword().length == 0) {
                    txtConfirmarSenha.setVisible(false);
                    txtConfirmarSenhaPlaceholder.setVisible(true);
                }
            }
        });

        ArredondarBotao verSenhaConfirmar = new ArredondarBotao("👁", 10, 10);
        verSenhaConfirmar.setBounds(389, 154, 50, 30);
        getContentPane().add(verSenhaConfirmar);

        verSenhaConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtConfirmarSenha.setEchoChar((char) 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtConfirmarSenha.setEchoChar('\u2022');
            }
        });

        // Botão Alterar Senha
        ArredondarBotao btnAlterarSenha = new ArredondarBotao("Alterar Senha", 10, 10);
        btnAlterarSenha.setBounds(139, 194, 245, 40);
        getContentPane().add(btnAlterarSenha);

        btnAlterarSenha.addActionListener(e -> {
            String senhaAtual = new String(txtSenhaAtual.getPassword());
            String senha = new String(txtSenha.getPassword());
            String confirmarSenha = new String(txtConfirmarSenha.getPassword());

            if (senhaAtual.isBlank()) {
                JOptionPane.showMessageDialog(null, "Por favor, informe a senha atual!");
                return;
            } else if (senha.isBlank()) {
                JOptionPane.showMessageDialog(null, "Por favor, informe a nova senha!");
                return;
            } else if (confirmarSenha.isBlank()) {
                JOptionPane.showMessageDialog(null, "Por favor, confirme a nova senha!");
                return;
            }

            if (!senhaAtual.equals(apostadorAtual.getSenha())) {
                JOptionPane.showMessageDialog(this, "Senha atual incorreta.");
                return;
            }

            if (senha.length() < 8) {
                JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 8 caracteres!");
                return;
            } else if (!senha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(null, "As senhas não são iguais!");
                return;
            }

            apostadorAtual.setSenha(senha);
            ApostadorBD apostadorBD = new ApostadorBD();
            try {
				apostadorBD.atualizar(apostadorAtual);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAlterarSenha frame = new TelaAlterarSenha(null);
            frame.setVisible(true);
        });
    }
}
