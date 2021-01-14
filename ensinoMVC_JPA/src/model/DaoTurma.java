package model;

import java.util.List;

public interface DaoTurma {

	void abrirTransacao() throws DaoException;

	void incluir(Turma turma) throws DaoException;

	void alterar(Turma turma) throws DaoException;

	void excluir(Turma turma) throws DaoException;

	List<Turma> obterObjetos() throws DaoException;

	Turma obterTurmaPeloID(int id) throws DaoException;;

	void commit() throws DaoException;

	void rollback();

}