package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CtrlManterTurmas;
import model.DaoException;

public class JanelaListarTurmas extends JFrame {
	private CtrlManterTurmas ctrl;
	private JPanel contentPane;
	private JTextField tfSerie;
	private JTextField tfCodTurma;
	private JLabel lbPos;
	private JTextField tfEscola;
	private JPanel meuCanvas;
	private JLabel lblFoto;
	private BufferedImage biFoto;
	private JTextField tfTurno;
	
	/**
	 * Create the frame.
	 */
	public JanelaListarTurmas(CtrlManterTurmas c) {
		this.ctrl = c;
		setTitle("Turmas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 399);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lbSerie = new JLabel("S\u00E9rie:");
		lbSerie.setForeground(new Color(255, 255, 255));
		lbSerie.setFont(new Font("Calibri", Font.BOLD, 16));
		lbSerie.setBounds(28, 31, 69, 20);
		contentPane.add(lbSerie);
		
		JLabel lbTurno = new JLabel("Turno:");
		lbTurno.setForeground(new Color(255, 255, 255));
		lbTurno.setFont(new Font("Calibri", Font.BOLD, 16));
		lbTurno.setBounds(274, 37, 46, 14);
		contentPane.add(lbTurno);

		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		lbCodigo.setForeground(new Color(255, 255, 255));
		lbCodigo.setFont(new Font("Calibri", Font.BOLD, 16));
		lbCodigo.setBounds(28, 84, 69, 20);
		contentPane.add(lbCodigo);
		
		

		tfSerie = new JTextField();
		tfSerie.setEditable(false);
		tfSerie.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfSerie.setBounds(93, 31, 146, 26);
		contentPane.add(tfSerie);
		tfSerie.setColumns(10);
		
		tfTurno = new JTextField();
		tfTurno.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfTurno.setEditable(false);
		tfTurno.setBounds(334, 31, 146, 26);
		contentPane.add(tfTurno);
		tfTurno.setColumns(10);

		tfCodTurma = new JTextField();
		tfCodTurma.setEditable(false);
		tfCodTurma.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfCodTurma.setBounds(93, 84, 382, 26);
		contentPane.add(tfCodTurma);
		tfCodTurma.setColumns(10);

		JButton btSair = new JButton("Voltar");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ctrl.encerrarCasoDeUso();

			}
		});
		btSair.setFont(new Font("Calibri", Font.PLAIN, 16));
		btSair.setBounds(436, 257, 115, 29);
		contentPane.add(btSair);

		JLabel lbEscola = new JLabel("Escola:");
		lbEscola.setForeground(new Color(255, 255, 255));
		lbEscola.setFont(new Font("Calibri", Font.BOLD, 16));
		lbEscola.setBounds(28, 146, 105, 20);
		contentPane.add(lbEscola);

		JButton btPrimeiro = new JButton("Primeiro");
		btPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarPrimeiro();
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btPrimeiro.setFont(new Font("Calibri", Font.PLAIN, 16));
		btPrimeiro.setBounds(28, 198, 115, 29);
		contentPane.add(btPrimeiro);

		JButton btUltimo = new JButton("\u00DAltimo");
		btUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarUltimo();
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btUltimo.setFont(new Font("Calibri", Font.PLAIN, 16));
		btUltimo.setBounds(436, 198, 115, 29);
		contentPane.add(btUltimo);

		JButton btAnterior = new JButton("Anterior");
		btAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarAnterior();
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btAnterior.setFont(new Font("Calibri", Font.PLAIN, 16));
		btAnterior.setBounds(164, 198, 115, 29);
		contentPane.add(btAnterior);

		JButton btProximo = new JButton("Pr\u00F3ximo");
		btProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarProximo();
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btProximo.setFont(new Font("Calibri", Font.PLAIN, 16));
		btProximo.setBounds(300, 198, 115, 29);
		contentPane.add(btProximo);

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.iniciarCasoDeUsoIncluirTurma();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		btIncluir.setFont(new Font("Calibri", Font.PLAIN, 16));
		btIncluir.setBounds(28, 257, 115, 29);
		contentPane.add(btIncluir);

		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.iniciarCasoDeUsoAlterarTurma();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btAlterar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btAlterar.setBounds(164, 257, 115, 29);
		contentPane.add(btAlterar);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.iniciarCasoDeUsoExcluirTurma();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
		
		);
		btExcluir.setFont(new Font("Calibri", Font.PLAIN, 16));
		btExcluir.setBounds(300, 257, 115, 29);
		contentPane.add(btExcluir);

		lbPos = new JLabel("Listando ");
		lbPos.setForeground(new Color(230, 230, 250));
		this.lbPos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lbPos.setFont(new Font("Calibri", Font.BOLD, 16));
		lbPos.setBounds(334, 307, 217, 20);
		contentPane.add(lbPos);
		
		
		this.tfEscola = new JTextField();
		tfEscola.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfEscola.setEditable(false);
		this.tfEscola.setBounds(93, 143, 382, 26);
		this.contentPane.add(this.tfEscola);
		this.tfEscola.setColumns(10);
		
		// Classe Interna Anônima
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
		this.meuCanvas.setBounds(565, 16, 270, 270);
		this.contentPane.add(this.meuCanvas);

		this.lblFoto = new JLabel("Foto: ");
		lblFoto.setForeground(new Color(255, 255, 255));
		this.lblFoto.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblFoto.setFont(new Font("Calibri", Font.BOLD, 16));
		this.lblFoto.setBounds(479, 36, 80, 20);
		this.contentPane.add(this.lblFoto);

		this.setVisible(true);
	}

	
	
	
	
	public void listarTurma(int serie, int codTurma, String turno, String escola, byte[] foto, int pos, int total) {
		
		tfSerie.setText(Integer.toString(serie));
		
		tfCodTurma.setText(Integer.toString(codTurma));
		
		tfTurno.setText(turno);
		
		tfEscola.setText(escola);
		
		lbPos.setText("Listando " + (pos + 1) + "/" + total);
		
		
		if (foto != null) {

			ByteArrayInputStream byteStream = new ByteArrayInputStream(foto);
			try {
				biFoto = ImageIO.read(byteStream);
				repaint();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		else {
			biFoto = null;
			repaint();
		}
	}
}
