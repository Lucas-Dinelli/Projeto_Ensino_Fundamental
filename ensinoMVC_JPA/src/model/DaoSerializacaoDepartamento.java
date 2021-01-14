/*package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DaoSerializacaoDepartamento implements DaoDepartamento {
	//
	// ATRIBUTOS
	//
	private static boolean emTransacao = false;
	private List<Escola> conjDepartamento;
	private boolean proprietarioTransacao = false;

	//
	// MÉTODOS
	//
	public DaoSerializacaoDepartamento() {
		this.proprietarioTransacao = false;
	}

	public DaoSerializacaoDepartamento(boolean abrir) throws DaoException {
		if (abrir)
			this.abrirTransacao();
		else
			this.proprietarioTransacao = false;
	}

	private void lerObjetos() {
		try {
			FileInputStream f = new FileInputStream("departamento.dat");
			ObjectInputStream ois = new ObjectInputStream(f);
			this.conjDepartamento = (List<Escola>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			this.conjDepartamento = new ArrayList<Escola>();
		}
	}

	@Override
	public void abrirTransacao() throws DaoException {
		if (!DaoSerializacaoDepartamento.emTransacao) {
			DaoSerializacaoDepartamento.emTransacao = true;
			this.proprietarioTransacao = true;
			this.lerObjetos();
		} else
			throw new DaoException("Já há uma transação aberta.");
	}

	@Override
	public void incluir(Escola d) throws DaoException {
		if (this.proprietarioTransacao) {
			this.conjDepartamento.add(d);
			Collections.sort(this.conjDepartamento);
		}
		else
			throw new DaoException("Não há transação aberta.");
	}

	@Override
	public void alterar(Escola d) throws DaoException {
		if (!this.proprietarioTransacao || !this.conjDepartamento.contains(d))
			throw new DaoException("Não há transação aberta.");
	}

	@Override
	public void excluir(Escola d) throws DaoException {
		if (this.proprietarioTransacao) {
			this.conjDepartamento.remove(d);
			Collections.sort(this.conjDepartamento);
		}
		else
			throw new DaoException("Não há transação aberta.");
	}

	@Override
	public List<Escola> obterObjetos() {
		this.lerObjetos();
		return new ArrayList<Escola>(this.conjDepartamento);
	}

	@Override
	public Escola obterDepartamentoPelaSigla(String sigla) {
		this.lerObjetos();
		for (Escola d : this.conjDepartamento)
			if (d.getSigla().equals(sigla))
				return d;
		return null;
	}

	@Override
	public void commit() throws DaoException {
		if (this.proprietarioTransacao) {
			this.salvarObjetos();
			DaoSerializacaoDepartamento.emTransacao = false;
			this.proprietarioTransacao = false;
		} else
			throw new DaoException("Não há transação aberta para commit");
	}

	private void salvarObjetos() {
		try {
			FileOutputStream f = new FileOutputStream("departamento.dat");
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(this.conjDepartamento);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollback() {
		DaoSerializacaoDepartamento.emTransacao = false;
		this.proprietarioTransacao = false;
	}
}*/
