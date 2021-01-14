package model;

import java.util.List;

public interface DaoEscola {

	void abrirTransacao() throws DaoException;

	void incluir(Escola d) throws DaoException;

	void alterar(Escola d) throws DaoException;

	void excluir(Escola d) throws DaoException;

	List<Escola> obterObjetos() throws DaoException;

	Escola obterEscolaPeloCnpj(String cnpj) throws DaoException; 

	void commit() throws DaoException;

	void rollback();

}