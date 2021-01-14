/*package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoJDBCDepartamento implements DaoDepartamento {
	//
	// ATRIBUTOS
	//
	private static Connection conexao = null;
	
	//
	// MÉTODOS
	//
	public DaoJDBCDepartamento() throws DaoException {
		if(DaoJDBCDepartamento.conexao == null)
			DaoJDBCDepartamento.abrirConexao();
	}

	private static void abrirConexao() throws DaoException {
		String conta = "postgres";
		String senha = "postgres";

		try {
			// ---- Carga do driver JDBC Postgres ----//
			Class.forName("org.postgresql.Driver");
			// ---- Abrir conexão com BD exemplo  ----//
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exemplo", conta, senha);
			// ---- As transações só são efetivadas após o commit----//
			conexao.setAutoCommit(false);			
		} catch (ClassNotFoundException cnfe) {
			throw new DaoException("Não foi encontrado o driver:" + cnfe.getMessage());
		} catch (SQLException sqle) {
			throw new DaoException("Erro na conexão: " + sqle.getMessage());
		}
	
	}
	
	@Override
	public void abrirTransacao() throws DaoException {
	}

	@Override
	public void incluir(Escola d) throws DaoException {
		try {
			PreparedStatement comando = conexao.prepareStatement("INSERT INTO public.\"Departamento\" VALUES (?,?)");
			comando.setString(1,d.getSigla());
			comando.setString(2,d.getNome());
			comando.executeUpdate();
			comando.close();
		}
		catch(SQLException sqle) {
			throw new DaoException("Erro na inserção do Departamento: " + sqle.getMessage());			
		}		
	}

	@Override
	public void alterar(Escola d) throws DaoException {
		try {
			PreparedStatement comando = conexao.prepareStatement("UPDATE public.\"Departamento\" SET nome = ? WHERE sigla = ?");
			comando.setString(1,d.getNome());
			comando.setString(2,d.getSigla());
			comando.executeUpdate();
			comando.close();
		}
		catch(SQLException sqle) {
			throw new DaoException("Erro na alteração do Departamento: " + sqle.getMessage());			
		}		
	}

	@Override
	public void excluir(Escola d) throws DaoException {
		try {
			PreparedStatement comando = conexao.prepareStatement("delete from public.\"Departamento\" WHERE sigla = ?");
			comando.setString(1,d.getNome());
			comando.setString(2,d.getSigla());
			comando.executeUpdate();
			comando.close();
		}
		catch(SQLException sqle) {
			throw new DaoException("Erro na alteração do Departamento: " + sqle.getMessage());			
		}		
	}

	@Override
	public List<Escola> obterObjetos() throws DaoException{
		ArrayList<Escola> resultado = new ArrayList<Escola>();
		try {
			PreparedStatement comando = conexao.prepareStatement("SELECT sigla, nome FROM  public.\"Departamento\"");
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				String sigla = rs.getString("sigla");
				String nome  = rs.getString("nome");
				Escola d = new Escola(sigla, nome);
				resultado.add(d);
			}
			rs.close();
			comando.close();
		}
		catch(SQLException sqle) {
			throw new DaoException("Erro na recuperação do Departamento: " + sqle.getMessage());			
		}		
		catch(ModelException me) {
			throw new DaoException("Erro na recuperação do Departamento: " + me.getMessage());			
		}		
		Collections.sort(resultado);
		return resultado;
	}

	@Override
	public Escola obterDepartamentoPelaSigla(String sigla) throws DaoException {
		Escola resultado = null;
		try {
			PreparedStatement comando = conexao.prepareStatement("SELECT sigla, nome FROM public.\"Departamento\" WHERE sigla = ?");
			comando.setString(1, sigla);
			ResultSet rs = comando.executeQuery();
			while(rs.next()) {
				String s = rs.getString("sigla");
				String n  = rs.getString("nome");
				resultado = new Escola(s, n);
			}
			rs.close();
			comando.close();
		}
		catch(SQLException sqle) {
			throw new DaoException("Erro na recuperação do Departamento: " + sqle.getMessage());			
		}		
		catch(ModelException me) {
			throw new DaoException("Erro na recuperação do Departamento: " + me.getMessage());			
		}		
		return resultado;
	}

	@Override
	public void commit() throws DaoException {
		try {
			conexao.commit();
		} catch (SQLException e) {
			throw new DaoException("Erro na transação: " + e.getMessage());
		}
	}

	@Override
	public void rollback() {
		try {
			conexao.commit();
		} catch (SQLException e) {
		}	
	}
}*/
