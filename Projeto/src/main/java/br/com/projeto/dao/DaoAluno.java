package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.projeto.pojo.Aluno;

public class DaoAluno {
	private Connection connection;
	private String url = "jdbc:hsqldb:hsql://localhost:9002/";
	private String user = "SA";
	private String password = "";

	// Construtor que inicializa a conexão com o banco de dados
	public DaoAluno() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e){
			e.printStackTrace(); // Exibe erro no console se a conexão falhar
		}
	}

	// Método para inserir um novo aluno no banco de dados
	public void insert(Aluno aluno) {
		String sql = "INSERT INTO Alunos VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, aluno.getRa()); // Define o RA do aluno
			pstmt.setString(2, aluno.getNome()); // Define o nome do aluno
			// Converte a data de nascimento
			pstmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(4,  aluno.getRenda()); // Define a renda do aluno
			pstmt.executeUpdate(); // Executa a inserção
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para atualizar os dados de um aluno existente no banco de dados
	public void update(Aluno aluno) {
		String sql = "UPDATE Alunos SET NOME=?, DATANASCIMENTO=?, RENDA=? WHERE RA=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, aluno.getNome());
			pstmt.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(3,  aluno.getRenda());
			pstmt.setInt(4, aluno.getRa());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para excluir aluno do banco de dados
	public void delete(Aluno aluno) {
		String sql = "DELETE FROM Alunos WHERE RA =?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, aluno.getRa()); // Define o RA do aluno a ser excluído
			pstmt.executeUpdate(); // Executa a exclusão
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método que retorna uma lista com todos os alunos do banco de dados
	public List<Aluno> getLista(){
		List<Aluno> lista = new ArrayList<Aluno>();
		String sql = "SELECT * FROM Alunos";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// Percorre os resultados e adiciona os alunos à lista
			while (rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				lista.add(new Aluno(ra, nome, dataNascimento, renda));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista; // Retorna a lista de alunos
	}

	// Método que busca um aluno pelo RA
	public Aluno buscarPeloRa (int ra) {
		String sql = "SELECT * FROM Alunos WHERE RA = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ra);
			ResultSet rs = pstmt.executeQuery();

			// Se encontrar o aluno, cria e retorna o objeto Aluno
			if (rs.next()) {
				String nome = rs.getString(2);
				Date dataNascimento = rs.getDate(3);
				Double renda = rs.getDouble(4);
				return new Aluno(ra, nome, dataNascimento, renda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Retorna null caso o aluno não seja encontrado
	}
}