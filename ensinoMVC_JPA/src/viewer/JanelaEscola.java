package viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlEscola;
import controller.CtrlExcluirEscola;

import model.DaoException;
import model.ModelException;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class JanelaEscola extends JFrame {
	private CtrlEscola ctrl;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCnpj;

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.constructor
	 */
	public JanelaEscola(CtrlEscola c, String mensagem) {
		this(c, "", "", mensagem, true);
	}

	public JanelaEscola(CtrlEscola c, String nome, String cnpj, String mensagem, boolean isToCommit) {
		super();
		this.ctrl = c;
		setTitle("Escola");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 267);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setForeground(new Color(255, 255, 255));
		lbNome.setFont(new Font("Calibri", Font.BOLD, 16));
		lbNome.setBounds(42, 67, 69, 20);
		contentPane.add(lbNome);

		JLabel lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setForeground(new Color(255, 255, 255));
		lbCnpj.setFont(new Font("Calibri", Font.BOLD, 16));
		lbCnpj.setBounds(42, 114, 69, 20);
		contentPane.add(lbCnpj);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfNome.setBounds(93, 62, 146, 26);
		contentPane.add(tfNome);
		tfNome.setText(nome);
		tfNome.setColumns(10);

		tfCnpj = new JTextField();
		tfCnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(tfCnpj.getText().length()>12 &&(e.getKeyCode()!=8 && e.getKeyCode()!=37 && e.getKeyCode()!=39)) {
					tfCnpj.setText(tfCnpj.getText().substring(0, 13));
				}
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				for(int i=0; i<tfCnpj.getText().length(); i++) {
					char caractere = tfCnpj.getText().charAt(i);
					if(!c.caractereCorretoCnpj(caractere)) {
						tfCnpj.setText(tfCnpj.getText().substring(0, i));
						break;
					}
				}
			}
		});
		
		tfCnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				for(int i=0; i<tfCnpj.getText().length(); i++) {
					char caractere = tfCnpj.getText().charAt(i);
					if(!c.caractereCorretoCnpj(caractere)) {
						tfCnpj.setText(tfCnpj.getText().substring(0, i));
						break;
					}
				}
				
			}
		});
		
		tfCnpj.setFont(new Font("Calibri", Font.PLAIN, 16));
		tfCnpj.setBounds(93, 109, 424, 26);
		contentPane.add(tfCnpj);
		tfCnpj.setText(cnpj);
		tfCnpj.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = tfNome.getText();
				String cnpj = tfCnpj.getText();

				try {
					
					if(isToCommit) {
						
						ctrl.commitEscola(nome, cnpj);
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Esta Escola possui uma ou mais turmas vinculadas a ela!", "Não Permitido", 0);
					}
					
				} catch (ModelException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", 0);
					
					try {
						
						ctrl.rollback();
						
					} catch (DaoException e2) {
						
						e2.printStackTrace();
					}
					
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", 0);
					
				}
			}

		});
		btOk.setFont(new Font("Calibri", Font.BOLD, 16));
		btOk.setBounds(120, 166, 115, 29);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					
					ctrl.encerrarCasoDeUso(-1);
					
				} catch (DaoException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}

			}
		});
		btCancelar.setFont(new Font("Calibri", Font.BOLD, 16));
		btCancelar.setBounds(328, 164, 115, 29);
		contentPane.add(btCancelar);
		
		JLabel lbMensagem = new JLabel(mensagem);
		lbMensagem.setForeground(new Color(255, 255, 255));
		lbMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbMensagem.setFont(new Font("Calibri", Font.BOLD, 24));
		lbMensagem.setBounds(42, 16, 475, 30);
		this.contentPane.add(lbMensagem);
		
		
		desabilitarEntradasAoExcluir();
		
		this.setVisible(true);
	}
	
	
	
	
	
	private void desabilitarEntradasAoExcluir() {
		
		if(ctrl instanceof CtrlExcluirEscola) {
			
			tfNome.setEditable(false);
			
			tfCnpj.setEditable(false);
			
		}
	}
}
