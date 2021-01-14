package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.EmptyBorder;

import controller.CtrlAlterarTurma;
import controller.CtrlExcluirTurma;
import controller.CtrlTurma;
import model.DaoException;
import model.Escola;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JanelaTurma extends JFrame {

	private CtrlTurma ctrl;
	private JPanel contentPane;
	
	private JSpinner spSerie;
	private JComboBox cbTurno;
	private JTextField tfCodTurma;
	private JComboBox cbEscolas;
	private JLabel lblFoto;
	private JButton btSelecionarFoto;
	private JPanel meuCanvas;

	private File arqFoto;
	private BufferedImage biFoto;
	private JLabel lbMensagem;
	
	boolean isOpening;

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.constructor
	 */
	public JanelaTurma(CtrlTurma c, Object[] itens, String mensagem) {
		this(c, 1, 0, "", null, null, itens, mensagem);
	}

	public JanelaTurma(CtrlTurma c, int serie, int codTurma, String turno, Object selecionado, byte[] foto, Object[] itens, String mensagem) {
		this.ctrl = c;
		isOpening = true;
		setTitle("Turma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 384);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lbSerie = new JLabel("S\u00E9rie:");
		lbSerie.setForeground(new Color(255, 255, 255));
		lbSerie.setFont(new Font("Calibri", Font.BOLD, 16));
		lbSerie.setBounds(67, 72, 38, 20);
		contentPane.add(lbSerie);
		
		JLabel lbTurno = new JLabel("Turno:");
		lbTurno.setForeground(new Color(255, 255, 255));
		lbTurno.setFont(new Font("Calibri", Font.BOLD, 16));
		lbTurno.setBounds(62, 153, 46, 14);
		contentPane.add(lbTurno);

		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		lbCodigo.setForeground(new Color(255, 255, 255));
		lbCodigo.setFont(new Font("Calibri", Font.BOLD, 16));
		lbCodigo.setBounds(257, 150, 59, 20);
		contentPane.add(lbCodigo);
		
		JLabel lbEscola = new JLabel("Escola:");
		lbEscola.setForeground(new Color(255, 255, 255));
		lbEscola.setFont(new Font("Calibri", Font.BOLD, 16));
		lbEscola.setBounds(260, 72, 56, 20);
		contentPane.add(lbEscola);
		
		
		
		
		spSerie = new JSpinner();
		spSerie.setFont(new Font("Tahoma", Font.BOLD, 13));
		spSerie.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				try {
					
					preencherCodigoTurma(serie, selecionado, turno, codTurma);
					
				} catch (DaoException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		spSerie.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		spSerie.setBounds(113, 69, 74, 26);
		JComponent editor = spSerie.getEditor();
		((DefaultEditor)editor).getTextField().setFocusable(false);
		spSerie.setValue(serie);
		contentPane.add(spSerie);

		tfCodTurma = new JTextField(Integer.toString(codTurma));
		tfCodTurma.setFont(new Font("Tahoma", Font.BOLD, 13));
		tfCodTurma.setEditable(false);
		tfCodTurma.setBounds(316, 147, 155, 26);
		contentPane.add(tfCodTurma);
		tfCodTurma.setColumns(10);

		
		
		cbTurno = new JComboBox();
		cbTurno.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbTurno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					preencherCodigoTurma(serie, selecionado, turno, codTurma);
					
				} catch (DaoException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		cbTurno.setModel(new DefaultComboBoxModel(new String[] {"Manh\u00E3", "Tarde", "Noite"}));
		cbTurno.setBounds(113, 148, 102, 24);
		cbTurno.setSelectedItem(turno);
		contentPane.add(cbTurno);

		cbEscolas = new JComboBox(itens);
		cbEscolas.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbEscolas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					preencherCodigoTurma(serie, selecionado, turno, codTurma);
					
				} catch (DaoException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		cbEscolas.setBounds(316, 69, 155, 26);
		cbEscolas.setSelectedItem(selecionado);
		contentPane.add(cbEscolas);
		{
			this.lblFoto = new JLabel("Foto: ");
			lblFoto.setForeground(new Color(255, 255, 255));
			this.lblFoto.setHorizontalAlignment(SwingConstants.RIGHT);
			this.lblFoto.setFont(new Font("Calibri", Font.BOLD, 16));
			this.lblFoto.setBounds(489, 68, 80, 20);
			this.contentPane.add(this.lblFoto);
		}
		this.meuCanvas = new JPanel() {
			
			public Dimension getPreferredSize() {
				return biFoto == null ? new Dimension(270, 270) : new Dimension(biFoto.getWidth(), biFoto.getHeight());
			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (biFoto != null) {
					try {
						int x = (getWidth() - biFoto.getWidth()) / 2;
						int y = (getHeight() - biFoto.getHeight()) / 2;
						g.drawImage(biFoto, x, y, null);
					} catch (Exception ex) {
						biFoto = null;
						JOptionPane.showMessageDialog(null, "Foto inválida.");
					}
				}
			}
		};
		this.meuCanvas.setBackground(Color.WHITE);
		this.meuCanvas.setBounds(577, 10, 270, 270);
		this.contentPane.add(this.meuCanvas);

		this.btSelecionarFoto = new JButton("Selecionar Foto");
		btSelecionarFoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btSelecionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser("C:/Temp");
				int retorno = jfc.showOpenDialog(null);

				if (retorno == JFileChooser.APPROVE_OPTION) {
					arqFoto = jfc.getSelectedFile();
					try {
						biFoto = ImageIO.read(arqFoto);
						repaint();
					} catch (Exception ex) {
						System.out.println("Erro na apresentação do arquivo");
					}
				}
			}
		});
		this.btSelecionarFoto.setBounds(577, 283, 270, 29);
		this.contentPane.add(this.btSelecionarFoto);
		
		this.lbMensagem = new JLabel(mensagem);
		lbMensagem.setForeground(new Color(255, 255, 255));
		this.lbMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		this.lbMensagem.setFont(new Font("Calibri", Font.BOLD, 24));
		this.lbMensagem.setBounds(15, 16, 517, 30);
		this.contentPane.add(this.lbMensagem);
		
		
		
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int serie = Integer.parseInt(spSerie.getValue().toString());
				
				int codTurma = Integer.parseInt(tfCodTurma.getText());
				
				String turno = cbTurno.getSelectedItem().toString();
				
				Object selecionado = cbEscolas.getSelectedItem();
				byte[] foto = null;

				
				if (biFoto != null) {
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					try {
						ImageIO.write(biFoto, "JPG", byteStream);
						foto = byteStream.toByteArray();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				try {
					
					ctrl.commitTurma(serie, codTurma, turno, selecionado, foto);
					
				}  catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}

			}
		});
		btOk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btOk.setBounds(113, 251, 115, 39);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.encerrarCasoDeUso(-1);
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btCancelar.setBounds(316, 251, 115, 39);
		contentPane.add(btCancelar);
		
		
		inserirImagem(foto);
		
		desabilitarEntradasAoExcluir(serie, selecionado, turno);
		
		
		this.setVisible(true);
		
		
		isOpening = false;
		
	}
	
	
	
	
	
	
	private void preencherCodigoTurma(int serieAtual, Object escolaAtual, String turnoAtual, int codTurmaAtual) throws DaoException {
		
		
		if(!(ctrl instanceof CtrlExcluirTurma) && (!isOpening)) {
			
			int serieEscolhida = Integer.parseInt(spSerie.getValue().toString());
			
			Escola escolaEscolhida = (Escola) cbEscolas.getSelectedItem();
			
			String turnoEscolhido = cbTurno.getSelectedItem().toString();
			
			int codigoCorrespondente = ctrl.getCodigoCorrespondente(serieEscolhida, escolaEscolhida, turnoEscolhido);
			
			
			if(ctrl instanceof CtrlAlterarTurma) {
				
				if(serieEscolhida==serieAtual && escolaEscolhida.equals(escolaAtual) && turnoEscolhido.equals(turnoAtual)) {
					
					tfCodTurma.setText(Integer.toString(codTurmaAtual));
				}
				else {
					
					tfCodTurma.setText(Integer.toString(codigoCorrespondente));
				}
			}
			else {
				
				tfCodTurma.setText(Integer.toString(codigoCorrespondente));
			}
		}
		
	}
	
	
	
	
	
	
	private void inserirImagem(byte[] foto) {
		
		if(foto != null) {
			
			ByteArrayInputStream byteStream = new ByteArrayInputStream(foto);
			
			try {
				
				biFoto = ImageIO.read(byteStream);
				repaint();
				
			} catch (IOException e) {
				
				throw new RuntimeException(e);
			}
		}
	}
	
	
	
	
	
	
	
	private void desabilitarEntradasAoExcluir(int serieAtual, Object escolaAtual, String turnoAtual) {
		
		if(ctrl instanceof CtrlExcluirTurma) {
			
			spSerie.setModel(new SpinnerNumberModel(serieAtual, serieAtual, serieAtual, 1));
			JComponent editor = spSerie.getEditor();
			((DefaultEditor)editor).getTextField().setFocusable(false);
			
			cbTurno.setModel(new DefaultComboBoxModel(new String[] {turnoAtual}));
			
			btSelecionarFoto.setEnabled(false);
		}
	}
	
}
