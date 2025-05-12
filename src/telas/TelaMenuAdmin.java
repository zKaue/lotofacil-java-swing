package telas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMenuAdmin extends JFrame{

	private static final long serialVersionUID = 1L;

	public TelaMenuAdmin() {
		
		
		setTitle("MENU ADMIN GOLDENLOTO");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
		getContentPane().setLayout(null);
		JLabel lblTelaAdm = new JLabel("MENU ADMIN");
        setSize(800, 700);
        setLocationRelativeTo(null);
		lblTelaAdm.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTelaAdm.setBounds(272, 128, 225, 63);
		getContentPane().add(lblTelaAdm);
		
		JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);   
        lblColocarFoco.requestFocusInWindow();
		
		JButton btnGerenciarConcurso = new JButton("Gerenciar Concurso");
		btnGerenciarConcurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaGerenciarConcurso form = new TelaGerenciarConcurso();
				form.setSize(800, 700);
				form.setLocationRelativeTo(null);
				form.setVisible(true);
			}
		});
		btnGerenciarConcurso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGerenciarConcurso.setBounds(235, 216, 280, 77);
		getContentPane().add(btnGerenciarConcurso);
		
		JButton btnGerenciarApostadores = new JButton("Gerenciar Apostadores");
		btnGerenciarApostadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaListarApostadores form = new TelaListarApostadores();
				form.setVisible(true);
			}
		});
		btnGerenciarApostadores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGerenciarApostadores.setBounds(235, 304, 280, 77);
		getContentPane().add(btnGerenciarApostadores);
		
		JButton btnGerarSorteio = new JButton("Gerar Sorteio");
		btnGerarSorteio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaGerarSorteio form = new TelaGerarSorteio();
				form.setVisible(true);
			}
		});
		btnGerarSorteio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGerarSorteio.setBounds(235, 392, 280, 77);
		getContentPane().add(btnGerarSorteio);
		
		JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaLogin form = new TelaLogin();
        		form.setVisible(true);
        		dispose();
        	}
        });
        btnSair.setBounds(10, 10, 89, 23);
        getContentPane().add(btnSair);
        
	}
}
