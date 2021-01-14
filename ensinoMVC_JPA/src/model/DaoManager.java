package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoManager {
	private static Properties prop;

	static {
		String propFileName = "dao.properties";
		prop = new Properties();

		InputStream is = DaoManager.class.getClassLoader().getResourceAsStream(propFileName);
		if (is != null) {
			try {
				prop.load(is);
			} catch (IOException e) {
				System.out.println("O arquivo dao.properties não foi encontrado.");
			}
		}
	}

	public static DaoEscola obterDaoEscola() throws DaoException {
		String nomeClasse = prop.getProperty("DaoEscola");
		try {
			Class classeDao = Class.forName(nomeClasse);
			return (DaoEscola)classeDao.newInstance();
		} catch (Exception e) {
			throw new DaoException("Erro na recuperação do DAO: " + e.getMessage());
		} 
	}

	public static DaoTurma obterDaoTurma() throws DaoException {
		String nomeClasse = prop.getProperty("DaoTurma");
		try {
			Class classeDao = Class.forName(nomeClasse);
			return (DaoTurma)classeDao.newInstance();
		} catch (Exception e) {
			throw new DaoException("Erro na recuperação do DAO: " + e.getMessage());
		} 
	}
}
