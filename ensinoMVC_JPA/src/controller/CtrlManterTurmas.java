package controller;

import java.util.ArrayList;
import java.util.List;

import model.DaoTurma;
import model.DaoException;
import model.DaoManager;
import model.Turma;
import viewer.JanelaListarTurmas;

public class CtrlManterTurmas {
	final public static int PRIMEIRO = 0;
	final public static int ULTIMO = Integer.MAX_VALUE;
	final public static int PROXIMO = 1;
	final public static int ANTERIOR = -1;

	//
	// ATRIBUTOS
	//
	private JanelaListarTurmas janela;
	private CtrlTurma ctrlExtends;
	private int idAtual;

	//
	// M�TODOS
	//
	public CtrlManterTurmas() throws DaoException {
		super();
		this.janela = new JanelaListarTurmas(this);
		this.listarPrimeiro();

	}
	
	public CtrlManterTurmas(Object turmaEscolhida) throws DaoException {
		super();
		this.janela = new JanelaListarTurmas(this);
		Turma turma = (Turma) turmaEscolhida;
		DaoTurma daoTurma = DaoManager.obterDaoTurma();
		this.listarAtual(daoTurma.obterObjetos().indexOf(turma));
	}

	public void iniciarCasoDeUsoIncluirTurma() throws DaoException {
		if (this.ctrlExtends == null)
			this.ctrlExtends = new CtrlIncluirTurma(this);
	}

	public void iniciarCasoDeUsoAlterarTurma() throws DaoException {
		if (this.ctrlExtends == null && this.idAtual != 0) {
			DaoTurma dao = DaoManager.obterDaoTurma();
			this.ctrlExtends = new CtrlAlterarTurma(this, dao.obterTurmaPeloID(idAtual));
		}
	}

	public void iniciarCasoDeUsoExcluirTurma() throws DaoException {
		if (this.ctrlExtends == null && this.idAtual != 0) {
			DaoTurma dao = DaoManager.obterDaoTurma();
			this.ctrlExtends = new CtrlExcluirTurma(this, dao.obterTurmaPeloID(idAtual));
		}
	}

	public void extendsEncerrado(int verificadorPosicao) throws DaoException {
		
		this.ctrlExtends = null;
		
		if(verificadorPosicao!=-1) {
			this.listarAtual(verificadorPosicao);
		}
	}

	public void listarAtual(int acao) throws DaoException {
		// Instancio o DAOTurma para pegar o estado atual dos objetos Turma que est�o persistidos na base de dados.
		DaoTurma daoTurma = DaoManager.obterDaoTurma();
		
		DaoManager.obterDaoEscola().obterObjetos();
		
		// Instancio um List para facilitar o processo de navega��o por posi��o, j� que o m�todo obterObjetos() nos d� um Set (n�o permite buscar por posi��o).
		List<Turma> lista = new ArrayList<Turma>(daoTurma.obterObjetos());
		
		// Verifico o n�mero de objetos existentes
		int tamanho = lista.size();
		
		// Se o tamanho � igual a zero, n�o h� objetos para serem listados.
		if (tamanho == 0) {
			
			// Mando uma mensagem para a janela listar tudo vazio
			this.janela.listarTurma(0, 0, "", "", null, -1, 0);
			
			// O atributo atual fica com valor null, indicando que n�o h� objeto sendo exibido.
			this.idAtual = 0;
			
			// Encerro o m�todo.
			return;
		}

		// A lista de objetos a serem exibidos n�o est� vazia e, ent�o, verifico em que posi��o est� o objeto que estava sendo exibido antes (this.atual).

		// Crio a vari�vel (posAtual) para guardar a nova posi��o do (this.atual).
		int posAtual = 0;
		
		// Se algu�m estava sendo exibindo, (this.cpfAtual) � diferente de null. Dessa forma, irei buscar a posi��o dele. 
		// Caso seja null, vou arbitrar a (posAtual) como zero para listar o primeiro objeto.
		Turma turmaAtual = null;
		
		if (this.idAtual != 0) {
			
			// Para cada objeto Turma na lista:
			for (Turma turma : lista) {
				
				// Verifico se o conte�do (n�o o ponteiro) do objeto que estou exibindo � igual ao objeto da lista recebida. 
				// Caso seja, encerro o loop e guardo o (posAtual).
				if (turma.getId()==this.idAtual) {
					turmaAtual = turma;
					break;
				} else
					// Incremento o (posAtual).
					posAtual++;
			}
			// Se o (posAtual) for igual ao tamanho, � porque o objeto que estava sendo exibindo foi removido por outro usu�rio. 
			// Ent�o, vamos arbitrar a apresenta��o do primeiro objeto.
			if (posAtual == tamanho)
				posAtual = 0;
		}

		// Executo a a��o passada:
		switch (acao) {
		case PRIMEIRO:
			turmaAtual = lista.get(0);
			posAtual = 0;
			break;
		case PROXIMO:
			if (posAtual != tamanho - 1) {
				turmaAtual = lista.get(posAtual + 1);
				posAtual++;
			}
			break;
		case ANTERIOR:
			if (posAtual != 0) {
				turmaAtual = lista.get(posAtual - 1);
				posAtual--;
			}
			break;
		case ULTIMO:
			turmaAtual = lista.get(tamanho - 1);
			posAtual = tamanho - 1;
			break;
		default:
			turmaAtual = lista.get(acao);
		}
		
		this.idAtual = turmaAtual.getId();
		posAtual = lista.indexOf(turmaAtual);
		
		// Mando a janela exibir a Turma atual.
		this.janela.listarTurma(turmaAtual.getSerie(), turmaAtual.getCodTurma(), turmaAtual.getTurno(),
				turmaAtual.getEscola().toString(), turmaAtual.getFoto(), posAtual, tamanho);
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
