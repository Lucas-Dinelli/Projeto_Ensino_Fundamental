package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity 
@Table(name = "Escola")
public class Escola implements Comparable<Escola> {
	//
	//  ATRIBUTOS
	//
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String nome;
	
	@Column
	private String cnpj;
	
	@OneToMany(mappedBy = "escola")   //estava -> "dpto"
	@OrderBy("codTurma")
	private List<Turma> listaTurmas;
	
	//
	// MÉTODOS
	//
	public Escola() {
		
	}
	
	public Escola(String nome, String cnpj) throws ModelException {
		super();
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.listaTurmas = new ArrayList<Turma>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getSigla() {
		return sigla;
	}

	public void setSigla(String s) throws ModelException {
		if(!Departamento.validarSigla(s))
			throw new ModelException("A sigla passada é inválida: " + s);
		this.sigla = s;
	}*/

	
	public String getNome() {
		return nome;
	}
	
	
	
	public void setNome(String nome) throws ModelException {
		
		//validacao
		
		if(!Escola.nomeValido(nome)) {
			
			throw new ModelException("Por favor, digite um nome válido. Somente letras, dígitos, traços ou espaços serão permitidos!");
		}
		else {
			this.nome = nome;
		}
	}
	
	
	
	
	
	
	
	public String getCnpj() {
		return cnpj;
	}
	
	

	public void setCnpj(String cnpj) throws ModelException {
		
		//validacao
		
		if(!(Escola.cnpjTamanhoValido(cnpj) && Escola.cnpjCaracteresValidos(cnpj))) {
			
			throw new ModelException("Por favor, digite o CNPJ corretamente!");
		}
		else {
			
			this.cnpj = cnpj;
		}
	}
	
	
	
	
	
	
	

	/*public void setNome(String nome) throws ModelException {
		if(!Escola.validarNome(nome))
			throw new ModelException("O nome passado é inválido: " + nome);
		this.nome = nome;
	}*/

	public List<Turma> getListaTurmas() {
		return new ArrayList<Turma>(this.listaTurmas);
	}
 	
	public void setListaTurmas(List<Turma> novaLista) {
		this.listaTurmas = novaLista;
	}
 	
	public void adicionarTurma(Turma turma) throws ModelException {
		if(!Escola.validarTurma(turma)) {
			throw new ModelException("Não se indicou a turma a ser incluída na escola");
		}
		else {
			this.listaTurmas.add(turma);
			Collections.sort(this.listaTurmas);
		}
	}
	
	public int removerTurma(Turma turma) throws ModelException {
		int index = -1;
		if(!Escola.validarTurma(turma)) {
			throw new ModelException("Não se indicou a turma a ser removida da escola");
		}
		else {
			index = this.listaTurmas.indexOf(turma);
			this.listaTurmas.remove(turma);
		}
		return(index);
	}

	
	@Override
	public String toString() {
		return nome;
	}
	
	
	public int compareTo(Escola outra) {
		return this.nome.compareTo(outra.nome);
	}
	
	
	
	
	
	
	
	
	
	
	public static boolean nomeValido(String nome) {
			
		boolean isValid = true;
			
		if(nome.length()>2 && nome.length()<50) {
				
			for(int i=0; i<nome.length(); i++) {
					
				char caractere = nome.charAt(i);
					
				if(!(Character.isLetterOrDigit(caractere) || caractere=='-' || caractere==' ')) {
					isValid = false;
					break;
				}
			}
		}
		else {
			
			isValid = false;
		}
			
		return(isValid);	
	}
		
	
	
	
	
	
	
	
	public static boolean cnpjTamanhoValido(String cnpj) {
		
		if(cnpj.length()==14) {
			
			return(true);
		}
		else {
			
			return(false);
		}
		
	}
	
	
	
	public static boolean cnpjCaracteresValidos(String cnpj) {
		
		boolean isValid = true;
		
		for(int i=0; i<cnpj.length(); i++) {
			
			if(!(Character.isDigit(cnpj.charAt(i)))){
				
				isValid = false;
				
				break;
			}
		}
		
		return(isValid);
	}
	
	
	
	public static boolean validarTurma(Turma turma) {
		
		if(turma != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	

	/*public static boolean validarNome(String nome) {
		if(nome != null && nome.length() > 2 && nome.length() <= 40)
			return true;
		return false;
	}

	public static boolean validarEmpregado(Empregado e) {
		if(e != null)
			return true;
		return false;
	}*/
}
