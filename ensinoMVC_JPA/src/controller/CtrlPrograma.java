package controller;

import model.DaoException;
import viewer.JanelaPrograma;

public class CtrlPrograma {

	public static void main(String[] args) {
		new CtrlPrograma();
	}

	private JanelaPrograma janela;
	
	public CtrlPrograma() {
		this.janela = new JanelaPrograma(this);
	}
	
	public void iniciarCasoDeUsoManterTurmas() throws DaoException {
		this.janela.setVisible(false);
		new CtrlManterTurmas();
	}

	public void iniciarCasoDeUsoManterEscolas() throws DaoException {
		this.janela.setVisible(false);
		new CtrlManterEscolas();
	}

	public void encerrarPrograma() {
		System.exit(0);
	}
}
