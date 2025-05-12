package telas;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bd.ApostaBD;
import bd.ConcursoBD;
import entidade.Aposta;
import entidade.Apostador;
import entidade.Concurso;
import util.ArredondarBotao;
import util.DateUtil;
import util.GerarResultado;
import util.ValorAposta;


public class TelaAposta extends JFrame {
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JLabel lblValorAposta = new JLabel("Valor da Aposta: R$ 0.00");
	List<Integer> numerosApostados = new ArrayList<>();
	ArredondarBotao btnApostar = new ArredondarBotao("Apostar", 10, 10);
	ValorAposta valores = new ValorAposta();
	JCheckBox[] checkboxes = new JCheckBox[25];
	
	
		
	public void verificarEstadoDoBotaoSalvar() {
	    btnApostar.setEnabled(numerosApostados.size() >= 15);
	}

	public double verificaPreco(int numerosSelecionados) {
	    int[] precos = {
	        valores.quinze.intValue(), valores.desseseis.intValue(), valores.dessete.intValue(),
	        valores.dezoito.intValue(), valores.desenove.intValue(), valores.vinte.intValue()
	    };

	    if (numerosSelecionados < 15) {
	        lblValorAposta.setText("Valor da Aposta: R$ 0.00");
	    } else if (numerosSelecionados <= 20) {
	        int preco = precos[numerosSelecionados - 15];
	        lblValorAposta.setText("Valor da Aposta: R$ " + preco +".00");
	        return preco;
	    }
	    return 0;
	}
	
	public void desligaCheckBox(int numerosSelecionados) {
		if (numerosSelecionados > 19) {

			for (JCheckBox checkbox : checkboxes) {

				if (!checkbox.isSelected()) {
					checkbox.setEnabled(false);
				}
			}
		} else {

			for (JCheckBox checkbox : checkboxes) {
				checkbox.setEnabled(true);
				
			}
		}

	}
	
	private void clicouCheckbox(JCheckBox checkbox, Integer num) {
		if (checkbox.isSelected()) {
			numerosApostados.add(num);
		} else {
			numerosApostados.remove(num);
		}
		verificaPreco(numerosApostados.size());
		desligaCheckBox(numerosApostados.size());
		verificarEstadoDoBotaoSalvar();
	}


