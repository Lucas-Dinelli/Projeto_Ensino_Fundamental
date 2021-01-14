package controller;

import java.util.ArrayList;
import java.util.List;

import model.DaoEscola;
import model.DaoTurma;
import model.DaoException;
import model.DaoJpaTurma;
import model.DaoManager;
import model.Escola;
import model.Turma;
import model.ModelException;
import viewer.JanelaTurma;

public class CtrlAlterarTurma extends CtrlTurma {
	//
	// ATRIBUTOS
	//
	private int IDTurmaEmAlteracao;
	
	//
	// MÉTODOS
	//
	public CtrlAlterarTurma(CtrlManterTurmas ctrl, Turma turma) throws DaoException {
		super();
		this.ctrlPai = ctrl;
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		List<Escola> conjEscolas = daoEscola.obterObjetos();
		this.janela = new JanelaTurma(this, turma.getSerie(), turma.getCodTurma(),turma.getTurno(), turma.getEscola(), turma.getFoto(), conjEscolas.toArray(), "Alterando Turma");
		IDTurmaEmAlteracao = turma.getId();
	}
	
	public void commitTurma(int serie, int codTurma, String turno, Object selecionado, byte[] foto) throws ModelException, DaoException, ControllerException {
		DaoTurma daoTurma = DaoManager.obterDaoTurma();
		Turma turmaEmAlteracao = daoTurma.obterTurmaPeloID(this.IDTurmaEmAlteracao);
		if(IDTurmaEmAlteracao == 0)
			throw new DaoException("Turma com o id " + this.IDTurmaEmAlteracao + " não encontrado.");
		turmaEmAlteracao.getEscola().removerTurma(turmaEmAlteracao);
		turmaEmAlteracao.setSerie(serie);
		turmaEmAlteracao.setCodTurma(codTurma);
		turmaEmAlteracao.setTurno(turno);
		turmaEmAlteracao.setEscola((Escola)selecionado);
		turmaEmAlteracao.setFoto(foto);
		daoTurma.abrirTransacao();
		daoTurma.alterar(turmaEmAlteracao);
		daoTurma.commit();
		this.encerrarCasoDeUso(daoTurma.obterObjetos().indexOf(turmaEmAlteracao));
	}
	
	
	@Override
	public int getCodigoCorrespondente(int serieEscolhida, Escola escolaEscolhida, String turnoEscolhido) throws DaoException {
			
		int codigoCorrespondente = 0;
			
		if(escolaEscolhida!=null) {
				
			ArrayList<Turma> listaTurmas = new ArrayList<Turma>(new DaoJpaTurma().obterObjetos()); 
				
			codigoCorrespondente = (serieEscolhida*100) + 1;
				
				
			for(int i=listaTurmas.size()-1; i>=0; i--) {
					
				Turma turma = listaTurmas.get(i);
					
				if((turma.getSerie()==serieEscolhida) && (turma.getTurno().equals(turnoEscolhido)) && (turma.getEscola().equals(escolaEscolhida))) {
						
					System.out.println("Aqui: " +turma.getCodTurma());
					if(codigoCorrespondente<=turma.getCodTurma()) {
							
						codigoCorrespondente = turma.getCodTurma() + 1;
					}
						
				}
			}
		}
			
		return(codigoCorrespondente);
	}
}
