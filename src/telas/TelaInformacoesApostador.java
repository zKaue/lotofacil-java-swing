package telas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;	
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import bd.ApostadorBD;
import entidade.Apostador;
import util.ArredondarBotao;
import util.ArredondarCaixaTexto;

public class TelaInformacoesApostador extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public TelaInformacoesApostador(Apostador apostadorAtual) {
		
		
		//----------------------------------------------------------------------------------alterações PS


		
		   
	    JPanel panelComFundo = new JPanel() {
	        private static final long serialVersionUID = 1L;

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            try {
	                BufferedImage img =ImageIO.read(getClass().getResource("/imagens/TelaCadastro.jpg"));
	      	                Image dimg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	                g.drawImage(dimg, 0, 0, null);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    };

	    panelComFundo.setLayout(null); 
	    setContentPane(panelComFundo); 

	


    //------------------------------------------------------------------
		
		

        setTitle("CONSULTAR INFORMAÇÕES GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        //Colocar foco
        JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);
        lblColocarFoco.requestFocusInWindow();

        // Título
        JLabel tituloLabel = new JLabel("INFORMAÇÕES DO USUÁRIO");
        tituloLabel.setBounds(470, 25, 200, 30);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(tituloLabel);

        // Data de Nascimento
        JLabel dataNascLabel = new JLabel("Data de nascimento");
        dataNascLabel.setBounds(420, 54, 300, 30);
        getContentPane().add(dataNascLabel);
        
        Date dataNascimento = apostadorAtual.getDataNascimento();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascimentoApostador = formato.format(dataNascimento);
        
        String dia = dataNascimentoApostador.substring(0, 2);
        String mes = util.DateUtil.getMesNome(dataNascimentoApostador.substring(3, 5));
        String ano = dataNascimentoApostador.substring(6, 10);    

        JComboBox<String> diaComboBox = new JComboBox<>(new String[]{dia});
        diaComboBox.setBounds(420, 84, 80, 30);
        getContentPane().add(diaComboBox);
        diaComboBox.setEnabled(false);

        JComboBox<String> mesComboBox = new JComboBox<>(new String[]{mes});
        mesComboBox.setBounds(530, 84, 80, 30);
        getContentPane().add(mesComboBox);
        mesComboBox.setEnabled(false);

        JComboBox<String> anoComboBox = new JComboBox<>(new String[]{ano});
        anoComboBox.setBounds(640, 84, 80, 30);
        getContentPane().add(anoComboBox);
        anoComboBox.setEnabled(false);

        // Campos de Cadastro
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(420, 114, 300, 30);
        getContentPane().add(lblNome);
        
        ArredondarCaixaTexto txtNomeUsuario = new ArredondarCaixaTexto(10, 10);
        txtNomeUsuario.setBounds(420, 144, 300, 30);
        getContentPane().add(txtNomeUsuario);
        
        
        txtNomeUsuario.setText(apostadorAtual.getNome());

	    if (txtNomeUsuario.getText().isBlank()) {
	         util.Placeholder.setPlaceholder(txtNomeUsuario, "Informe seu nome completo");
	     }
	
	    txtNomeUsuario.addFocusListener(new FocusAdapter() {
	         @Override
	         public void focusGained(FocusEvent e) {
	             if (txtNomeUsuario.getText().equals("Informe seu nome completo")) {
	                 txtNomeUsuario.setText("");
	                 txtNomeUsuario.setForeground(Color.BLACK);
	             }
	         }
	
	         @Override
	         public void focusLost(FocusEvent e) {
	             if (txtNomeUsuario.getText().isBlank()) {
	                 util.Placeholder.setPlaceholder(txtNomeUsuario, "Informe seu nome completo");
	             }
	         }
	     });
        

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(420, 174, 300, 30);
        getContentPane().add(lblCpf);
        ArredondarCaixaTexto txtCpf = new ArredondarCaixaTexto(10, 10);
            
        txtCpf.setBounds(420, 204, 300, 30);
        getContentPane().add(txtCpf);
        
        String cpf = apostadorAtual.getCpf();
        String cpfFormatado = String.format("%s.%s.%s-%s", 
        	    cpf.substring(0, 3), 
        	    cpf.substring(3, 6), 
        	    cpf.substring(6, 9), 
        	    cpf.substring(9, 11)
        	);
        
        txtCpf.setText(cpfFormatado);
        txtCpf.setEnabled(false);
        
        JComboBox<String> codigoPaisComboBox = new JComboBox<>();
        codigoPaisComboBox.setBounds(420, 264, 80, 30);
        codigoPaisComboBox.addItem("+55");
        getContentPane().add(codigoPaisComboBox);
        codigoPaisComboBox.setEnabled(true);
            
        JLabel lblNumeroTelefone = new JLabel("Telefone");
        lblNumeroTelefone.setBounds(420, 234, 300, 30);
        getContentPane().add(lblNumeroTelefone);
        
        ArredondarCaixaTexto txtTelefone = new ArredondarCaixaTexto(10, 10);
        txtTelefone.setBounds(510, 264, 210, 30);
        getContentPane().add(txtTelefone);
        
        String telefone = apostadorAtual.getTelefone();
        String telefoneFormatado = String.format("(%s) %s-%s", 
                telefone.substring(0, 2),
                telefone.substring(2, 7),
                telefone.substring(7, 11)
            );

        txtTelefone.setText(telefoneFormatado);

        if (txtTelefone.getText().isBlank()) {
            util.Placeholder.setPlaceholder(txtTelefone, "(XX) XXXXX-XXXX");
        }

        txtTelefone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = txtTelefone.getText().replaceAll("[^0-9]", "");

                if (texto.length() > 11) {
                    texto = texto.substring(0, 11);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < texto.length(); i++) {
                    if (i == 0) sb.append("(");
                    else if (i == 2) sb.append(") ");
                    else if (i == 7) sb.append("-");
                    sb.append(texto.charAt(i));
                }
                txtTelefone.setText(sb.toString());
                txtTelefone.setCaretPosition(sb.length());  // Mantém o cursor no final
                txtTelefone.setForeground(Color.BLACK);
            }
        });

        txtTelefone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTelefone.getText().equals("(XX) XXXXX-XXXX")) {
                    txtTelefone.setText("");
                    txtTelefone.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTelefone.getText().isBlank()) {
                    util.Placeholder.setPlaceholder(txtTelefone, "(XX) XXXXX-XXXX");
                }
            }
        });
        

        txtTelefone.setBounds(510, 264, 210, 30);
        getContentPane().add(txtTelefone);
        

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(420, 294, 300, 30);
        getContentPane().add(lblEmail);

        ArredondarCaixaTexto txtEmail = new ArredondarCaixaTexto(10, 10);
        txtEmail.setBounds(420, 324, 300, 30);
        getContentPane().add(txtEmail);
        txtEmail.setText(apostadorAtual.getEmail());

        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtEmail.getText().equals("Example@gmail.com")) {
                    txtEmail.setText("");
                    txtEmail.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtEmail.getText().isBlank()) {
                    util.Placeholder.setPlaceholder(txtEmail, "Example@gmail.com");
                }
            }
        });
        
        

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(420, 417, 300, 30);
        getContentPane().add(lblSenha);
        
        // Gênero
        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setBounds(420, 353, 300, 30);
        getContentPane().add(lblSexo);

        final ImageIcon mascIcon = new ImageIcon(getClass().getResource("/imagens/mascNormal.png"));
        Image mascImage = mascIcon.getImage().getScaledInstance(50, 32, Image.SCALE_SMOOTH);
        final ImageIcon mascIconFinal = new ImageIcon(mascImage);

        final ImageIcon mascIconSelecionado = new ImageIcon(getClass().getResource("/imagens/mascSelecionado.png"));
        Image mascImageSelecionado = mascIconSelecionado.getImage().getScaledInstance(50, 32, Image.SCALE_SMOOTH);
        final ImageIcon mascIconSelecionadoFinal = new ImageIcon(mascImageSelecionado);

        final ImageIcon femIcon = new ImageIcon(getClass().getResource("/imagens/femNormal.png"));
        Image femImage = femIcon.getImage().getScaledInstance(50, 32, Image.SCALE_SMOOTH);
        final ImageIcon femIconFinal = new ImageIcon(femImage);

        final ImageIcon femIconSelecionado = new ImageIcon(getClass().getResource("/imagens/femSelecionado.png"));
        Image femImageSelecionado = femIconSelecionado.getImage().getScaledInstance(50, 32, Image.SCALE_SMOOTH);
        final ImageIcon femIconSelecionadoFinal = new ImageIcon(femImageSelecionado);

        JButton btnMasc = new JButton(mascIconFinal);
        btnMasc.setBounds(420, 383, 150, 35);
        btnMasc.setFocusPainted(false);
        getContentPane().add(btnMasc);
        
        JButton btnFem = new JButton(femIconFinal);
        btnFem.setBounds(570, 383, 150, 35);
        btnFem.setFocusPainted(false);
        getContentPane().add(btnFem);
        
        
        if ("Masculino".equals(apostadorAtual.getSexo())) {
            btnMasc.setIcon(mascIconSelecionadoFinal);
            btnFem.setIcon(femIconFinal);
        } else if ("Feminino".equals(apostadorAtual.getSexo())) {
            btnFem.setIcon(femIconSelecionadoFinal);
            btnMasc.setIcon(mascIconFinal);
        }
        
        btnMasc.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(null, "Não é possível alterar o sexo!");
	        }
	    });

	    btnFem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(null, "Não é possível alterar o sexo!");
	        }
	    });

        // Botão de Cadastro
        ArredondarBotao btnSalvarConta = new ArredondarBotao("Cadastrar-se", 10, 10);
        btnSalvarConta.setText("Salvar Conta");
        btnSalvarConta.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		        		
        	    String nome = txtNomeUsuario.getText().trim();
        	    String placeHolderNome = "Informe seu nome completo";

        	    if (nome.equals(placeHolderNome) || nome.isBlank()) {
        	        JOptionPane.showMessageDialog(null, "Por favor, informe o nome!");
        	        return;
        	    }
        	    
        	    String nomeRegex = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$";
        	    
        	    if (!nome.matches(nomeRegex)) {
        	        JOptionPane.showMessageDialog(null, "O nome deve conter apenas letras e espaços!");
        	        return;
        	    }
        	    
        	    String cpf = txtCpf.getText().replaceAll("[^0-9]", "");
        	    String placeHolderCpf = "000.000.000-00";

                if (txtCpf.getText().equals(placeHolderCpf)) {
                    JOptionPane.showMessageDialog(null, "Por favor, informe o CPF!");
                    return;
                } else if (cpf.length() != 11) {
                	JOptionPane.showMessageDialog(null, "O CPF deve ter 11 dígitos!");
                    return;
                }
                
                String telefone = txtTelefone.getText().replaceAll("[^0-9]", "");
                String placeHolderTelefone = "(XX) XXXXX-XXXX";
                
                if (txtTelefone.getText().equals(placeHolderTelefone)) {
                    JOptionPane.showMessageDialog(null, "Por favor, informe o número de telefone!");
                    return;
                } else if (telefone.length() != 11){
                	JOptionPane.showMessageDialog(null, "Por favor, informe um número válido!");
                    return;
                }
                
                String[] dddsValidos = {
                        "11", "12", "13", "14", "15", "16", "17", "18", "19",
                        "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38",
                        "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55",
                        "61", "62", "64", "63", "65", "66", "67", "68", "69",
                        "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89",
                        "91", "92", "93", "94", "95", "96", "97", "98", "99"
                };

                String ddd = telefone.substring(0, 2);
                
                if (!Arrays.asList(dddsValidos).contains(ddd)) {
                    JOptionPane.showMessageDialog(null, "Por favor, informe um número com DDD válido do Brasil!");
                    return;
                }
                
                String email = txtEmail.getText();
                String placeHolderEmail = "Example@gmail.com";
                
                if (email.equals(placeHolderEmail)) {
        	        JOptionPane.showMessageDialog(null, "Por favor, informe o email!");
        	        return;
        	    }
                
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

                if (!email.matches(emailRegex)) {
                    JOptionPane.showMessageDialog(null, "Por favor, informe um email válido!");
                    return;
                }
                
                apostadorAtual.setNome(nome);
                apostadorAtual.setTelefone(telefone);
                apostadorAtual.setEmail(email);
                
                
        	    try {
        	    	ApostadorBD apostadorBD = new ApostadorBD();
					apostadorBD.atualizar(apostadorAtual);
	        	    JOptionPane.showMessageDialog(null, "Perfil atualizado com sucesso!");
	        	    
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} 
        	}
        });
        
        btnSalvarConta.setBounds(420, 493, 300, 30);
        btnSalvarConta.setEnabled(true);
        getContentPane().add(btnSalvarConta);
        
        ArredondarBotao btnAlterarSenha = new ArredondarBotao("Cadastrar-se", 10, 10);
        btnAlterarSenha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaAlterarSenha frame = new TelaAlterarSenha(apostadorAtual);
                frame.setVisible(true);
        	}
        });
        btnAlterarSenha.setText("Alterar Senha");
        btnAlterarSenha.setEnabled(true);
        btnAlterarSenha.setBounds(420, 447, 300, 30);
        getContentPane().add(btnAlterarSenha);
        
        ArredondarBotao btnExcluirConta = new ArredondarBotao("Cadastrar-se", 10, 10);
        btnExcluirConta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int resposta = JOptionPane.showConfirmDialog(getContentPane(), 
                        "Tem certeza de que deseja excluir sua conta?", "Confirmação", 
                        JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            ApostadorBD apostadorBD = new ApostadorBD();
                            apostadorBD.remover(apostadorAtual);
                            JOptionPane.showMessageDialog(getContentPane(), "Conta excluida com sucesso!");
                            dispose();
                            TelaLogin form = new TelaLogin();
                            form.setVisible(true);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(getContentPane(), "Erro ao excluir conta: " + ex.getMessage());
                        }
                    }
        	}
        });
        btnExcluirConta.setForeground(new Color(255, 0, 0));
        btnExcluirConta.setText("Excluir Conta");
        btnExcluirConta.setEnabled(true);
        btnExcluirConta.setBounds(420, 538, 300, 30);
        getContentPane().add(btnExcluirConta);
        
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

        setVisible(true);
    }

    public static void main(String[] args) {
    	Apostador apostadorAtual = new Apostador();
		Date dataNascimento = new Date(106, 6, 11);
		apostadorAtual.setDataNascimento(dataNascimento);
		apostadorAtual.setNome("Kaue");
		apostadorAtual.setCpf("12345678900");
		apostadorAtual.setTelefone("41912345676");
		apostadorAtual.setEmail("kaue@gmail.com");
		apostadorAtual.setAdmin(false);
		apostadorAtual.setSenha("11223344");
		apostadorAtual.setSexo("Masculino");
    	
    	TelaInformacoesApostador telainfo = new TelaInformacoesApostador(apostadorAtual);
    	telainfo.setVisible(true);
    }
}
