package telas;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bd.ApostadorBD;
import util.ArredondarBotao;
import util.ArredondarCaixaSenha;
import util.ArredondarCaixaTexto;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaLogin() {
        setTitle("LOGIN GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        //-----------------------------------------------------------
        
        JPanel panelComFundo = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    
                    BufferedImage img = ImageIO.read(getClass().getResource("/imagens/TelaLogin.jpg"));

                 
                    Image dimg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(dimg, 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panelComFundo.setLayout(null);
        setContentPane(panelComFundo);
        
        //-----------------------------------------------------------
        
        
        
        
        
        JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);   
        lblColocarFoco.requestFocusInWindow();
        
        JTextField txtUsuario = new ArredondarCaixaTexto(10, 10);
        txtUsuario.setBounds(238, 373, 300, 30);
        getContentPane().add(txtUsuario);
        util.Placeholder.setPlaceholder(txtUsuario, "Insira seu Email ou CPF");

        //PLACEHOLDER SENHA
        ArredondarCaixaTexto txtSenhaPlaceholder = new ArredondarCaixaTexto(10, 10);
        txtSenhaPlaceholder.setBounds(238, 433, 245, 30);
        util.Placeholder.setPlaceholder(txtSenhaPlaceholder, "Insira sua senha");
        getContentPane().add(txtSenhaPlaceholder);

        //CAMPO SENHA
        ArredondarCaixaSenha txtSenha = new ArredondarCaixaSenha(10, 10);
        txtSenha.setBounds(238, 433, 245, 30);
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
        verSenha.setBounds(488, 433, 50, 30);
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
        
        JLabel naoPossuiConta = new JLabel("<html>Não possui conta? <a href=''>Clique aqui</a></html>");
        naoPossuiConta.setBounds(303, 529, 170, 30);
        naoPossuiConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //OQ VAI ACONTECER SE CLICAR NO BOTAO
        naoPossuiConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	dispose();
                TelaCadastro telaCadrastro = new TelaCadastro();
                telaCadrastro.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	naoPossuiConta.setText("<html>Não possui conta? <a href='' style='color: #0000EE;'>Clique aqui</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	naoPossuiConta.setText("<html>Não possui conta? <a href=''>Clique aqui</a></html>");
            }
        });

        getContentPane().add(naoPossuiConta);
        
        
       ArredondarBotao btnEntrar = new ArredondarBotao("Entrar", 10, 10);
      
    
       btnEntrar.addActionListener(new ActionListener() {
    	   
            public void actionPerformed(ActionEvent e) {

            	                                    
                String emailOuCpf = txtUsuario.getText();
                String cpfAjustado = emailOuCpf.replaceAll("[^0-9]", "");
                String senha = new String(txtSenha.getPassword());
                
                ApostadorBD apostadorBD = new ApostadorBD();
                
                try {
                    boolean loginValido = apostadorBD.verificarLogin(emailOuCpf, cpfAjustado, senha);
                    if (loginValido) {
                    	boolean Admin = apostadorBD.verificarAdmin(emailOuCpf, emailOuCpf, senha);
                    	if(Admin) {
                    		JOptionPane.showMessageDialog(null, "Login como administrador realizado com sucesso!");
                            dispose();
                            TelaMenuAdmin telaAdmin = new TelaMenuAdmin();
                            telaAdmin.setVisible(true);
                    	} else {
                    		JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                            dispose();
                            TelaMenu menu = new TelaMenu(ApostadorBD.retornarApostador(emailOuCpf, cpfAjustado, senha));
                            menu.setVisible(true);
                    	}                      
                    } else {
                        JOptionPane.showMessageDialog(null, "Email/CPF ou senha incorretos.");
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao realizar login.");
                }
          
            }
            
            
           
           
        });
       
        
        btnEntrar.setText("Entrar");
        btnEntrar.setBounds(238, 483, 300, 30);
        getContentPane().add(btnEntrar);
        

        setVisible(true);
    }

    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}