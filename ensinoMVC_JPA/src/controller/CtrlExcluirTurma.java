package controller;

import model.DaoEscola;
import model.DaoTurma;
import model.DaoException;
import model.DaoManager;
import model.Escola;
import model.Turma;
import model.ModelException;
import viewer.JanelaTurma;

public class CtrlExcluirTurma extends CtrlTurma {
	//
	// ATRIBUTOS
	//
	private Turma turma;
	
	//
	// MÉTODOS
	//
	public CtrlExcluirTurma(CtrlManterTurmas ctrl, Turma turma) throws DaoException {
		super();
		this.ctrlPai = ctrl;
		Escola[] conjEscolas = {turma.getEscola()};
		this.janela = new JanelaTurma(this, turma.getSerie(), turma.getCodTurma(), turma.getTurno(), turma.getEscola(), turma.getFoto(), conjEscolas, "Confirma a Exclusão desta Turma?");
		this.turma = turma;
	}
	
	public void commitTurma(int serie, int codTurma, String turno, Object selecionado, byte[] foto) throws ModelException, DaoException, ControllerException {
		if(selecionado instanceof Escola) {
			DaoTurma daoTurma = DaoManager.obterDaoTurma();
			DaoEscola daoEscola = DaoManager.obterDaoEscola();
			Escola escola = daoEscola.obterEscolaPeloCnpj(((Escola)selecionado).getCnpj());
			Turma turmaParaExclusao = daoTurma.obterTurmaPeloID(this.turma.getId());  
			if(turmaParaExclusao == null)
				throw new DaoException("Turma com o id " + this.turma.getId() + " não encontrado.");
			daoTurma.abrirTransacao();
			daoTurma.excluir(turmaParaExclusao);
			int indexTurmaExcluida = escola.removerTurma(turmaParaExclusao);
			reordenarCodigosDeTurma(codTurma, escola, indexTurmaExcluida);
			daoTurma.commit();
			this.encerrarCasoDeUso(0);
		}
	}

	@Override
	public int getCodigoCorrespondente(int serieEscolhida, Escola escolaEscolhida, String turnoEscolhido) {
		
		return -1;
	}
	
	private void reordenarCodigosDeTurma(int codTurma, Escola escola, int indexTurmaExcluida) throws DaoException, ModelException {
		
		for(int i=indexTurmaExcluida; i<escola.getListaTurmas().size(); i++) {
			if(escola.getListaTurmas().get(i).getCodTurma()==codTurma+1) {
				escola.getListaTurmas().get(i).setCodTurma(codTurma);
				codTurma = codTurma + 1;
			}
			else {
				break;
			}
		}
	}
}
