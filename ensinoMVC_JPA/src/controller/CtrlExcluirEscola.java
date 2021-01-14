package controller;

import model.DaoEscola;
import model.DaoException;
//import model.DaoJDBCDepartamento;
import model.DaoManager;
import model.Escola;
import model.ModelException;
import viewer.JanelaEscola;

public class CtrlExcluirEscola extends CtrlEscola {
	//
	// ATRIBUTOS
	//
	private Escola escola;
	
	//
	// M�TODOS
	//
	public CtrlExcluirEscola(CtrlManterEscolas ctrl, Escola escola) {
		super();
		this.janela = new JanelaEscola(this, escola.getNome(), escola.getCnpj(), "Confirma a Exclus�o desta Escola?", escola.getListaTurmas().isEmpty());
		this.janela.setVisible(true);
		this.ctrlPai = ctrl;
		this.escola = escola;
	}
	
	public void commitEscola(String nome, String cnpj) throws ModelException, DaoException, ControllerException {
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		Escola escolaParaExclusao = daoEscola.obterEscolaPeloCnpj(this.escola.getCnpj()); 
		if(escolaParaExclusao == null)
			throw new DaoException("Escola com o CNPJ " + this.escola.getCnpj() + " n�o encontrada.");
		daoEscola.abrirTransacao();
		daoEscola.excluir(escolaParaExclusao);
		daoEscola.commit();
		this.encerrarCasoDeUso(0);
	}
	
}
