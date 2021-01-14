package controller;

import model.DaoEscola;
import model.DaoException;
import model.DaoManager;
import model.Escola;
import model.ModelException;
import viewer.JanelaEscola;

abstract public class CtrlEscola {
	protected JanelaEscola janela;
	protected CtrlManterEscolas ctrlPai;
	
	

	public CtrlEscola() {
		super();
	}
	

	abstract public void commitEscola(String nome, String cnpj) throws ModelException, DaoException, ControllerException;
	
	
	//abstract public void rollback() throws DaoException;
	
	public void rollback() throws DaoException {
		
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		
		daoEscola.rollback();
	}
	
	
	
	//...:
	
	public boolean caractereCorretoCnpj(char caractere) {
		
		if(Escola.cnpjCaracteresValidos(Character.toString(caractere))) {
			return(true);
		}
		else {
			return(false);
		}
	}
	
	
	
	
	
	
	public void encerrarCasoDeUso(int verificadorPosicao) throws DaoException {
		this.janela.setVisible(false);
		this.ctrlPai.extendsEncerrado(verificadorPosicao);
	}
	
}