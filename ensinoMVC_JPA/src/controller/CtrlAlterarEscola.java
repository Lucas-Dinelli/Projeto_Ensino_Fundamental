package controller;

import model.DaoEscola;
import model.DaoException;
//import model.DaoJDBCDepartamento;
import model.DaoManager;
import model.Escola;
import model.ModelException;
import viewer.JanelaEscola;

public class CtrlAlterarEscola extends CtrlEscola {
	//
	// ATRIBUTOS
	//
	private Escola escola;
	
	//
	// MÉTODOS
	//
	public CtrlAlterarEscola(CtrlManterEscolas ctrl, Escola escola) {
		super();
		this.janela = new JanelaEscola(this, escola.getNome(), escola.getCnpj(), "Alterando Escola", true);
		this.janela.setVisible(true);
		this.ctrlPai = ctrl;
		this.escola = escola;
	}
	
	public void commitEscola(String nome, String cnpj) throws ModelException, DaoException, ControllerException {
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		Escola escolaEmAlteracao = daoEscola.obterEscolaPeloCnpj(this.escola.getCnpj()); //this.depto.getSigla()
		if(escolaEmAlteracao == null)
			throw new DaoException("Escola com o CNPJ " + this.escola.getCnpj() + " não encontrada.");
		escolaEmAlteracao.setNome(nome);;
		escolaEmAlteracao.setCnpj(cnpj);;
		daoEscola.abrirTransacao();
		daoEscola.alterar(escolaEmAlteracao);
		daoEscola.commit();
		this.encerrarCasoDeUso(daoEscola.obterObjetos().indexOf(escolaEmAlteracao));
	}
	
}
