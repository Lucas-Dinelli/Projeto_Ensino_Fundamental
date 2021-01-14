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

public class CtrlIncluirTurma extends CtrlTurma {
	//
	// MÉTODOS
	//
	public CtrlIncluirTurma(CtrlManterTurmas ctrl) throws DaoException {
		super();
		this.ctrlPai = ctrl;
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		List<Escola> conjEscolas = daoEscola.obterObjetos();
		this.janela = new JanelaTurma(this, conjEscolas.toArray(), "Incluindo Turma");
	}

	public void commitTurma(int serie, int codTurma, String turno, Object selecionado, byte[] foto) throws ModelException, DaoException, ControllerException {
		if (selecionado instanceof Escola) {
			DaoTurma daoTurma = DaoManager.obterDaoTurma();
			DaoEscola daoEscola = DaoManager.obterDaoEscola();			
			Escola escola = daoEscola.obterEscolaPeloCnpj(((Escola)selecionado).getCnpj());
			Turma turma = new Turma(serie, codTurma, turno, escola, foto);
			daoTurma.abrirTransacao();
			daoTurma.incluir(turma);
			daoTurma.commit();
			this.encerrarCasoDeUso(daoTurma.obterObjetos().indexOf(turma));
		} 
		else {
			throw new ModelException("Não se indicou uma escola para a turma");
		}
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
					
					
					if(codigoCorrespondente<=turma.getCodTurma()) {
						
						codigoCorrespondente = turma.getCodTurma() + 1;
					}
					
				}
			}
		}
		
		return(codigoCorrespondente);
	}
	
}
