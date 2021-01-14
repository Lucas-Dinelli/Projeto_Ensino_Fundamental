package model;

import java.util.Collections;
import java.util.List;

public class DaoJpaTurma implements DaoTurma {
	//
	// MÉTODOS
	//
	public DaoJpaTurma() throws DaoException {
		JPAManager.getEntityManager();
	}

	public void abrirTransacao() throws DaoException {
		try {
			JPAManager.getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public Turma getById(int id) {
		return JPAManager.getEntityManager().find(Turma.class, id);
	}

	public Turma obterTurmaPeloID(int id) {
		return JPAManager.getEntityManager().createQuery("select t from Turma t " + "where t.id = :id", Turma.class)
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Turma> obterObjetos() {
		List<Turma> objetosTurma = JPAManager.getEntityManager().createQuery("FROM " + Turma.class.getName()).getResultList();
		Collections.sort(objetosTurma);
		return objetosTurma;
	}

	public void incluir(Turma turma) throws DaoException {
		try {
			JPAManager.getEntityManager().persist(turma);
		} catch (Exception e) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(e.getMessage());
		}
	}

	public void alterar(Turma turma) throws DaoException {
		try {
			JPAManager.getEntityManager().merge(turma);
		} catch (Exception e) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(e.getMessage());
		}
	}

	public void excluir(Turma turma) throws DaoException {
		try {
			turma = JPAManager.getEntityManager().find(Turma.class, turma.getId());
			JPAManager.getEntityManager().remove(turma);
		} catch (Exception ex) {
			JPAManager.getEntityManager().getTransaction().rollback();
			throw new DaoException(ex.getMessage());
		}
	}

	public void removerPeloId(int id) throws DaoException {
		try {
			Turma turma = getById(id);
			excluir(turma);
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
