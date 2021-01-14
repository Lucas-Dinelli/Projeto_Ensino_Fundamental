/*package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class DaoSerializacaoEmpregado implements DaoEmpregado {
	//
	// ATRIBUTOS
	//
	private static boolean emTransacao = false;
	private List<Turma> listaEmpregados;
	private boolean proprietarioTransacao = false;

	//
	// MÉTODOS
	//
	public DaoSerializacaoEmpregado() {
		this.proprietarioTransacao = false;
	}
	
	public DaoSerializacaoEmpregado(boolean abrir) throws DaoException {
		if(abrir)
			this.abrirTransacao();
		else
			this.proprietarioTransacao = false;
	}

	private void lerObjetos() {
		try {
			FileInputStream f = new FileInputStream("empregado.dat");
			ObjectInputStream ois = new ObjectInputStream(f);
			this.listaEmpregados = (List<Turma>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			this.listaEmpregados = new ArrayList<Turma>();
		}
	}
	
	public void abrirTransacao() throws DaoException {
		if (!DaoSerializacaoEmpregado.emTransacao) {
			DaoSerializacaoEmpregado.emTransacao = true;
			this.proprietarioTransacao = true;
			this.lerObjetos();
		}
		else
			throw new DaoException("Já há uma transação aberta.");
	}

	public void incluir(Turma d) throws DaoException {
		if(this.proprietarioTransacao) {
			this.listaEmpregados.add(d);
			Collections.sort(this.listaEmpregados);
		}
		else
			throw new DaoException("Não há transação aberta.");
	}

	public void alterar(Turma e) throws DaoException {
		if(!this.proprietarioTransacao)
			throw new DaoException("Não há transação aberta.");
		if(!this.listaEmpregados.contains(e))
			throw new DaoException("O Empregado " + e + " não está na base de dados.");
	}

	public void excluir(Turma d) throws DaoException {
		if(this.proprietarioTransacao) {
			this.listaEmpregados.remove(d);
			Collections.sort(this.listaEmpregados);
		}
		else
			throw new DaoException("Não há transação aberta.");
	}

	public List<Turma> obterObjetos() {
		this.lerObjetos();
		return new ArrayList<Turma>(this.listaEmpregados);
	}
	
	public Turma obterEmpregadoPeloCpf(String cpf) {
		this.lerObjetos();
		for(Turma e : this.listaEmpregados)
			if(e.getCpf().equals(cpf))
				return e;
		return null;
	}

	public void commit() throws DaoException {
		if(this.proprietarioTransacao) {
			this.salvarObjetos();
			DaoSerializacaoEmpregado.emTransacao = false;
			this.proprietarioTransacao = false;
		}
		else
			throw new DaoException("Não há transação aberta para commit");
	}

	private void salvarObjetos() {
		try {
			FileOutputStream f = new FileOutputStream("empregado.dat");
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(this.listaEmpregados);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		DaoSerializacaoEmpregado.emTransacao = false;
		this.proprietarioTransacao = false;
	}
}*/
