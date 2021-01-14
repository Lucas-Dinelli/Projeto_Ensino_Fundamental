package viewer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlPrograma;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class JanelaPrograma extends JFrame {

	private CtrlPrograma ctrl;
	private JPanel contentPane;
	
	private JLabel lbImagem;

	/**
	 * Create the frame.
	 */
	public JanelaPrograma(CtrlPrograma c) {
		this.ctrl = c;
		setTitle("Ensino Fundamental");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);          
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		lbImagem = new JLabel("");
		lbImagem.setBounds(348, 55, 220, 167);
		contentPane.add(lbImagem);
		
		inserirImagem("turma.png");
		
		
		JButton btTurmas = new JButton("Manter Turmas");
		btTurmas.setForeground(new Color(255, 255, 255));
		btTurmas.setBackground(new Color(0, 128, 128));
		btTurmas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btTurmas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				inserirImagem("turma.png");
				
			}
		});
		btTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ctrl.iniciarCasoDeUsoManterTurmas();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}	
				
			}
		});
		btTurmas.setBounds(82, 50, 188, 76);
		btTurmas.setFocusPainted(false);
		contentPane.add(btTurmas);
		
		JButton btEscolas = new JButton("Manter Escolas");
		btEscolas.setForeground(new Color(255, 255, 255));
		btEscolas.setBackground(new Color(0, 128, 0));
		btEscolas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btEscolas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				inserirImagem("livro.png");
			}
		});
		btEscolas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ctrl.iniciarCasoDeUsoManterEscolas();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btEscolas.setBounds(82, 155, 188, 76);
		btEscolas.setFocusPainted(false);
		contentPane.add(btEscolas);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ctrl.encerrarPrograma();
				
			}
		});
		btSair.setBackground(new Color(128, 0, 0));
		btSair.setForeground(Color.white);
		btSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btSair.setBackground(Color.red);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				btSair.setBackground(new Color(128, 0, 0));
			}
		});
		btSair.setBounds(245, 280, 106, 54);    
		btSair.setBorderPainted(false);
		btSair.setFocusPainted(false);
		contentPane.add(btSair);
		
		
		this.setVisible(true);
	}
		
	
	
	
	
	
	// Inserir imagens alternadas
	
	private void inserirImagem(String nomeDoArquivoImagem) {
		
		ImageIcon imagemIcon = new ImageIcon("img/" + nomeDoArquivoImagem);
		
		lbImagem.setIcon(imagemIcon);
		
	}
	
}
