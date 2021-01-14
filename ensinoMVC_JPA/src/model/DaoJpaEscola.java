package model;

import java.util.Collections;
import java.util.List;

public class DaoJpaEscola implements DaoEscola {
	//
	// MÉTODOS
	//
	public DaoJpaEscola() throws DaoException {
		JPAManager.getEntityManager();
	}

	public void abrirTransacao() throws DaoException {
		try {
			JPAManager.getEntityManager().getTransaction().begin();
		} catch (Exception e1) {
			throw new DaoException(e1.getMessage());
		}
	}

	public Escola getById(int id) {
		return JPAManager.getEntityManager().find(Escola.class, id);
	}

	public Escola obterEscolaPeloCnpj(String cnpj) {		
		return JPAManager.getEntityManager().createQuery(
				"select e from Escola e " +  
				"where e.cnpj = :cnpj",Escola.class)
					.setParameter("cnpj", cnpj)
					.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Escola> obterObjetos() {
		List<Escola> objetosEscola = JPAManager.getEntityManager().createQuery("FROM " + Escola.class.getName()).getResultList();
		Collections.sort(objetosEscola);
		return objetosEscola;
	}

	public void incluir(Escola escola) throws DaoException {
		try {
			JPAManager.getEntityManager().persist(escola);
		} catch (Exception ex) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(ex.getMessage());
		}
	}

	public void alterar(Escola escola) throws DaoException{
		try {
			JPAManager.getEntityManager().merge(escola);
		} catch (Exception ex) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(ex.getMessage());
		}
	}

	public void excluir(Escola escola) throws DaoException {
		try {
			escola = JPAManager.getEntityManager().find(Escola.class, escola.getId());
			JPAManager.getEntityManager().remove(escola);
		} catch (Exception ex) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(ex.getMessage());
		}
	}

	public void removerPeloId(int id) throws DaoException {
		try {
			Escola escola = getById(id);
			excluir(escola);
		} catch (Exception ex) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(ex.getMessage());
		}
	}

	@Override
	public void commit() throws DaoException {
		try {
			JPAManager.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void rollback() {
		JPAManager.getEntityManager().getTransaction().rollback();
	}
}
