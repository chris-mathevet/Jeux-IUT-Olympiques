package database;

import java.sql.*;

public class ConnexionMySql {
	private Connection mysql;
	public ConnexionMySql() throws ClassNotFoundException{
		this.mysql=null;
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		this.mysql=null;
		this.mysql = DriverManager.getConnection(
					"jdbc:mysql://cigale1.lescigales.org:3310/s4a_julian","s4a_julian"," ");
	}
	
	public void close() throws SQLException {
		this.mysql.close();
	}
    public Statement createStatement() throws SQLException {
        if (this.mysql == null) {
            throw new SQLException("pas connecté");
        }
        return this.mysql.createStatement();
    }


	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
