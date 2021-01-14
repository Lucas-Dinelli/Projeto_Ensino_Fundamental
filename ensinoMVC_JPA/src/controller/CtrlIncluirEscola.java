package controller;

import model.DaoEscola;
import model.DaoException;
//import model.DaoJDBCDepartamento;
import model.DaoManager;
import model.Escola;
import model.ModelException;
import viewer.JanelaEscola;

public class CtrlIncluirEscola extends CtrlEscola {
	//
	// MÉTODOS
	//
	public CtrlIncluirEscola(CtrlManterEscolas ctrl) {
		super();
		this.janela = new JanelaEscola(this, "Incluindo Escola");
		this.janela.setVisible(true);
		this.ctrlPai = ctrl;
	}
	
	public void commitEscola(String nome, String cnpj) throws ModelException, DaoException, ControllerException {
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		daoEscola.abrirTransacao();
		Escola escola = new Escola(nome, cnpj);
		daoEscola.incluir(escola);
		daoEscola.commit();
		this.encerrarCasoDeUso(daoEscola.obterObjetos().indexOf(escola));
	}
	
}
