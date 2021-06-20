package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	// Toda a regra de negocio acontece nesta classe

	// CRUD = CREATE (INSERT), READ (SELECT), UPDATE E DELETE

	// DAO = data access object

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// Adicionar valores que são eserados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// executar a query
			pstm.execute();

			System.out.println("Contato Salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			// criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Qual o Id do registro que deseja atualizar
			pstm.setInt(4, contato.getId());

			// executar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			System.out.println("Lista de contatos cadastrados: ");

			while (rset.next()) {

				Contato contato = new Contato();

				// Recuperar o id
				contato.setId(rset.getInt("id"));
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar a idade
				contato.setId(rset.getInt("idade"));
				// Recuperar a data de cadastrado
				contato.setDataCadastro(rset.getDate("datacadastro"));

				contatos.add(contato);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contatos;
	}

	public void deleteByID(int id) {

		String sql = "DELETE FROM contatos WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {

					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}