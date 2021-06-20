package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	// Nome do usuario do mysql
	private static final String USERNAME = "root";

	// Senha do banco
	private static final String PASSWORD = "";

	// Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	// conex�o com o banco de dados
	public static Connection createConnectionToMySQL() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// Recuperar uma conex�o com o banco de dados

		Connection con = createConnectionToMySQL();

		// Testar se a conex�o � nula

		if (con != null) {
			System.out.println("Conex�o obtida com sucesso");
			con.close();
		}
	}

}
