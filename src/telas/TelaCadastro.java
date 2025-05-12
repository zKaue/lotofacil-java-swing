package telas;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javax.swing.SwingUtilities;

import bd.ApostadorBD;
import entidade.Apostador;
import util.ArredondarBotao;
import util.ArredondarCaixaSenha;
import util.ArredondarCaixaTexto;
import util.DateUtil;

public class TelaCadastro extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public TelaCadastro() {
		
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
		
        setTitle("CADASTRO GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        //Colocar foco
        JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);
        lblColocarFoco.requestFocusInWindow();

        // Título
        JLabel tituloLabel = new JLabel("CADASTRE-SE E DIVIRTA-SE!");
        tituloLabel.setBounds(470, 25, 200, 30);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(tituloLabel);

        // Data de Nascimento
        JLabel dataNascLabel = new JLabel("Data de nascimento");
        dataNascLabel.setBounds(420, 54, 300, 30);
        getContentPane().add(dataNascLabel);

        JComboBox<String> diaComboBox = new JComboBox<>(new String[]{"Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
        diaComboBox.setBounds(420, 84, 80, 30);
        getContentPane().add(diaComboBox);

        JComboBox<String> mesComboBox = new JComboBox<>(new String[]{"Mês", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});
        mesComboBox.setBounds(530, 84, 80, 30);
        getContentPane().add(mesComboBox);

        JComboBox<String> anoComboBox = new JComboBox<>();
        anoComboBox.setBounds(640, 84, 80, 30);
        anoComboBox.addItem("Ano");
        for (int ano = 2006; ano >= 1925; ano--) {
            anoComboBox.addItem(String.valueOf(ano));
        }
        getContentPane().add(anoComboBox);

        // Campos de Cadastro
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(420, 114, 300, 30);
        getContentPane().add(lblNome);
        
        ArredondarCaixaTexto txtNomeUsuario = new ArredondarCaixaTexto(10, 10);
        txtNomeUsuario.setBounds(420, 144, 300, 30);
        getContentPane().add(txtNomeUsuario);
        util.Placeholder.setPlaceholder(txtNomeUsuario, "Informe seu nome completo");

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(420, 174, 300, 30);
        getContentPane().add(lblCpf);
        ArredondarCaixaTexto txtCpf = new ArredondarCaixaTexto(10, 10);
        
        txtCpf.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
            	
                String texto = txtCpf.getText().replaceAll("[^0-9]", ""); //CPF SEM FORMATAÇAO
                
                if (texto.length() > 11) {
                    texto = texto.substring(0, 11);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < texto.length(); i++) {
                    if (i == 3 || i == 6) {
                        sb.append(".");
                    } else if (i == 9) {
                        sb.append("-");
                    }
                    sb.append(texto.charAt(i));
                }

                txtCpf.setText(sb.toString());
                txtCpf.setCaretPosition(sb.length());
            }
        });
        
        
        txtCpf.setBounds(420, 204, 300, 30);
        getContentPane().add(txtCpf);
        util.Placeholder.setPlaceholder(txtCpf, "000.000.000-00");
    
        
        JLabel lblNumeroTelefone = new JLabel("Telefone");
        lblNumeroTelefone.setBounds(420, 234, 300, 30);
        getContentPane().add(lblNumeroTelefone);
        
        ArredondarCaixaTexto txtTelefone = new ArredondarCaixaTexto(10, 10);
        txtTelefone.setBounds(510, 264, 210, 30);
        getContentPane().add(txtTelefone);
        util.Placeholder.setPlaceholder(txtTelefone, "(XX) XXXXX-XXXX");
        
        txtTelefone.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {

                String texto = txtTelefone.getText().replaceAll("[^0-9]", ""); // TELEFONE SEM FORMATAÇÃO
                
                if (texto.length() > 11) {
                    texto = texto.substring(0, 11);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < texto.length(); i++) {
                    if (i == 0) {
                        sb.append("(");
                    } else if (i == 2) {
                        sb.append(") ");
                    } else if (i == 7) {
                        sb.append("-");
                    }
                    sb.append(texto.charAt(i));
                }

                txtTelefone.setText(sb.toString());
                txtTelefone.setCaretPosition(sb.length());
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
        util.Placeholder.setPlaceholder(txtEmail, "Example@gmail.com");
        
        JComboBox<String> codigoPaisComboBox = new JComboBox<>();
        codigoPaisComboBox.setBounds(420, 264, 80, 30);
        codigoPaisComboBox.addItem("+55");
        getContentPane().add(codigoPaisComboBox);
        codigoPaisComboBox.setEnabled(true);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(420, 354, 300, 30);
        getContentPane().add(lblSenha);

        //PLACEHOLDER SENHA
        ArredondarCaixaTexto txtSenhaPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtSenhaPlaceholder.setBounds(420, 384, 245, 30);
        util.Placeholder.setPlaceholder(txtSenhaPlaceholder, "Mínimo 8 caracteres");
        getContentPane().add(txtSenhaPlaceholder);

        //CAMPO SENHA
        ArredondarCaixaSenha txtSenha = new ArredondarCaixaSenha(10, 10);
        txtSenha.setBounds(420, 384, 245, 30);
        txtSenha.setEchoChar('\u2022');
        txtSenha.setVisible(false);
        getContentPane().add(txtSenha);

        //QUANDO O CARA CLICA NO CAMPO, TIRA O PLACEHOLDER E TAMPA A SENHA DO USUARIO
        txtSenhaPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSenhaPlaceholder.setVisible(false);
                txtSenha.setVisible(true);
                txtSenha.requestFocus();
            }
        });

        //QUANDO O FOCO NAO ESTÁ NO CAMPO, PORÉM ANTES ELE VERIFICA SE TEM ALGUM CARACTER ANTES DE COLOCAR O PLACEHOLDER NOVAMENTE
        txtSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSenha.getPassword().length == 0) {
                    txtSenha.setVisible(false);
                    txtSenhaPlaceholder.setVisible(true);
                }
            }
        });

        //BOTAO DO OLHINHO PRA EXIBIR A SENHA
        ArredondarBotao verSenha = new ArredondarBotao("👁", 10, 10);
        verSenha.setBounds(670, 384, 50, 30);
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

        //TEXTINHO DE CONFIRMAR
        JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
        lblConfirmarSenha.setBounds(420, 414, 300, 30);
        getContentPane().add(lblConfirmarSenha);

        //CAMPO DE TEXTO PLACEHOLDER
        ArredondarCaixaTexto txtConfirmarSenhaPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtConfirmarSenhaPlaceholder.setBounds(420, 444, 245, 30);
        util.Placeholder.setPlaceholder(txtConfirmarSenhaPlaceholder, "Digite sua senha novamente");
        getContentPane().add(txtConfirmarSenhaPlaceholder);

        //CAMPO SENHA
        ArredondarCaixaSenha txtConfirmarSenha = new ArredondarCaixaSenha(10, 10);
        txtConfirmarSenha.setBounds(420, 444, 245, 30);
        txtConfirmarSenha.setEchoChar('\u2022');
        txtConfirmarSenha.setVisible(false);
        getContentPane().add(txtConfirmarSenha);

      //QUANDO O CARA CLICA NO CAMPO, TIRA O PLACEHOLDER E TAMPA A SENHA DO USUARIO
        txtConfirmarSenhaPlaceholder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtConfirmarSenhaPlaceholder.setVisible(false);
                txtConfirmarSenha.setVisible(true);
                txtConfirmarSenha.requestFocus();
            }
        });

      //QUANDO O FOCO NAO ESTÁ NO CAMPO, PORÉM ANTES ELE VERIFICA SE TEM ALGUM CARACTER ANTES DE COLOCAR O PLACEHOLDER NOVAMENTE
        txtConfirmarSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtConfirmarSenha.getPassword().length == 0) {
                    txtConfirmarSenha.setVisible(false);
                    txtConfirmarSenhaPlaceholder.setVisible(true);
                }
            }
        });

      //BOTAO DO OLHINHO PRA EXIBIR A SENHA
        ArredondarBotao verSenhaConfirmar = new ArredondarBotao("👁", 10, 10);
        verSenhaConfirmar.setBounds(670, 444, 50, 30);
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
        
        // Gênero
        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setBounds(420, 474, 300, 30);
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
        btnMasc.setBounds(420, 504, 150, 35);
        btnMasc.setFocusPainted(false);
        getContentPane().add(btnMasc);
        
        JButton btnFem = new JButton(femIconFinal);
        btnFem.setBounds(570, 504, 150, 35);
        btnFem.setFocusPainted(false);
        getContentPane().add(btnFem);
        
        final boolean[] mascSelecionado = {false};
        final boolean[] femSelecionado = {false};
        
        btnMasc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (mascSelecionado[0]) {
        			btnMasc.setIcon(mascIconFinal);
        			mascSelecionado[0] = false;
        		} else {
        			btnMasc.setIcon(mascIconSelecionadoFinal);
        			mascSelecionado[0] = true;
        			
        			btnFem.setIcon(femIconFinal);
        			femSelecionado[0] = false;
        		}
        	}
        });

        btnFem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (femSelecionado[0]) {
                    btnFem.setIcon(femIconFinal);
                    femSelecionado[0] = false;
                } else {
                    btnFem.setIcon(femIconSelecionadoFinal);
                    femSelecionado[0] = true;

                    btnMasc.setIcon(mascIconFinal);
                    mascSelecionado[0] = false;
                }
            }
        });
        

        // Botão de Cadastro
        ArredondarBotao btnCadastrar = new ArredondarBotao("Cadastrar-se", 10, 10);
        btnCadastrar.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        	    String diaSelecionado = (String) diaComboBox.getSelectedItem();
        	    String mesSelecionado = (String) mesComboBox.getSelectedItem();
        	    String anoSelecionado = (String) anoComboBox.getSelectedItem();
        	    
        	    boolean dataValida = util.ValidadorDeData.verificarDataNascimento(diaSelecionado, mesSelecionado, anoSelecionado);
        	    
        	    if(!dataValida) {
        	    	JOptionPane.showMessageDialog(null, "Data de nascimento inválida!");
        	    	return;
        	    }
        	    //CRIAR DPS QUE FOR VALIDADO
        	    String dataFormatada = diaSelecionado + "/" + DateUtil.getMesNumerico(mesSelecionado) + "/" + anoSelecionado;
        	    Date dataNascimentoUsuario = util.DateUtil.converterStringParaData(dataFormatada);
        	    String dataNascimento = util.DateUtil.converterDateParaData(dataNascimentoUsuario);

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
                
                String senha = String.valueOf(txtSenha.getPassword());
				String confirmarSenha = String.valueOf(txtConfirmarSenha.getPassword());
				
				if (senha.isBlank()) {
		            JOptionPane.showMessageDialog(null, "Por favor, informe a senha!");
		            return;
		        } else if (senha.length() < 8) {
		            JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 8 caracteres!");
		            return;
		        } else if (confirmarSenha.isBlank()) {
		        	JOptionPane.showMessageDialog(null, "Por favor, confirme sua senha!");
		            return;
		        } else if (!senha.equals(confirmarSenha)) {
		            JOptionPane.showMessageDialog(null, "As senhas não são iguais!");
		            return;
		        }
			
				String sexo = "";
				
        	    if(btnMasc.getIcon().equals(mascIconFinal) && btnFem.getIcon().equals(femIconFinal)) {
        	    	JOptionPane.showMessageDialog(null, "Por favor, informe o sexo!");
		            return;
        	    } else if(btnMasc.getIcon().equals(mascIconSelecionadoFinal)) {
        	    	sexo = "Masculino";
        	    } else {
        	    	sexo = "Feminino";
        	    }
        	    
        	    ApostadorBD apostadorBD = new ApostadorBD();
        	    Apostador apostador = new Apostador(apostadorBD.gerarNovoId(), dataNascimentoUsuario, nome, cpf, telefone, email, senha, sexo, false);
        	    
        	    try {
					apostadorBD.inserir(apostador);
	        	    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
	        	    
	        	    dispose();
	        	    TelaLogin telaLogin = new TelaLogin();
	                telaLogin.setVisible(true);
	                
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} 
        	}
        });
        
        btnCadastrar.setBounds(420, 560, 300, 30);
        btnCadastrar.setEnabled(true);
        getContentPane().add(btnCadastrar);

     
        JLabel jaPossuiConta = new JLabel("<html>Já possui conta? <a href=''>Clique aqui</a></html>");
        jaPossuiConta.setBounds(490, 595, 170, 30);
        jaPossuiConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //OQ VAI ACONTECER SE CLICAR NO BOTAO
        jaPossuiConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	dispose();
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jaPossuiConta.setText("<html>Já possui conta? <a href='' style='color: #0000EE;'>Clique aqui</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jaPossuiConta.setText("<html>Já possui conta? <a href=''>Clique aqui</a></html>");
            }
        });

        getContentPane().add(jaPossuiConta);

        setVisible(true);
    }

  



	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro());
    }
}