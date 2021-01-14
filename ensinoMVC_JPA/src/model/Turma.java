package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Turma")
public class Turma implements Serializable, Comparable<Turma> {
	//
	// ATRIBUTOS
	//
	@Id
	@GeneratedValue
    private int id;
	
	@Column
	private int serie;
	
	@Column
	private int codTurma;
	
	@Column
	private String turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "escola", foreignKey = @ForeignKey(name = "fk_turma_escola"))
	private Escola escola;
	
	@Lob  // Large Object 
	@Column(name = "foto")
	@Basic(fetch= FetchType.LAZY)
	public byte[] foto;

	//
	// MÉTODOS
	//
	public Turma() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Turma(int serie, int codTurma, String turno, Escola escola, byte[] foto) throws ModelException {
		super();
		this.setSerie(serie);
		this.setCodTurma(codTurma);
		this.setTurno(turno);
		this.setEscola(escola);
		this.setFoto(foto);
	}
	
	
	
	
	
	public int getSerie() {
		return serie;
	}
	
	
	

	public void setSerie(int serie) throws ModelException {
		
		if(!Turma.serieValida(serie)) {
			
			throw new ModelException("Por favor, apenas séries do Ensino Fundamental (da 1ª até a 9ª série)!");
		}
		else {
			this.serie = serie;
		}
	}
	
	
	
	
	

	/*public String getCpf() {
		return cpf;
	}

	public void setCpf(String c) throws ModelException {
		if(!Empregado.validarCpf(c))
			throw new ModelException("O cpf passado é inválido: " + c);
		this.cpf = c;
	}*/
	
	
	
	public int getCodTurma() {
		return codTurma;
	}

	
	public void setCodTurma(int codTurma) throws ModelException {
		
		if(!Turma.codTurmaValido(codTurma, getSerie())) {
			
			throw new ModelException("Digite o código da turma de acordo com a série!");
			
		}
		else {
			this.codTurma = codTurma;
		}
	}
	
	
	
	
	
	
	
	
	public String getTurno() {
		return turno;
	}

	
	public void setTurno(String turno) throws ModelException {
		
		if(!Turma.turnoValido(turno)) {
			
			throw new ModelException("Apenas Manhã, Tarde ou Noite");
			
		}
		else {
			this.turno = turno;
		}
		
	}
	
	
	
	
	
	

	/*public String getNome() {
		return nome;
	}

	public void setNome(String n) throws ModelException {
		if(!Empregado.validarNome(n))
			throw new ModelException("O nome passado é inválido: " + n);
		this.nome = n;
	}*/
	
	
	
	
	
	public Escola getEscola() {
		return escola;
	}
	

	
	public void setEscola(Escola escola) throws ModelException {
		
		if(!Turma.escolaValida(escola)) {
			
			throw new ModelException("A Turma precisa estar vinculada a uma Escola!");
			
		}
		else {
			
			this.escola = escola;
			
			escola.adicionarTurma(this);
			
		}
	}
	
	
	
	
	
	
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
	

	/*public void setDepto(Departamento d) throws ModelException {
		if(!Empregado.validarDepartamento(d))
			throw new ModelException("Não informado o departamento para o Empregado.");
		this.depto = d;
		d.adicionarEmpregado(this);
	}
	
	public Departamento getDepto() {
		return this.depto;
	}*/
	
	
	
	
	
	public int compareTo(Turma outra) {
		
		String thisSerieString = Integer.toString(this.serie);
		
		String outraSerieString = Integer.toString(outra.serie);
		
		return(thisSerieString.compareTo(outraSerieString));
		
		//return this.serie.compareTo(outra.serie);   // Este método pode acabar inibindo valores repetidos(retirar)
	}
	
	
	
	
	
	@Override
	public String toString() {
		
		return("Turma " + getCodTurma() + " - " + getSerie() + "ª Série => Turno da " + getTurno() + ".");
	}
	
	
	
	
	
	
	
	
	
	//------------------------------------------------ Validações ----------------------------------------------------
	
	
	
	
		public static boolean serieValida(int serie) {
			
			if(serie>=1 && serie<=9) {
				return(true);
			}
			else {
				return(false);
			}
		}
		
		
		
		
		public static boolean codTurmaValido(int codTurma, int serie) {
			
			if(codTurma>(serie*100) && codTurma<((serie*100)+100)) {
				return(true);
			}
			else {
				return(false);
			}
		}
		
		
		
		
		
		public static boolean turnoValido(String turno) {
			
			if(turno.equals("Manhã") || turno.equals("Tarde") || turno.equals("Noite")) {
				return(true);
			}
			else {
				return(false);
			}
		}
		
		
		
		
		
		public static boolean escolaValida(Escola escola) {
			
			if(escola!=null) {
				return(true);
			}
			else {
				return(false);
			}
			
		}
	
	
	
	
	
	
	
	
	
	/*public static boolean validarCpf(String cpf) {
		if(cpf != null && cpf.length() >= 11 && cpf.length() <= 14)
			return true;
		return false;
	}
	
	public static boolean validarNome(String nome) {
		if(nome != null && nome.length() > 2)
			return true;
		return false;
	}

	public static boolean validarDepartamento(Departamento depto) {
		if(depto != null)
			return true;
		return false;
	}*/
}
