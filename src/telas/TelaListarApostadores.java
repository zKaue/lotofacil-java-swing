package telas;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ApostadorBD;
import entidade.Apostador;
import table.model.ApostadorTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class TelaListarApostadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private ApostadorBD apostadorBD;
	private JPanel contentPane;
	private JTable tbApostador;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarApostadores frame = new TelaListarApostadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListarApostadores() {
		
		apostadorBD = new ApostadorBD();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 472);
		setSize(800, 700);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 40, 701, 434);
		contentPane.add(scrollPane);
		
		// Consulta os usuários
		List<Apostador> apostadores = ApostadorBD.consultar();
		
		// Cria a estrutura da tabela
		ApostadorTableModel apostadorTableModel = new ApostadorTableModel();
		apostadorTableModel.setApostadores(apostadores);
		
		// Atribui a estrutura da tabela a um JTable
		tbApostador = new JTable(apostadorTableModel);
		tbApostador.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {					
					int row = tbApostador.getSelectedRow();
					Apostador apostador = apostadorTableModel.getApostadores().get(row);
					
					TelaInformacoesApostador form = new TelaInformacoesApostador(apostador);
					form.setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(tbApostador);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro form = new TelaCadastro();
				form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				form.setVisible(true);
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNovo.setBounds(625, 485, 124, 29);
		contentPane.add(btnNovo);
		
		JButton btnVoltarMenu = new JButton("Voltar");
        btnVoltarMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaMenuAdmin form = new TelaMenuAdmin();
        		form.setVisible(true);
        		dispose();
        	}
        });
        btnVoltarMenu.setBounds(10, 10, 89, 23);
        getContentPane().add(btnVoltarMenu);
		
		setLocationRelativeTo(null);
	}
}