	public TelaAposta(Apostador apostadorAtual) {
		
		//----------------------------------------------------------------------------------alterações PS


	
		   
		    JPanel panelComFundo = new JPanel() {
		        private static final long serialVersionUID = 1L;

		        @Override
		        protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            try {
		                BufferedImage img = ImageIO.read(getClass().getResource("/imagens/TelaCadastro.jpg"));
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


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(435, -37, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
	  // setContentPane(contentPane); 
		
	  /* O erro está na linha acima, pois se comenta ela o fundo aparece
	   * mas se descomente o checkbox aparece, a Hirarquia de container pai e filho não
	   * está funcionando*/
		
		
		
		//contentPane.setLayout(null);
		
		
	
		
		
		/*JTextField lblColocarFoco;
        lblColocarFoco = new JTextField();
        lblColocarFoco.setBounds(0, 0, 1, 1);
        getContentPane().add(lblColocarFoco);
        lblColocarFoco.setColumns(10);   
        lblColocarFoco.requestFocusInWindow();*/
		
		//-----------------------------------------------------
		 JTextField lblColocarFoco = new JTextField();
		    lblColocarFoco.setBounds(0, 0, 1, 1);
		    panelComFundo.add(lblColocarFoco);
		    lblColocarFoco.setColumns(10);
		    lblColocarFoco.requestFocusInWindow();
		//-----------------------------------------------------

		JCheckBox checkBox1 = new JCheckBox("01");
		checkBox1.setBounds(270, 171, 40, 20);
		//contentPane.add(checkBox1);
		panelComFundo.add(checkBox1);
		checkboxes[0] = checkBox1;

		JCheckBox checkBox2 = new JCheckBox("02");
		checkBox2.setBounds(320, 171, 40, 20);
		//contentPane.add(checkBox2);
		panelComFundo.add(checkBox2);
		checkboxes[1] = checkBox2;

		JCheckBox checkBox3 = new JCheckBox("03");
		checkBox3.setBounds(370, 171, 40, 20);
		//contentPane.add(checkBox3);
		panelComFundo.add(checkBox3);
		checkboxes[2] = checkBox3;

		JCheckBox checkBox4 = new JCheckBox("04");
		checkBox4.setBounds(420, 171, 40, 20);
		//contentPane.add(checkBox4);
		panelComFundo.add(checkBox4);
		checkboxes[3] = checkBox4;

		JCheckBox checkBox5 = new JCheckBox("05");
		checkBox5.setBounds(470, 171, 40, 20);
		//contentPane.add(checkBox5);
		panelComFundo.add(checkBox5);
		checkboxes[4] = checkBox5;

		JCheckBox checkBox6 = new JCheckBox("06");
		checkBox6.setBounds(270, 211, 40, 20);
		//contentPane.add(checkBox6);
		panelComFundo.add(checkBox6);
		checkboxes[5] = checkBox6;

		JCheckBox checkBox7 = new JCheckBox("07");
		checkBox7.setBounds(320, 211, 40, 20);
		//contentPane.add(checkBox7);
		panelComFundo.add(checkBox7);
		checkboxes[6] = checkBox7;

		JCheckBox checkBox8 = new JCheckBox("08");
		checkBox8.setBounds(370, 211, 40, 20);
		//contentPane.add(checkBox8);
		panelComFundo.add(checkBox8);
		checkboxes[7] = checkBox8;

		JCheckBox checkBox9 = new JCheckBox("09");
		checkBox9.setBounds(420, 211, 40, 20);
		//contentPane.add(checkBox9);
		panelComFundo.add(checkBox9);
		checkboxes[8] = checkBox9;

		JCheckBox checkBox10 = new JCheckBox("10");
		checkBox10.setBounds(470, 211, 40, 20);
		//contentPane.add(checkBox10);
		panelComFundo.add(checkBox10);
		checkboxes[9] = checkBox10;

		JCheckBox checkBox11 = new JCheckBox("11");
		checkBox11.setBounds(270, 251, 40, 20);
		//contentPane.add(checkBox11);
		panelComFundo.add(checkBox11);
		checkboxes[10] = checkBox11;

		JCheckBox checkBox12 = new JCheckBox("12");
		checkBox12.setBounds(320, 251, 40, 20);
		//contentPane.add(checkBox12);
		panelComFundo.add(checkBox12);
		checkboxes[11] = checkBox12;

		JCheckBox checkBox13 = new JCheckBox("13");
		checkBox13.setBounds(370, 251, 40, 20);
		//contentPane.add(checkBox13);
		panelComFundo.add(checkBox13);
		checkboxes[12] = checkBox13;

		JCheckBox checkBox14 = new JCheckBox("14");
		checkBox14.setBounds(420, 251, 40, 20);
		//contentPane.add(checkBox14);
		panelComFundo.add(checkBox14);
		checkboxes[13] = checkBox14;

		JCheckBox checkBox15 = new JCheckBox("15");
		checkBox15.setBounds(470, 251, 40, 20);
		//contentPane.add(checkBox15);
		panelComFundo.add(checkBox15);
		checkboxes[14] = checkBox15;

		JCheckBox checkBox16 = new JCheckBox("16");
		checkBox16.setBounds(270, 291, 40, 20);
		//contentPane.add(checkBox16);
		panelComFundo.add(checkBox16);
		checkboxes[15] = checkBox16;

		JCheckBox checkBox17 = new JCheckBox("17");
		checkBox17.setBounds(320, 291, 40, 20);
		//contentPane.add(checkBox17);
		panelComFundo.add(checkBox17);
		checkboxes[16] = checkBox17;

		JCheckBox checkBox18 = new JCheckBox("18");
		checkBox18.setBounds(370, 291, 40, 20);
		//contentPane.add(checkBox18);
		panelComFundo.add(checkBox18);
		checkboxes[17] = checkBox18;

		JCheckBox checkBox19 = new JCheckBox("19");
		checkBox19.setBounds(420, 291, 40, 20);
		//contentPane.add(checkBox19);
		panelComFundo.add(checkBox19);
		checkboxes[18] = checkBox19;

		JCheckBox checkBox20 = new JCheckBox("20");
		checkBox20.setBounds(470, 291, 40, 20);
		//contentPane.add(checkBox20);
		panelComFundo.add(checkBox20);
		checkboxes[19] = checkBox20;

		JCheckBox checkBox21 = new JCheckBox("21");
		checkBox21.setBounds(270, 331, 40, 20);
		//contentPane.add(checkBox21);
		panelComFundo.add(checkBox21);
		checkboxes[20] = checkBox21;

		JCheckBox checkBox22 = new JCheckBox("22");
		checkBox22.setBounds(320, 331, 40, 20);
		//contentPane.add(checkBox22);
		panelComFundo.add(checkBox22);
		checkboxes[21] = checkBox22;

		JCheckBox checkBox23 = new JCheckBox("23");
		checkBox23.setBounds(370, 331, 40, 20);
		//contentPane.add(checkBox23);
		panelComFundo.add(checkBox23);
		checkboxes[22] = checkBox23;

		JCheckBox checkBox24 = new JCheckBox("24");
		checkBox24.setBounds(420, 331, 40, 20);
		//contentPane.add(checkBox24);
		panelComFundo.add(checkBox24);
		checkboxes[23] = checkBox24;

		JCheckBox checkBox25 = new JCheckBox("25");
		checkBox25.setBounds(470, 331, 40, 20);
		//contentPane.add(checkBox25);
		panelComFundo.add(checkBox25);
		checkboxes[24] = checkBox25;
		
	
        
		checkBox1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				clicouCheckbox(checkBox1, 1);
			}
		});
        
		checkBox2.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox2, 2);
		    }
		});

		checkBox3.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox3, 3);
		    }
		});

		checkBox4.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox4, 4);
		    }
		});

		checkBox5.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox5, 5);
		    }
		});

		checkBox6.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox6, 6);
		    }
		});

		checkBox7.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox7, 7);
		    }
		});

		checkBox8.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox8, 8);
		    }
		});

		checkBox9.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox9, 9);
		    }
		});

		checkBox10.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox10, 10);
		    }
		});

		checkBox11.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox11, 11);
		    }
		});

		checkBox12.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox12, 12);
		    }
		});

		checkBox13.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox13, 13);
		    }
		});

		checkBox14.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox14, 14);
		    }
		});

		checkBox15.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox15, 15);
		    }
		});

		checkBox16.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox16, 16);
		    }
		});

		checkBox17.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox17, 17);
		    }
		});

		checkBox18.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox18, 18);
		    }
		});

		checkBox19.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox19, 19);
		    }
		});

		checkBox20.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox20, 20);
		    }
		});

		checkBox21.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox21, 21);
		    }
		});

		checkBox22.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox22, 22);
		    }
		});

		checkBox23.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox23, 23);
		    }
		});

		checkBox24.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox24, 24);
		    }
		});

		checkBox25.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        clicouCheckbox(checkBox25, 25);
		    }
		});
		
		
		btnApostar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Cria a nova aposta
		        	ConcursoBD concursoBD = new ConcursoBD();
		            Concurso concursoSelecionado = concursoBD.getConcursoAbertoComMenorId();
		            
		            int idConcurso = concursoSelecionado.getId();
		        	
		            Date dataCriacao = new Date();
		            Aposta apostaRealizada = new Aposta(ApostaBD.gerarNovoId(), apostadorAtual, dataCriacao, numerosApostados, verificaPreco(numerosApostados.size()), idConcurso);
		            
		            
		            if (concursoSelecionado == null) {
		                throw new Exception("Não há concursos abertos para apostas.");
		            }
		            
		            ApostaBD apostaBD = new ApostaBD();
		            if (apostaBD.temApostaDuplicada(apostaRealizada, concursoSelecionado.getId())) {
		                throw new Exception("Você já fez uma aposta com os mesmos números neste concurso.");
		            }
		            
		            apostaBD.inserir(apostaRealizada);
		            apostaBD.imprimirApostas();

		            concursoSelecionado.adicionarAposta(apostaRealizada);
		            concursoBD.alterar(concursoSelecionado.getId(), concursoSelecionado);
		            //-------------------------------------------------------
		       
		            
		            
	                for (JCheckBox checkbox : checkboxes) {
	                    checkbox.setSelected(false);
	                }
		            //-------------------------------------------------------

		            JOptionPane.showMessageDialog(contentPane, "Aposta registrada com sucesso!");

		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(contentPane, e2.getMessage());
		        }
		    }
		   
		});

		btnApostar.setBounds(270, 369, 240, 34);
		//contentPane.add(btnApostar);
		panelComFundo.add(btnApostar);
		btnApostar.setEnabled(false);
		
		
		JLabel lblMsg = new JLabel("Selecione entre 15 e 20 números");
		lblMsg.setBounds(295, 140, 225, 14);
		//contentPane.add(lblMsg);
		panelComFundo.add(lblMsg);

		lblValorAposta.setBounds(314, 414, 196, 13);
		//contentPane.add(lblValorAposta);
		panelComFundo.add(lblValorAposta);
		
		ConcursoBD concursoBD = new ConcursoBD();
        Concurso concursoSelecionado = concursoBD.getConcursoAbertoComMenorId();
		
		JLabel lblNumeroConcurso = new JLabel("CONCURSO N° " +concursoSelecionado.getId());
		lblNumeroConcurso.setBounds(343, 115, 134, 14);
		//contentPane.add(lblNumeroConcurso);
		panelComFundo.add(lblNumeroConcurso);
				
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
        
        
        
        
        //----------------------------------------------------------
        JButton btnGerar = new JButton("Gerar");
        btnGerar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        				
      		 
                Set<Integer> unico = new HashSet<>();
                Random rand = new Random();
                
               
                while (unico.size() < 15) {
                    unico.add(rand.nextInt(1, 25));  
                }
                
                System.out.println(unico);  
                
                
                JCheckBox[] checkboxes = {
                    checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6,
                    checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12,
                    checkBox13, checkBox14, checkBox15, checkBox16, checkBox17, checkBox18,
                    checkBox19, checkBox20, checkBox21, checkBox22, checkBox23, checkBox24,checkBox25
                };

                
                for (JCheckBox checkbox : checkboxes) {
                    checkbox.setSelected(false);
                }
                
                
                for (int num : unico) {
                    checkboxes[num - 1].setSelected(true); 
                }

               
                verificarEstadoDoBotaoSalvar();
            
				
        	}
        }); 
 
        
        btnGerar.setBounds(10, 43, 89, 23);
        //contentPane.add(btnGearar);
        panelComFundo.add(btnGerar);
        
        Date dataSorteio = concursoSelecionado.getDataSorteio();
        String dataFormatada = DateUtil.converterDateParaData(dataSorteio);
        
        JLabel lblDataSorteio = new JLabel("Data do Sorteio: " + dataFormatada + " às 20:00h");
        lblDataSorteio.setBounds(540, 171, 225, 14);
        panelComFundo.add(lblDataSorteio);
        
        JLabel lblValorArrecadado = new JLabel("Valor Arrecadado: R$" +concursoSelecionado.calcularArrecadado());
        lblValorArrecadado.setBounds(540, 196, 225, 14);
        panelComFundo.add(lblValorArrecadado);
        
//        System.out.println(concursoSelecionado.isAcumulado());
//        
//        JLabel lblAcumulado = new JLabel("Concurso Acumulado: " +concursoSelecionado.isAcumulado());
//        lblAcumulado.setBounds(540, 221, 225, 14);
//        panelComFundo.add(lblAcumulado);
        
	}
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAposta frame = new TelaAposta(null);
					frame.setSize(800, 700);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}
}

                    
                    
                    
                    
                    
        
        
        
        
        
        
        
        
        
        