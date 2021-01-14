package controller;

import java.util.ArrayList;
import java.util.List;

import model.DaoEscola; // Injeção de Dependência (em tempo de execução)
import model.DaoException;
import model.DaoManager;
import model.Escola;
import viewer.JanelaListarEscolas;

public class CtrlManterEscolas {
	final public static int PRIMEIRO = 0;
	final public static int ULTIMO = Integer.MAX_VALUE;
	final public static int PROXIMO = 1;
	final public static int ANTERIOR = -1;

	//
	// ATRIBUTOS
	//
	private JanelaListarEscolas  janela;
	
	private CtrlEscola 		   ctrlExtends;
	
	private String cnpjAtual;

	//
	// MÉTODOS
	//
	public CtrlManterEscolas() throws DaoException {
		super();
		this.janela = new JanelaListarEscolas(this);
		this.listarPrimeiro();
	}

	public void iniciarCasoDeUsoIncluirEscola() {
		if (this.ctrlExtends == null)
			this.ctrlExtends = new CtrlIncluirEscola(this);
	}

	public void extendsEncerrado(int verificadorPosicao) throws DaoException {
		
		this.ctrlExtends = null;
		
		if(verificadorPosicao!=-1) {
			this.listarAtual(verificadorPosicao);
		}
	}

	public void iniciarCasoDeUsoAlterarEscola() throws DaoException {
		if (this.ctrlExtends == null && this.cnpjAtual != null) {
			DaoEscola dao = DaoManager.obterDaoEscola();
			this.ctrlExtends = new CtrlAlterarEscola(this, dao.obterEscolaPeloCnpj(cnpjAtual));
		}
	}

	public void iniciarCasoDeUsoExcluirEscola() throws DaoException {
		if (this.ctrlExtends == null && this.cnpjAtual != null) {
			DaoEscola dao = DaoManager.obterDaoEscola();
			this.ctrlExtends = new CtrlExcluirEscola(this, dao.obterEscolaPeloCnpj(cnpjAtual));
		}
	}
	
	public void iniciarCasoDeUsoManterTurmas(Object turmaEscolhida) throws DaoException {
		this.janela.setVisible(false);
		new CtrlManterTurmas(turmaEscolhida);
	}

	public void listarAtual(int acao) throws DaoException {
		DaoEscola daoEscola = DaoManager.obterDaoEscola();
		List<Escola> lista = new ArrayList<Escola>(daoEscola.obterObjetos());
		int tamanho = lista.size();
		if (tamanho == 0) {
			this.janela.listarEscola("", "", null, -1, 0);
			this.cnpjAtual = null;
			return;
		}
		int posAtual = 0;
		Escola escolaAtual = null;
		if (this.cnpjAtual != null) {
			for (Escola e : lista)
				if (e.getCnpj().compareTo(this.cnpjAtual) == 0) {
					escolaAtual = e;
					break;
				} else
					posAtual++;
			if (posAtual == tamanho)
				posAtual = 0;
		}

		switch (acao) {
		case PRIMEIRO:
			escolaAtual = lista.get(0);
			break;
		case PROXIMO:
			if (posAtual != tamanho - 1) {
				escolaAtual = lista.get(posAtual + 1);
			}
			break;
		case ANTERIOR:
			if (posAtual != 0)
				escolaAtual = lista.get(posAtual - 1);
			break;
		case ULTIMO:
			escolaAtual = lista.get(tamanho - 1);
			break;
		default:
			escolaAtual = lista.get(acao);
		}
		
		this.cnpjAtual = escolaAtual.getCnpj();
		posAtual = lista.indexOf(escolaAtual);
		this.janela.listarEscola(escolaAtual.getNome(), escolaAtual.getCnpj(), 
				escolaAtual.getListaTurmas().toArray(), posAtual, tamanho);
	}

	public void listarPrimeiro() throws DaoException {
		this.listarAtual(PRIMEIRO);
	}

	public void listarAnterior() throws DaoException {
		this.listarAtual(ANTERIOR);
	}

	public void listarProximo() throws DaoException {
		this.listarAtual(PROXIMO);
	}

	public void listarUltimo() throws DaoException {
		this.listarAtual(ULTIMO);
	}

	public void encerrarCasoDeUso() {
		new CtrlPrograma();
		this.janela.setVisible(false);
	}
}
