package telas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bd.ApostaBD;
import bd.ConcursoBD;
import entidade.Aposta;
import entidade.Apostador;
import entidade.Concurso;

public class TelaMenu extends JFrame {

	private static final long serialVersionUID = 1L;

    public TelaMenu(Apostador apostadorAtual) {
    	
    	
        //-----------------------------------------------------------
        
        JPanel panelComFundo = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    
                    BufferedImage img = ImageIO.read(getClass().getResource("/imagens/GDtelaDois.jpg"));

                 
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
    	
    	
    	
    	
    	
    	
    	
        setTitle("MENU GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextField lblColocarFoco;
        getContentPane().setLayout(null);
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);
        
        JButton btnRealizarAposta = new JButton("");
        
        //-----------------------------------------------------------
        btnRealizarAposta.setOpaque(false);
        btnRealizarAposta.setContentAreaFilled(false); 
        btnRealizarAposta.setBorderPainted(false); 
        btnRealizarAposta.setFocusPainted(false);
        //-----------------------------------------------------------
        btnRealizarAposta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	        	try {
	        		TelaAposta telaApostar = new TelaAposta(apostadorAtual);
	        	    telaApostar.setSize(800, 700);
	                telaApostar.setLocationRelativeTo(null);
	                telaApostar.setVisible(true);
	                dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sem concurso cadastrado no momento!");
				}    
        	}
        });
        btnRealizarAposta.setBounds(260, 174, 273, 54);
        getContentPane().add(btnRealizarAposta);
        
        JButton btnConsultarApostas = new JButton("");
        //-----------------------------------------------------------
        btnConsultarApostas.setOpaque(false);
        btnConsultarApostas.setContentAreaFilled(false); 
        btnConsultarApostas.setBorderPainted(false); 
        btnConsultarApostas.setFocusPainted(false);
        //-----------------------------------------------------------
        
        btnConsultarApostas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    TelaConcultarApostas form = new TelaConcultarApostas(apostadorAtual);
                    form.setSize(800, 700);
                    form.setLocationRelativeTo(null);
                    form.setVisible(true);
                } catch (ClassNotFoundException | IOException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Erro ao abrir a tela de consultar apostas: " + ex.getMessage(), 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnConsultarApostas.setBounds(260, 238, 273, 54);
        getContentPane().add(btnConsultarApostas);
        
        JButton btnVerPerfil = new JButton("");
        
        //-----------------------------------------------------------
        btnVerPerfil.setOpaque(false);
        btnVerPerfil.setContentAreaFilled(false); 
        btnVerPerfil.setBorderPainted(false); 
        btnVerPerfil.setFocusPainted(false);
        //-----------------------------------------------------------
        
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	    TelaInformacoesApostador telaInfo = new TelaInformacoesApostador(apostadorAtual);
        	    telaInfo.setSize(800, 700);
        	    telaInfo.setLocationRelativeTo(null);
        	    telaInfo.setVisible(true);
        	}
        });
        btnVerPerfil.setBounds(260, 314, 273, 54);
        getContentPane().add(btnVerPerfil);
        lblColocarFoco.requestFocusInWindow();
        
        JButton btnSair = new JButton("");
        
        //-----------------------------------------------------------
        btnSair.setOpaque(false);
        btnSair.setContentAreaFilled(false); 
        btnSair.setBorderPainted(false); 
        btnSair.setFocusPainted(false);
        //-----------------------------------------------------------
        
        btnSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaLogin form = new TelaLogin();
        		form.setVisible(true);
        		dispose();
        	}
        });
        btnSair.setBounds(266, 388, 267, 54);
        getContentPane().add(btnSair);
        SwingUtilities.invokeLater(() -> {
            List<Concurso> listaConcursos = ConcursoBD.consultar();
            exibirNotificacoesGanhadores(apostadorAtual, listaConcursos);
        });
        
    }
    
    public void exibirNotificacoesGanhadores(Apostador apostador, List<Concurso> listaConcursos) {
        for (Concurso concurso : listaConcursos) {
            if (concurso.getApostas() != null) {
                for (Aposta aposta : concurso.getApostas()) {
                    if (aposta.getApostador().getCpf().equals(apostador.getCpf()) 
                            && aposta.getValorGanho() > 0 
                            && !aposta.isFoiNotificado()) {

                        String mensagem = "Parabéns, você ganhou!\n" +
                                          "Concurso ID: " + concurso.getId() + "\n" +
                                          "Aposta ID: " + aposta.getId() + "\n" +
                                          "Valor Ganho: R$" + aposta.getValorGanho() + "\n" +
                                          "Números Acertados: " + aposta.getQuantidadeAcertos();
                        JOptionPane.showMessageDialog(null, mensagem, "Notificação de Ganhador", JOptionPane.INFORMATION_MESSAGE);

                        aposta.setFoiNotificado(true);
                        ConcursoBD.atualizarConcurso(concurso);
                        ApostaBD.atualizarAposta(aposta);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TelaMenu frame = new TelaMenu(null);
        frame.setVisible(true);
    }
}
