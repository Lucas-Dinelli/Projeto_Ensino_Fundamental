package controller;

import model.DaoException;
import model.Escola;
import model.ModelException;
import viewer.JanelaTurma;

abstract public class CtrlTurma {
	
	protected JanelaTurma janela;
	
	protected CtrlManterTurmas ctrlPai;
	

	public CtrlTurma() {
		super();
	}
	
	

	abstract public void commitTurma(int serie, int codTurma, String turno, Object selecionado, byte[] foto) throws ModelException, DaoException, ControllerException;
	
	
	
	abstract public int getCodigoCorrespondente(int serieEscolhida, Escola escolaEscolhida, String turnoEscolhido) throws DaoException;
	
	
	
	

	public void encerrarCasoDeUso(int verificadorPosicao) throws DaoException {
		this.janela.setVisible(false);
		this.ctrlPai.extendsEncerrado(verificadorPosicao);
	}

}