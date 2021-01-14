package viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlManterEscolas;
import model.DaoException;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JanelaListarEscolas extends JFrame {

	private CtrlManterEscolas ctrl;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCnpj;
	private JLabel lbPos;
	private JScrollPane scrollPane;
	private JList listaTurmas;

	/**
	 * Create the frame.
	 */
	public JanelaListarEscolas(CtrlManterEscolas c) {
		this.ctrl = c;
		setTitle("Escolas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(28, 31, 69, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CNPJ:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBounds(28, 78, 69, 20);
		contentPane.add(lblNewLabel_1);

		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfNome.setBounds(93, 31, 146, 26);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfCnpj = new JTextField();
		tfCnpj.setEditable(false);
		tfCnpj.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		tfCnpj.setBounds(93, 78, 424, 26);
		contentPane.add(tfCnpj);
		tfCnpj.setColumns(10);

		JButton btSair = new JButton("Voltar");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ctrl.encerrarCasoDeUso();

			}
		});
		btSair.setFont(new Font("Calibri", Font.PLAIN, 16));
		btSair.setBounds(438, 435, 115, 29);
		contentPane.add(btSair);

		JLabel lblNewLabel_2 = new JLabel("Turmas:");
		lblNewLabel_2.setBackground(new Color(240, 240, 240));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 126, 105, 20);
		contentPane.add(lblNewLabel_2);

		JButton btPrimeiro = new JButton("Primeiro");
		btPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarPrimeiro();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				

			}
		});
		btPrimeiro.setFont(new Font("Calibri", Font.PLAIN, 16));
		btPrimeiro.setBounds(30, 376, 115, 29);
		contentPane.add(btPrimeiro);

		JButton btUltimo = new JButton("\u00DAltimo");
		btUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarUltimo();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}				

			}
		});
		btUltimo.setFont(new Font("Calibri", Font.PLAIN, 16));
		btUltimo.setBounds(438, 376, 115, 29);
		contentPane.add(btUltimo);

		JButton btAnterior = new JButton("Anterior");
		btAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarAnterior();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}	


			}
		});
		btAnterior.setFont(new Font("Calibri", Font.PLAIN, 16));
		btAnterior.setBounds(166, 376, 115, 29);
		contentPane.add(btAnterior);

		JButton btProximo = new JButton("Pr\u00F3ximo");
		btProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrl.listarProximo();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}	
				
			}
		});
		btProximo.setFont(new Font("Calibri", Font.PLAIN, 16));
		btProximo.setBounds(302, 376, 115, 29);
		contentPane.add(btProximo);

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ctrl.iniciarCasoDeUsoIncluirEscola();

			}
		});
		btIncluir.setFont(new Font("Calibri", Font.PLAIN, 16));
		btIncluir.setBounds(30, 435, 115, 29);
		contentPane.add(btIncluir);

		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ctrl.iniciarCasoDeUsoAlterarEscola();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btAlterar.setFont(new Font("Calibri", Font.PLAIN, 16));
		btAlterar.setBounds(166, 435, 115, 29);
		contentPane.add(btAlterar);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.iniciarCasoDeUsoExcluirEscola();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btExcluir.setFont(new Font("Calibri", Font.PLAIN, 16));
		btExcluir.setBounds(302, 435, 115, 29);
		contentPane.add(btExcluir);

		lbPos = new JLabel("Listando ");
		lbPos.setForeground(new Color(255, 255, 255));
		this.lbPos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lbPos.setFont(new Font("Calibri", Font.BOLD, 16));
		lbPos.setBounds(336, 488, 217, 20);
		contentPane.add(lbPos);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(129, 126, 386, 218);
		this.contentPane.add(this.scrollPane);
		
		this.listaTurmas = new JList();
		listaTurmas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1 && e.getClickCount()==2) {
					try {
						ctrl.iniciarCasoDeUsoManterTurmas(listaTurmas.getSelectedValue());
					} catch (DaoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		listaTurmas.setFont(new Font("Calibri", Font.BOLD, 15));
		this.scrollPane.setViewportView(this.listaTurmas);
		
		this.setVisible(true);
	}

	public void listarEscola(String nome, String cnpj, Object[] turmas, int pos, int total) {
		tfNome.setText(nome);
		tfCnpj.setText(cnpj);
		lbPos.setText("Listando " + (pos + 1) + "/" + total);
		DefaultListModel model = new DefaultListModel();
		if (turmas != null)
			for (Object o : turmas)
				model.addElement(o);
		listaTurmas.setModel(model);
	}
}
